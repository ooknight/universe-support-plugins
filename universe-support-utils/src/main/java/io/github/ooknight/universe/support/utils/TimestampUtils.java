package io.github.ooknight.universe.support.utils;

import oo.DTF;

public final class TimestampUtils {

    public String standard() {
        return DTF.DATE_TIME_COMPACT.format(Chrono.datetime());
    }
}
