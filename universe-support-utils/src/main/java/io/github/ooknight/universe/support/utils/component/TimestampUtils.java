package io.github.ooknight.universe.support.utils.component;

import io.github.ooknight.universe.support.utils.DTF;

public final class TimestampUtils {

    public String standard() {
        return DTF.DATE_TIME_COMPACT.format(Chrono.datetime());
    }

    public long now() {
        return Chrono.millisecond();
    }
}
