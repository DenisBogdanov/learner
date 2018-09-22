package ru.bogdanov.learner.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bogdanov.learner.model.Lesson;

/**
 * Denis, 22.09.2018
 */
public interface CrudLessonRepository extends JpaRepository<Lesson, Integer> {
}
