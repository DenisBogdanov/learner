package ru.bogdanov.learner.web.user;

import static ru.bogdanov.learner.util.LessonUtil.DEFAULT_DAILY_GOAL;

/**
 * Denis, 16.09.2018
 */
public class SecurityUtil {

    public static int authUserId() {
        return 1;
    }

    public static int authUserDailyGoal() {
        return DEFAULT_DAILY_GOAL;
    }
}
