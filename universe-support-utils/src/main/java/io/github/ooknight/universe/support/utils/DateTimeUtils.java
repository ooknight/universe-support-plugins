package io.github.ooknight.universe.support.utils;

import java.time.LocalDateTime;

public final class DateTimeUtils {

    public LocalDateTime now() {
        return Chrono.datetime();
    }

    public long timestamp() {
        return Chrono.timestamp();
    }
}
