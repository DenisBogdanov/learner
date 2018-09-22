package ru.bogdanov.learner.service;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.bogdanov.learner.ActiveDbProfileResolver;
import ru.bogdanov.learner.exception.NotFoundException;
import ru.bogdanov.learner.model.Lesson;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.TimeUnit;

import static ru.bogdanov.learner.LessonTestData.*;
import static ru.bogdanov.learner.UserTestData.ADMIN_ID;
import static ru.bogdanov.learner.UserTestData.USER_ID;

/**
 * Denis, 19.09.2018
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public class LessonServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger("result");

    private static StringBuilder results = new StringBuilder();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("\n%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result);
            LOGGER.info(result + " ms\n");
        }
    };

    @Autowired
    private LessonService service;

    static {
        SLF4JBridgeHandler.install();
    }

    @AfterClass
    public static void printResult() {
        LOGGER.info("\n=================================" +
                "\nTest                 Duration, ms" +
                "\n=================================" +
                results +
                "\n=================================");
    }

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