package ru.bogdanov.learner.web;

import ru.bogdanov.learner.model.AbstractBaseEntity;

import static ru.bogdanov.learner.util.LessonUtil.DEFAULT_DAILY_GOAL;

/**
 * Denis, 16.09.2018
 */
public class SecurityUtil {

    private static int id = AbstractBaseEntity.START_SEQ;

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

    public static int authUserDailyGoal() {
        return DEFAULT_DAILY_GOAL;
    }
}
