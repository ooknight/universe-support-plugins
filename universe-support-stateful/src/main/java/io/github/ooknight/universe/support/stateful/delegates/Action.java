package io.github.ooknight.universe.support.stateful.delegates;

import io.github.ooknight.universe.support.stateful.transitions.Transition;

/**
 * Represents an operation that accepts no input arguments and returns no result.
 */
@FunctionalInterface
public interface Action<S, T, C> {

    /**
     * Performs this operation
     */
    void execute(Transition<S, T> transition, C context);
}
