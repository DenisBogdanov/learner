package ru.bogdanov.learner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bogdanov.learner.exception.NotFoundException;
import ru.bogdanov.learner.model.Lesson;
import ru.bogdanov.learner.repository.LessonRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.bogdanov.learner.util.ValidationUtil.checkNotFoundWithId;

/**
 * Denis, 16.09.2018
 */
@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository repository;

    @Autowired
    public LessonServiceImpl(LessonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Lesson get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public List<Lesson> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return repository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public List<Lesson> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Lesson update(Lesson lesson, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.save(lesson, userId), lesson.getId());
    }

    @Override
    public Lesson create(Lesson lesson, int userId) {
        return repository.save(lesson, userId);
    }
}
