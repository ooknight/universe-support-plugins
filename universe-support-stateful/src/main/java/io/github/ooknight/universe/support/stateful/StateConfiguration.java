package io.github.ooknight.universe.support.stateful;

import io.github.ooknight.universe.support.stateful.delegates.Action;
import io.github.ooknight.universe.support.stateful.delegates.Selector;
import io.github.ooknight.universe.support.stateful.triggers.TriggerBehaviourDynamic;
import io.github.ooknight.universe.support.stateful.triggers.TriggerBehaviourInternal;
import io.github.ooknight.universe.support.stateful.triggers.TriggerBehaviourTransitioning;

import java.util.function.Predicate;

public class StateConfiguration<S, T, C> {

    private final StateRepresentation<S, T, C> representation;

    StateConfiguration(StateRepresentation<S, T, C> representation) {
        this.representation = representation;
    }

    /**
     * Accept the specified trigger and transition to the destination state
     *
     * @param trigger     The accepted trigger
     * @param destination The state that the trigger will cause a transition to
     * @return The receiver
     */
    public StateConfiguration<S, T, C> permit(T trigger, S destination) {
        return permit(trigger, destination, null, null);
    }

    /**
     * Accept the specified trigger and transition to the destination state.
     * <p>
     * Additionally a given action is performed when transitioning. This action will be called after
     * the onExit action of the current state and before the onEntry action of
     * the destination state.
     *
     * @param trigger     The accepted trigger
     * @param destination The state that the trigger will cause a transition to
     * @param action      The action to be performed "during" transition
     * @return The receiver
     */
    public StateConfiguration<S, T, C> permit(T trigger, S destination, Action<S, T, C> action) {
        return permit(trigger, destination, null, action);
    }

    /**
     * Accept the specified trigger and transition to the destination state if guard is true
     *
     * @param trigger     The accepted trigger
     * @param destination The state that the trigger will cause a transition to
     * @param guard       Function that must return true in order for the trigger to be accepted
     * @return The receiver
     */
    public StateConfiguration<S, T, C> permit(T trigger, S destination, Predicate<C> guard) {
        return permit(trigger, destination, guard, null);
    }

    /**
     * Accept the specified trigger and transition to the destination state if guard is true
     * <p>
     * Additionally a given action is performed when transitioning. This action will be called after
     * the onExit action of the current state and before the onEntry action of
     * the destination state.
     *
     * @param trigger     The accepted trigger
     * @param destination The state that the trigger will cause a transition to
     * @param guard       Function that must return true in order for the trigger to be accepted
     * @param action      The action to be performed "during" transition
     * @return The receiver
     */
    public StateConfiguration<S, T, C> permit(T trigger, S destination, Predicate<C> guard, Action<S, T, C> action) {
        enforceNotIdentityTransition(destination);
        representation.addTriggerBehaviour(new TriggerBehaviourTransitioning<>(trigger, destination, guard, action));
        return this;
    }

    /**
     * Accept the specified trigger and transition to the destination state, calculated dynamically by the supplied
     * function
     *
     * @param trigger  The accepted trigger
     * @param selector Function to calculate the state that the trigger will cause a transition to
     * @return The receiver
     */
    public StateConfiguration<S, T, C> permit(T trigger, Selector<C, S> selector) {
        return permit(trigger, selector, null, null);
    }

    /**
     * Accept the specified trigger and transition to the destination state, calculated dynamically by the supplied
     * function
     * <p>
     * Additionally a given action is performed when transitioning. This action will be called after
     * the onExit action and before the onEntry action (of the re-entered state). The parameter of the
     * trigger will be given to this action.
     *
     * @param trigger  The accepted trigger
     * @param selector Function to calculate the state that the trigger will cause a transition to
     * @param action   The action to be performed "during" transition
     * @return The receiver
     */
    public StateConfiguration<S, T, C> permit(T trigger, Selector<C, S> selector, Action<S, T, C> action) {
        return permit(trigger, selector, null, action);
    }

    /**
     * Accept the specified trigger and transition to the destination state, calculated dynamically by the supplied
     * function
     *
     * @param trigger  The accepted trigger
     * @param selector Function to calculate the state that the trigger will cause a transition to
     * @param guard    Function that must return true in order for the  trigger to be accepted
     * @return The receiver
     */
    public StateConfiguration<S, T, C> permit(T trigger, Selector<C, S> selector, Predicate<C> guard) {
        return permit(trigger, selector, guard, null);
    }

    /**
     * Accept the specified trigger and transition to the destination state, calculated dynamically by the supplied
     * function
     * <p>
     * Additionally a given action is performed when transitioning. This action will be called after
     * the onExit action of the current state and before the onEntry action of the destination state.
     * The parameter of the trigger will be given to this action.
     *
     * @param trigger  The accepted trigger
     * @param selector Function to calculate the state that the trigger will cause a transition to
     * @param guard    Function that must return true in order for the  trigger to be accepted
     * @param action   The action to be performed "during" transition
     * @return The receiver
     */
    public StateConfiguration<S, T, C> permit(T trigger, Selector<C, S> selector, Predicate<C> guard, Action<S, T, C> action) {
        representation.addTriggerBehaviour(new TriggerBehaviourDynamic<>(trigger, selector, guard, action));
        return this;
    }

    /**
     * Accept the specified trigger, execute exit actions and re-execute entry actions. Reentry behaves as though the
     * configured state transitions to an identical sibling state
     * <p>
     * Applies to the current state only. Will not re-execute superstate actions, or  cause actions to execute
     * transitioning between super- and sub-states
     *
     * @param trigger The accepted trigger
     * @return The receiver
     */
    public StateConfiguration<S, T, C> reentry(T trigger) {
        return reentry(trigger, null, null);
    }

    /**
     * Accept the specified trigger, execute exit actions and re-execute entry actions. Reentry behaves as though the
     * configured state transitions to an identical sibling state
     * <p>
     * Applies to the current state only. Will not re-execute superstate actions, or  cause actions to execute
     * transitioning between super- and sub-states
     * <p>
     * Additionally a given action is performed when transitioning. This action will be called after
     * the onExit action and before the onEntry action (of the re-entered state).
     *
     * @param trigger The accepted trigger
     * @param action  The action to be performed "during" transition
     * @return The receiver
     */
    public StateConfiguration<S, T, C> reentry(T trigger, Action<S, T, C> action) {
        return reentry(trigger, null, action);
    }

    /**
     * Accept the specified trigger, execute exit actions and re-execute entry actions. Reentry behaves as though the
     * configured state transitions to an identical sibling state
     * <p>
     * Applies to the current state only. Will not re-execute superstate actions, or  cause actions to execute
     * transitioning between super- and sub-states
     *
     * @param trigger The accepted trigger
     * @param guard   Function that must return true in order for the trigger to be accepted
     * @return The receiver
     */
    public StateConfiguration<S, T, C> reentry(T trigger, Predicate<C> guard) {
        return reentry(trigger, guard, null);
    }

    /**
     * Accept the specified trigger, execute exit actions and re-execute entry actions. Reentry behaves as though the
     * configured state transitions to an identical sibling state
     * <p>
     * Applies to the current state only. Will not re-execute superstate actions, or  cause actions to execute
     * transitioning between super- and sub-states
     * <p>
     * Additionally a given action is performed when transitioning. This action will be called after
     * the onExit action and before the onEntry action (of the re-entered state).
     *
     * @param trigger The accepted trigger
     * @param guard   Function that must return true in order for the trigger to be accepted
     * @param action  The action to be performed "during" transition
     * @return The receiver
     */
    public StateConfiguration<S, T, C> reentry(T trigger, Predicate<C> guard, Action<S, T, C> action) {
        representation.addTriggerBehaviour(new TriggerBehaviourTransitioning<>(trigger, representation.getUnderlyingState(), guard, action));
        return this;
    }

    /**
     * ignore the specified trigger when in the configured state
     *
     * @param trigger The trigger to ignore
     * @return The receiver
     */
    public StateConfiguration<S, T, C> ignore(T trigger) {
        return ignore(trigger, null);
    }

    /**
     * ignore the specified trigger when in the configured state, if the guard returns true
     *
     * @param trigger The trigger to ignore
     * @param guard   Function that must return true in order for the trigger to be ignored
     * @return The receiver
     */
    public StateConfiguration<S, T, C> ignore(T trigger, Predicate<C> guard) {
        representation.addTriggerBehaviour(new TriggerBehaviourInternal<>(trigger, guard, null));
        return this;
    }

    /**
     * Specify an action that will execute when transitioning into the configured state
     *
     * @param action Action to execute, providing details of the transition and trigger parameters
     * @return The receiver
     */
    public StateConfiguration<S, T, C> onEntry(Action<S, T, C> action) {
        representation.addEntryAction(action);
        return this;
    }

    /**
     * Specify an action that will execute when transitioning into the configured state
     *
     * @param trigger The trigger by which the state must be entered in order for the action to execute
     * @param action  Action to execute, providing details of the transition
     * @return The receiver
     */
    public StateConfiguration<S, T, C> onEntryFrom(T trigger, Action<S, T, C> action) {
        representation.addEntryAction(trigger, action);
        return this;
    }

    /**
     * Specify an action that will execute when transitioning from the configured state
     *
     * @param action Action to execute
     * @return The receiver
     */
    public StateConfiguration<S, T, C> onExit(Action<S, T, C> action) {
        representation.addExitAction(action);
        return this;
    }

    private void enforceNotIdentityTransition(S destination) {
        if (destination.equals(representation.getUnderlyingState())) {
            throw new IllegalStateException("Permit() (and PermitIf()) require that the destination state is not equal to the source state. To accept a trigger without changing state, use either ignore() or reentry().");
        }
    }
}
