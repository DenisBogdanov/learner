package ru.bogdanov.learner.repository.mock;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.bogdanov.learner.model.Lesson;
import ru.bogdanov.learner.repository.LessonRepository;
import ru.bogdanov.learner.util.DateTimeUtil;
import ru.bogdanov.learner.util.LessonUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ru.bogdanov.learner.UserTestData.ADMIN_ID;
import static ru.bogdanov.learner.UserTestData.USER_ID;


/**
 * Denis, 16.09.2018
 */
@Repository
public class InMemoryLessonRepositoryImpl implements LessonRepository {

    // Map userId -> (lessonId -> lesson)
    private Map<Integer, Map<Integer, Lesson>> repository = new ConcurrentHashMap<>();
    private AtomicInteger id = new AtomicInteger(0);

    {
        LessonUtil.LESSONS.forEach(lesson -> save(lesson, USER_ID));

        save(new Lesson(LocalDateTime.of(2018, Month.SEPTEMBER, 16, 14, 0), "Java", 30), ADMIN_ID);
        save(new Lesson(LocalDateTime.of(2018, Month.SEPTEMBER, 16, 21, 0), "Java", 60), ADMIN_ID);
    }


    @Override
    public Lesson save(Lesson lesson, int userId) {
        Map<Integer, Lesson> lessons = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (lesson.isNew()) {
            lesson.setId(id.incrementAndGet());
            lessons.put(lesson.getId(), lesson);
            return lesson;
        }
        return lessons.computeIfPresent(lesson.getId(), (id, oldLesson) -> lesson);
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
        return getAllFiltered(userId, lesson -> DateTimeUtil.isBetween(lesson.getStartDateTime(), startDateTime, endDateTime));
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
