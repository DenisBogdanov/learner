package ru.bogdanov.learner.repository;

import ru.bogdanov.learner.model.Lesson;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Denis, 16.09.2018
 */
public interface LessonRepository {

    // null if updated lesson doesn't belong to user
    Lesson save(Lesson lesson, int userId);

    // false if lesson doesn't belong to user
    boolean delete(int id, int userId);

    // null if lesson doesn't belong to user
    Lesson get(int id, int userId);

    // ORDERED dateTime desc
    Collection<Lesson> getAll(int userId);

    // ORDERED dateTime desc
    List<Lesson> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);
}
