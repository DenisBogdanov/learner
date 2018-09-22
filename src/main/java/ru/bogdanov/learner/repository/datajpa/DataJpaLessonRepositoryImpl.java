package ru.bogdanov.learner.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bogdanov.learner.model.Lesson;
import ru.bogdanov.learner.repository.LessonRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Denis, 22.09.2018
 */
@Repository
public class DataJpaLessonRepositoryImpl implements LessonRepository {

    @Autowired
    private CrudLessonRepository crudRepository;

    @Override
    public Lesson save(Lesson lesson, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Lesson get(int id, int userId) {
        return null;
    }

    @Override
    public List<Lesson> getAll(int userId) {
        return null;
    }

    @Override
    public List<Lesson> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return null;
    }
}
