package ru.bogdanov.learner.repository;

import ru.bogdanov.learner.model.Lesson;

import java.util.Collection;

/**
 * Denis, 16.09.2018
 */
public interface LessonRepository {

    Collection<Lesson> getAll();

    Lesson save(Lesson lesson);

    void delete(int id);

    Lesson get(int id);
}
