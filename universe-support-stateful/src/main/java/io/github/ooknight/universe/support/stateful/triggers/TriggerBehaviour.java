package io.github.ooknight.universe.support.stateful.triggers;

import io.github.ooknight.universe.support.stateful.delegates.Action;
import io.github.ooknight.universe.support.stateful.transitions.Transition;

import java.util.function.Predicate;

public abstract class TriggerBehaviour<S, T, C> {

    private final T trigger;
    /**
     * Note that this guard gets called quite often, and sometimes multiple times per fire() call.
     * Thus, it should not be anything performance intensive.
     */
    private final Predicate<C> guard;
    private final Action<S, T, C> action;

    TriggerBehaviour(T trigger, Predicate<C> guard, Action<S, T, C> action) {
        this.trigger = trigger;
        this.guard = guard;
        this.action = action;
    }

    public T getTrigger() {
        return trigger;
    }

    public void perform(Transition<S, T> transition, C context) {
        if (action != null) {
            action.execute(transition, context);
        }
    }

    public boolean isInternal() {
        return false;
    }

    public boolean isGuardConditionMet(C context) {
        if (guard == null) {
            return true;
        }
        return guard.test(context);
    }

    public abstract S transitionsTo(S source, C args);
}
