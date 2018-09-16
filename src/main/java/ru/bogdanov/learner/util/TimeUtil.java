package ru.bogdanov.learner.util;

import java.time.LocalTime;

/**
 * Denis, 16.09.2018
 */
public class TimeUtil {
    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 &&
                lt.compareTo(endTime) <= 0;
    }
}
