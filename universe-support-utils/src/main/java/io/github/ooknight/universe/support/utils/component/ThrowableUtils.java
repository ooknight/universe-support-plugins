package io.github.ooknight.universe.support.utils.component;

public final class ThrowableUtils {

    public void propagate(E1 executable) {
        try {
            executable.execute();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T propagate(E2<T> executable) {
        try {
            return executable.execute();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface E1 {
        void execute() throws Throwable;
    }

    @FunctionalInterface
    public interface E2<T> {
        T execute() throws Throwable;
    }
}
