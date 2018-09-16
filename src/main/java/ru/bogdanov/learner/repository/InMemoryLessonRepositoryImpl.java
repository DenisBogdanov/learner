package ru.bogdanov.learner.repository;

import ru.bogdanov.learner.model.Lesson;
import ru.bogdanov.learner.util.LessonUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Denis, 16.09.2018
 */
public class InMemoryLessonRepositoryImpl implements LessonRepository {

    private Map<Integer, Lesson> repository = new ConcurrentHashMap<>();
    private AtomicInteger id = new AtomicInteger(0);

    {
        LessonUtil.LESSONS.forEach(this::save);
    }

    @Override
    public Collection<Lesson> getAll() {
        return this.repository.values();
    }

    @Override
    public Lesson save(Lesson lesson) {
        if (lesson.isNew()) {
            lesson.setId(id.incrementAndGet());
            repository.put(lesson.getId(), lesson);
            return lesson;
        }
        return repository.computeIfPresent(lesson.getId(), (id, oldLesson) -> lesson);
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public Lesson get(int id) {
        return repository.get(id);
    }
}
