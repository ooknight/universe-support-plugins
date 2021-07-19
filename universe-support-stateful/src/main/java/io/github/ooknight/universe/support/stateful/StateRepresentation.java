package io.github.ooknight.universe.support.stateful;

import io.github.ooknight.universe.support.stateful.delegates.Action;
import io.github.ooknight.universe.support.stateful.transitions.Transition;
import io.github.ooknight.universe.support.stateful.triggers.TriggerBehaviour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class StateRepresentation<S, T, C> {

    private final S state;
    private final Map<T, List<TriggerBehaviour<S, T, C>>> behaviours = new HashMap<>();
    private final List<Action<S, T, C>> entryActions = new ArrayList<>();
    private final List<Action<S, T, C>> exitActions = new ArrayList<>();

    StateRepresentation(S state) {
        this.state = state;
    }

    Map<T, List<TriggerBehaviour<S, T, C>>> getTriggerBehaviours() {
        return behaviours;
    }

    boolean canHandle(T trigger, C context) {
        return tryFindHandler(trigger, context) != null;
    }

    TriggerBehaviour<S, T, C> tryFindHandler(T trigger, C context) {
        List<TriggerBehaviour<S, T, C>> possible = behaviours.get(trigger);
        if (possible == null) {
            return null;
        }
        List<TriggerBehaviour<S, T, C>> actual = new ArrayList<>();
        for (TriggerBehaviour<S, T, C> triggerBehaviour : possible) {
            if (triggerBehaviour.isGuardConditionMet(context)) {
                actual.add(triggerBehaviour);
            }
        }
        if (actual.size() > 1) {
            throw new IllegalStateException("Multiple permitted exit transitions are configured from state '" + state + "' for trigger '" + trigger + "'. Guard clauses must be mutually exclusive.");
        }
        return actual.isEmpty() ? null : actual.get(0);
    }

    void addEntryAction(T trigger, Action<S, T, C> action) {
        entryActions.add((transition, context) -> {
            T triggerT = transition.getTrigger();
            if (triggerT != null && triggerT.equals(trigger)) {
                action.execute(transition, context);
            }
        });
    }

    void addEntryAction(Action<S, T, C> action) {
        entryActions.add(action);
    }

    void addExitAction(Action<S, T, C> action) {
        exitActions.add(action);
    }

    void enter(Transition<S, T> transition, C context) {
        if (transition.isReentry() || !includes(transition.getSource())) {
            executeEntryActions(transition, context);
        }
    }

    void exit(Transition<S, T> transition, C context) {
        if (transition.isReentry() || !includes(transition.getDestination())) {
            executeExitActions(transition, context);
        }
    }

    void addTriggerBehaviour(TriggerBehaviour<S, T, C> triggerBehaviour) {
        List<TriggerBehaviour<S, T, C>> allowed;
        if (!behaviours.containsKey(triggerBehaviour.getTrigger())) {
            allowed = new ArrayList<>();
            behaviours.put(triggerBehaviour.getTrigger(), allowed);
        }
        allowed = behaviours.get(triggerBehaviour.getTrigger());
        allowed.add(triggerBehaviour);
    }

    S getUnderlyingState() {
        return state;
    }

    boolean includes(S stateToCheck) {
        return this.state.equals(stateToCheck);
    }

    List<T> getPermittedTriggers(C context) {
        Set<T> result = new HashSet<>();
        for (T t : behaviours.keySet()) {
            for (TriggerBehaviour<S, T, C> v : behaviours.get(t)) {
                if (v.isGuardConditionMet(context)) {
                    result.add(t);
                    break;
                }
            }
        }
        return new ArrayList<>(result);
    }

    private void executeEntryActions(Transition<S, T> transition, C context) {
        for (Action<S, T, C> action : entryActions) {
            action.execute(transition, context);
        }
    }

    private void executeExitActions(Transition<S, T> transition, C context) {
        for (Action<S, T, C> action : exitActions) {
            action.execute(transition, context);
        }
    }
}
