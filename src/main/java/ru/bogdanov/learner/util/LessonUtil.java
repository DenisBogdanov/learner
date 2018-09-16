package ru.bogdanov.learner.util;

import ru.bogdanov.learner.model.Lesson;
import ru.bogdanov.learner.model.LessonWithGoal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Denis, 16.09.2018
 */
public class LessonUtil {
    public static void main(String[] args) {
        List<Lesson> lessons = Arrays.asList(
                new Lesson(LocalDateTime.of(2018, Month.SEPTEMBER, 14, 8, 30), "Java", 60),
                new Lesson(LocalDateTime.of(2018, Month.SEPTEMBER, 14, 21, 30), "Java", 30),
                new Lesson(LocalDateTime.of(2018, Month.SEPTEMBER, 15, 12, 30), "Java", 60),
                new Lesson(LocalDateTime.of(2018, Month.SEPTEMBER, 15, 16, 30), "Java", 60),
                new Lesson(LocalDateTime.of(2018, Month.SEPTEMBER, 15, 19, 0), "Java", 60)
        );

        List<LessonWithGoal> lessonsWithGoal = getWithGoal(lessons, LocalTime.of(7, 0), LocalTime.of(17, 0), 120);
        lessonsWithGoal.forEach(System.out::println);
    }

    public static List<LessonWithGoal> getWithGoal(List<Lesson> lessons, LocalTime startTime, LocalTime endTime, int dailyGoal) {

        Map<LocalDate, Integer> lessonsDurationByDate = lessons.stream()
                .collect(
                        Collectors.groupingBy(Lesson::getDate, Collectors.summingInt(Lesson::getDuration))
                );

        return lessons.stream()
                .filter(lesson -> TimeUtil.isBetween(lesson.getTime(), startTime, endTime))
                .map(lesson ->
                        new LessonWithGoal(lesson.getStartDateTime(),
                                lesson.getDescription(),
                                lesson.getDuration(),
                                lessonsDurationByDate.get(lesson.getDate()) >= dailyGoal))
                .collect(Collectors.toList());
    }
}
