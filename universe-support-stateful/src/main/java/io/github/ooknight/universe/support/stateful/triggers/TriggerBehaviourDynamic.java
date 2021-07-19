package io.github.ooknight.universe.support.stateful.triggers;

import io.github.ooknight.universe.support.stateful.delegates.Action;
import io.github.ooknight.universe.support.stateful.delegates.Selector;

import java.util.function.Predicate;

public class TriggerBehaviourDynamic<S, T, C> extends TriggerBehaviour<S, T, C> {

    private final Selector<C, S> selector;

    public TriggerBehaviourDynamic(T trigger, Selector<C, S> selector, Predicate<C> guard, Action<S, T, C> action) {
        super(trigger, guard, action);
        this.selector = selector;
    }

    @Override
    public S transitionsTo(S source, C context) {
        return selector.apply(context);
    }
}
