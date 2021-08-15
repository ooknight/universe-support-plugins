package io.github.ooknight.universe.support.utils.component;

import java.time.LocalDateTime;

public final class DateTimeUtils {

    public LocalDateTime now() {
        return Chrono.datetime();
    }
}
