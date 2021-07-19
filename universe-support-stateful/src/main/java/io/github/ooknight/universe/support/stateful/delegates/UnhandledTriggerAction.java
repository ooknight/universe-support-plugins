package io.github.ooknight.universe.support.stateful.delegates;

@FunctionalInterface
public interface UnhandledTriggerAction<S, T, C> {

    void handle(S state, T trigger, C context);
}
