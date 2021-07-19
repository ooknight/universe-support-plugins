package io.github.ooknight.universe.support.stateful;

import io.github.ooknight.universe.support.stateful.delegates.UnhandledTriggerAction;
import io.github.ooknight.universe.support.stateful.transitions.Transition;
import io.github.ooknight.universe.support.stateful.triggers.TriggerBehaviour;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Models behaviour as transitions between a finite set of states
 *
 * @param <S> The type used to represent the states
 * @param <T> The type used to represent the triggers that cause state transitions
 */
public class StateMachine<S, T, C> {

    private static final Logger logger = LoggerFactory.getLogger(StateMachine.class);
    private static final String EMPTY = "";
    //
    private final StateMachineConfig<S, T, C> config;
    //
    private final C context;
    //
    private S state;
    private UnhandledTriggerAction<S, T, C> unhandledTriggerAction = Constant.DEFAULT_UNHANDLED_TRIGGER_ACTION();

    /**
     * Construct a state machine
     *
     * @param initialState The initial state
     * @param config       State machine configuration
     */
    StateMachine(S initialState, C context, StateMachineConfig<S, T, C> config) {
        this.state = initialState;
        this.context = context;
        this.config = config;
        if (config.isEntryActionOfInitialStateEnabled()) {
            Transition<S, T> initialTransition = new Transition<>(initialState, initialState, null);
            getCurrentRepresentation().enter(initialTransition, this.context);
        }
    }

    /**
     * The current state
     *
     * @return The current state
     */
    public S state() {
        return state;
    }

    /**
     * The currently-permissible trigger values
     *
     * @return The currently-permissible trigger values
     */
    public List<T> getPermittedTriggers(C context) {
        return getCurrentRepresentation().getPermittedTriggers(context);
    }

    /**
     * Transition from the current state via the specified trigger.
     * The target state is determined by the configuration of the current state.
     * Actions associated with leaving the current state and entering the new one
     * will be invoked
     *
     * @param trigger The trigger to fire
     */
    public void fire(T trigger) {
        _fire_(trigger);
    }

    /**
     * Override the default behaviour of throwing an exception when an unhandled trigger is fired
     *
     * @param unhandledTriggerAction An action to call with state, trigger and params when an unhandled trigger is fired
     */
    public void onUnhandledTrigger(UnhandledTriggerAction<S, T, C> unhandledTriggerAction) {
        if (unhandledTriggerAction == null) {
            throw new IllegalArgumentException("unhandledTriggerAction");
        }
        this.unhandledTriggerAction = unhandledTriggerAction;
    }

    /**
     * Determine if the state machine is in the supplied state
     *
     * @param state The state to test for
     * @return True if the current state is equal to, or a substate of, the supplied state
     */
    public boolean isInState(S state) {
        return getCurrentRepresentation().includes(state);
    }

    /**
     * Returns true if {@code trigger} can be fired  in the current state
     *
     * @param trigger Trigger to test
     * @return True if the trigger can be fired, false otherwise
     */
    public boolean canFire(T trigger) {
        return getCurrentRepresentation().canHandle(trigger, context);
    }

    /**
     * A human-readable representation of the state machine
     *
     * @return A description of the current state and permitted triggers
     */
    @Override
    public String toString() {
        List<T> permittedTriggers = getPermittedTriggers(context);
        List<String> parameters = new ArrayList<>();
        for (T tTrigger : permittedTriggers) {
            parameters.add(tTrigger.toString());
        }
        StringBuilder params = new StringBuilder();
        String delim = "";
        for (String param : parameters) {
            params.append(delim);
            params.append(param);
            delim = ", ";
        }
        return String.format("StateMachine {{ State = %s, PermittedTriggers = {{ %s }}}}", state(), params);
    }

    private void _fire_(T trigger) {
        TriggerBehaviour<S, T, C> triggerBehaviour = getCurrentRepresentation().tryFindHandler(trigger, context);
        if (triggerBehaviour == null) {
            unhandledTriggerAction.handle(getCurrentRepresentation().getUnderlyingState(), trigger, context);
            return;
        }
        logger.debug("Firing {}({})", trigger, Objects.toString(context, EMPTY));
        S source = state();
        S destination = triggerBehaviour.transitionsTo(source, context);
        Transition<S, T> transition = new Transition<>(source, destination, trigger);
        if (triggerBehaviour.isInternal()) {
            triggerBehaviour.perform(transition, context);
        } else {
            getCurrentRepresentation().exit(transition, context);
            triggerBehaviour.perform(transition, context);
            state = destination;
            getCurrentRepresentation().enter(transition, context);
            logger.debug("Fired [{}]--{}({})-->[{}]", source, trigger, Objects.toString(context, EMPTY), destination);
        }
    }

    private StateRepresentation<S, T, C> getCurrentRepresentation() {
        StateRepresentation<S, T, C> representation = config.getRepresentation(state());
        return representation == null ? new StateRepresentation<>(state()) : representation;
    }
}
