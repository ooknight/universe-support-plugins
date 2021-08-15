package io.github.ooknight.universe.support.utils.component;

import java.time.LocalDate;

public final class DateUtils {

    public LocalDate now() {
        return Chrono.date();
    }
}
