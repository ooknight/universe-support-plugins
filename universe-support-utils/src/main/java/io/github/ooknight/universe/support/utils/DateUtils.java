package io.github.ooknight.universe.support.utils;

import oo.DTF;

import java.time.LocalDate;

public final class DateUtils {

    public LocalDate now() {
        return Chrono.date();
    }

    public String yyyymmdd() {
        return DTF.DATE_YYYYMMDD.format(Chrono.date());
    }
}
