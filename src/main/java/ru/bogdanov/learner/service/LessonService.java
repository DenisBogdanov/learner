package ru.bogdanov.learner.service;

import ru.bogdanov.learner.exception.NotFoundException;
import ru.bogdanov.learner.model.Lesson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Denis, 16.09.2018
 */
public interface LessonService {

    Lesson get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    default List<Lesson> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
    }

    List<Lesson> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    List<Lesson> getAll(int userId);

    void update(Lesson lesson, int userId) throws NotFoundException;

    Lesson create(Lesson lesson, int userId);
}
