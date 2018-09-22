package ru.bogdanov.learner.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bogdanov.learner.exception.NotFoundException;
import ru.bogdanov.learner.model.Lesson;

import java.time.LocalDate;
import java.time.Month;

import static ru.bogdanov.learner.LessonTestData.*;
import static ru.bogdanov.learner.UserTestData.ADMIN_ID;
import static ru.bogdanov.learner.UserTestData.USER_ID;

/**
 * Denis, 19.09.2018
 */
public abstract class AbstractLessonServiceTest extends AbstractServiceTest {

    @Autowired
    private LessonService service;

    @Test
    public void delete() throws Exception {
        service.delete(LESSON_1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), LESSON_5, LESSON_4, LESSON_3, LESSON_2);
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(LESSON_1_ID, 1);
    }

    @Test
    public void create() throws Exception {
        Lesson created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), created, LESSON_5, LESSON_4, LESSON_3, LESSON_2, LESSON_1);
    }

    @Test
    public void get() throws Exception {
        Lesson actual = service.get(ADMIN_LESSON_ID, ADMIN_ID);
        assertMatch(actual, ADMIN_LESSON_1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(LESSON_1_ID, ADMIN_ID);
    }

    @Test
    public void update() throws Exception {
        Lesson updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(LESSON_1_ID, USER_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + LESSON_1_ID);
        service.update(LESSON_1, ADMIN_ID);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(USER_ID), LESSONS);
    }

    @Test
    public void getBetween() throws Exception {
        assertMatch(service.getBetweenDates(
                LocalDate.of(2018, Month.SEPTEMBER, 15),
                LocalDate.of(2018, Month.SEPTEMBER, 15), USER_ID), LESSON_5, LESSON_4, LESSON_3);
    }
}