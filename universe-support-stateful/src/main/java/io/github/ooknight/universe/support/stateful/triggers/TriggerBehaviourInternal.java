package io.github.ooknight.universe.support.stateful.triggers;

import io.github.ooknight.universe.support.stateful.delegates.Action;

import java.util.function.Predicate;

public class TriggerBehaviourInternal<S, T, C> extends TriggerBehaviour<S, T, C> {

    public TriggerBehaviourInternal(T trigger, Predicate<C> guard, Action<S, T, C> action) {
        super(trigger, guard, action);
    }

    @Override
    public boolean isInternal() {
        return true;
    }

    @Override
    public S transitionsTo(S source, C context) {
        return source;
    }
}
