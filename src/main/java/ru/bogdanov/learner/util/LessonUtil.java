package ru.bogdanov.learner.util;

import ru.bogdanov.learner.model.Lesson;
import ru.bogdanov.learner.to.LessonWithGoal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Denis, 16.09.2018
 */
public class LessonUtil {

    public static final int DEFAULT_DAILY_GOAL = 120;

    public static final List<Lesson> LESSONS = Arrays.asList(
            new Lesson(LocalDateTime.of(2018, Month.SEPTEMBER, 14, 8, 30), "Java", 60),
            new Lesson(LocalDateTime.of(2018, Month.SEPTEMBER, 14, 21, 30), "Java", 30),
            new Lesson(LocalDateTime.of(2018, Month.SEPTEMBER, 15, 12, 30), "Java", 60),
            new Lesson(LocalDateTime.of(2018, Month.SEPTEMBER, 15, 16, 30), "Java", 60),
            new Lesson(LocalDateTime.of(2018, Month.SEPTEMBER, 15, 19, 0), "Java", 60)
    );

    public static List<LessonWithGoal> getWithGoal(Collection<Lesson> lessons, int dailyGoal) {
        return getFilteredByTimeWithGoal(lessons, dailyGoal, lesson -> true);
    }

    public static List<LessonWithGoal> getFilteredByTimeWithGoal(Collection<Lesson> lessons, int dailyGoal, LocalTime startTime, LocalTime endTime) {
        return getFilteredByTimeWithGoal(lessons, dailyGoal,
                lesson -> DateTimeUtil.isBetween(lesson.getTime(), startTime, endTime));
    }

    public static List<LessonWithGoal> getFilteredByTimeWithGoal(Collection<Lesson> lessons, int dailyGoal, Predicate<Lesson> filter) {

        Map<LocalDate, Integer> lessonsDurationByDate = lessons.stream()
                .collect(
                        Collectors.groupingBy(Lesson::getDate, Collectors.summingInt(Lesson::getDuration))
                );

        return lessons.stream()
                .filter(filter)
                .map(lesson -> createLessonWithGoal(lesson, lessonsDurationByDate.get(lesson.getDate()) > dailyGoal))
                .collect(Collectors.toList());
    }

    private static LessonWithGoal createLessonWithGoal(Lesson lesson, boolean goalAchieved) {
        return new LessonWithGoal(lesson.getId(),
                lesson.getStartDateTime(),
                lesson.getDescription(),
                lesson.getDuration(),
                goalAchieved);
    }
}
