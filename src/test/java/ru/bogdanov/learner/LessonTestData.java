package ru.bogdanov.learner;

import ru.bogdanov.learner.model.Lesson;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.bogdanov.learner.model.AbstractBaseEntity.START_SEQ;

/**
 * Denis, 19.09.2018
 */
public class LessonTestData {

    public static final int LESSON_1_ID = START_SEQ + 2;
    public static final int ADMIN_LESSON_ID = START_SEQ + 7;

    public static final Lesson LESSON_1 = new Lesson(LESSON_1_ID, of(2018, Month.SEPTEMBER, 14, 8, 30), "Java", 60);
    public static final Lesson LESSON_2 = new Lesson(LESSON_1_ID + 1, of(2018, Month.SEPTEMBER, 14, 21, 30), "Java", 30);
    public static final Lesson LESSON_3 = new Lesson(LESSON_1_ID + 2, of(2018, Month.SEPTEMBER, 15, 12, 30), "Java", 60);
    public static final Lesson LESSON_4 = new Lesson(LESSON_1_ID + 3, of(2018, Month.SEPTEMBER, 15, 16, 30), "Java", 60);
    public static final Lesson LESSON_5 = new Lesson(LESSON_1_ID + 4, of(2018, Month.SEPTEMBER, 15, 19, 0), "Java", 60);
    public static final Lesson ADMIN_LESSON_1 = new Lesson(ADMIN_LESSON_ID, of(2018, Month.SEPTEMBER, 16, 14, 0), "Java", 30);
    public static final Lesson ADMIN_LESSON_2 = new Lesson(ADMIN_LESSON_ID + 1, of(2018, Month.SEPTEMBER, 16, 21, 0), "Java", 60);

    public static final List<Lesson> LESSONS = Arrays.asList(LESSON_5, LESSON_4, LESSON_3, LESSON_2, LESSON_1);

    public static Lesson getCreated() {
        return new Lesson(null, of(2018, Month.SEPTEMBER, 16, 12, 0), "Java", 90);
    }

    public static Lesson getUpdated() {
        return new Lesson(LESSON_1_ID, LESSON_1.getStartDateTime(), "New Java", 30);
    }

    public static void assertMatch(Lesson actual, Lesson expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
    }

    public static void assertMatch(Iterable<Lesson> actual, Lesson... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Lesson> actual, Iterable<Lesson> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }

}
