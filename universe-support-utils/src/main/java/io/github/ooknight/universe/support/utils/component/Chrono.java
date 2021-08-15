package io.github.ooknight.universe.support.utils.component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 所有获取当前时间的方法都基于该类的方法，
 * 方便需要的时候扩展穿越机制
 */
final class Chrono {

    private Chrono() {}

    static LocalDate date() {
        return LocalDate.now();
    }

    static LocalDateTime datetime() {
        return LocalDateTime.now();
    }

    static LocalTime time(LocalTime time) {
        return LocalTime.now();
    }

    static long millisecond() {
        return System.currentTimeMillis();
    }
}
