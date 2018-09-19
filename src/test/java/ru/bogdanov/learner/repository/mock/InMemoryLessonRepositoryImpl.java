package ru.bogdanov.learner.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.bogdanov.learner.model.Lesson;
import ru.bogdanov.learner.repository.LessonRepository;
import ru.bogdanov.learner.util.Util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Denis, 16.09.2018
 */
@Repository
public class InMemoryLessonRepositoryImpl implements LessonRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryLessonRepositoryImpl.class);

    // Map userId -> (lessonId -> lesson)
    private Map<Integer, Map<Integer, Lesson>> repository = new ConcurrentHashMap<>();
    private AtomicInteger id = new AtomicInteger(0);


    @Override
    public Lesson save(Lesson lesson, int userId) {
        Objects.requireNonNull(lesson);
        Map<Integer, Lesson> lessons = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (lesson.isNew()) {
            lesson.setId(id.incrementAndGet());
            lessons.put(lesson.getId(), lesson);
            return lesson;
        }
        return lessons.computeIfPresent(lesson.getId(), (id, oldLesson) -> lesson);
    }

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        LOGGER.info("+++ PreDestroy");
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Lesson> lessons = repository.get(userId);
        return lessons != null && lessons.remove(id) != null;
    }

    @Override
    public Lesson get(int id, int userId) {
        Map<Integer, Lesson> lessons = repository.get(userId);
        return lessons == null ? null : lessons.get(id);
    }

    @Override
    public List<Lesson> getAll(int userId) {
        return getAllFiltered(userId, lesson -> true);
    }

    @Override
    public List<Lesson> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Objects.requireNonNull(startDateTime);
        Objects.requireNonNull(endDateTime);
        return getAllFiltered(userId, lesson -> Util.isBetween(lesson.getStartDateTime(), startDateTime, endDateTime));
    }

    private List<Lesson> getAllFiltered(int userId, Predicate<Lesson> filter) {
        Map<Integer, Lesson> lessons = repository.get(userId);
        return CollectionUtils.isEmpty(lessons) ? Collections.emptyList() :
                lessons.values().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Lesson::getStartDateTime).reversed())
                        .collect(Collectors.toList());
    }
}
