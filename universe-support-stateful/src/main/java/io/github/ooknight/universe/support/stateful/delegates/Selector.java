package io.github.ooknight.universe.support.stateful.delegates;

/**
 * Represents a function that accepts an input and produces a result
 *
 * @param <C> Input argument type
 * @param <S> Result type
 */
@FunctionalInterface
public interface Selector<C, S> {

    /**
     * Applies this function to the given input
     *
     * @param context Input argument
     * @return Result
     */
    S apply(C context);
}
