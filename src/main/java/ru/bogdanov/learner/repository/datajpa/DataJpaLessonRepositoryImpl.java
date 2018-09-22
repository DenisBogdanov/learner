package ru.bogdanov.learner.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    private CrudLessonRepository crudLessonRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Lesson save(Lesson lesson, int userId) {
        if (!lesson.isNew() && get(lesson.getId(), userId) == null) {
            return null;
        }
        lesson.setUser(crudUserRepository.getOne(userId));
        return crudLessonRepository.save(lesson);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudLessonRepository.delete(id, userId) != 0;
    }

    @Override
    public Lesson get(int id, int userId) {
        return crudLessonRepository.findById(id)
                .filter(lesson -> lesson.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public List<Lesson> getAll(int userId) {
        return crudLessonRepository.getAll(userId);
    }

    @Override
    public List<Lesson> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudLessonRepository.getBetween(startDateTime, endDateTime, userId);
    }
}
