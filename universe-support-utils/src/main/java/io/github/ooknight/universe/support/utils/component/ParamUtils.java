package io.github.ooknight.universe.support.utils.component;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.function.Supplier;

public final class ParamUtils {

    public <T> T require(T value, T defaultValue) {
        return Optional.ofNullable(value).orElse(defaultValue);
    }

    public <T> T require(Supplier<T> supplier) {
        return optional_(supplier).orElseThrow(NullPointerException::new);
    }

    public <T> T require(Supplier<T> supplier, T defaultValue) {
        return optional_(supplier).orElse(defaultValue);
    }

    public <T> boolean exist(Supplier<T> supplier) {
        return optional_(supplier).isPresent();
    }

    private <T> Optional<T> optional_(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    public boolean valid(String value) {
        return (value != null) && (!StringUtils.isNotBlank(value));
    }

    public boolean valid(Object value) {
        return value != null;
    }
}
