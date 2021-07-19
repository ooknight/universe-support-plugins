package test.io.github.ooknight.universe.support.stateful;

import java.util.function.Predicate;

class Helper {

    static <C> Predicate<C> RETURN_TRUE(Class<C> c) {
        return context -> true;
    }

    static <C> Predicate<C> RETURN_FALSE(Class<C> c) {
        return context -> false;
    }
}
