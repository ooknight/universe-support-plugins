package io.github.ooknight.universe.support.stateful.triggers;

import io.github.ooknight.universe.support.stateful.delegates.Action;

import java.util.function.Predicate;

public class TriggerBehaviourTransitioning<S, T, C> extends TriggerBehaviour<S, T, C> {

    private final S destination;

    public TriggerBehaviourTransitioning(T trigger, S destination, Predicate<C> guard, Action<S, T, C> action) {
        super(trigger, guard, action);
        this.destination = destination;
    }

    @Override
    public S transitionsTo(S source, C context) {
        return destination;
    }
}
