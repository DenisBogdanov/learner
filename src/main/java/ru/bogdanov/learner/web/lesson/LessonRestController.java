package ru.bogdanov.learner.web.lesson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.bogdanov.learner.model.Lesson;
import ru.bogdanov.learner.service.LessonService;
import ru.bogdanov.learner.to.LessonWithGoal;
import ru.bogdanov.learner.util.DateTimeUtil;
import ru.bogdanov.learner.util.LessonUtil;
import ru.bogdanov.learner.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.bogdanov.learner.util.ValidationUtil.assureIdConsistent;
import static ru.bogdanov.learner.util.ValidationUtil.checkNew;

/**
 * Denis, 16.09.2018
 */
@Controller
public class LessonRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LessonRestController.class);

    private final LessonService service;

    @Autowired
    public LessonRestController(LessonService service) {
        this.service = service;
    }

    public Lesson get(int id) {
        int userId = SecurityUtil.authUserId();
        LOGGER.info("get lesson {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        LOGGER.info("delete lesson {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<LessonWithGoal> getAll() {
        int userId = SecurityUtil.authUserId();
        LOGGER.info("getAll for user {}", userId);
        return LessonUtil.getWithGoal(service.getAll(userId), SecurityUtil.authUserDailyGoal());
    }

    public Lesson create(Lesson lesson) {
        int userId = SecurityUtil.authUserId();
        checkNew(lesson);
        LOGGER.info("create {} for user {}", lesson, userId);
        return service.create(lesson, userId);
    }

    public void update(Lesson lesson, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(lesson, id);
        LOGGER.info("update {} for user {}", lesson, userId);
        service.update(lesson, userId);
    }

    public List<LessonWithGoal> getBetween(LocalDate startDate, LocalTime startTime,
                                           LocalDate endDate, LocalTime endTime) {

        int userId = SecurityUtil.authUserId();
        LOGGER.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);

        List<Lesson> lessonsFilteredByDates = service.getBetweenDates(
                startDate != null ? startDate : DateTimeUtil.MIN_DATE,
                endDate != null ? endDate : DateTimeUtil.MAX_DATE, userId);

        return LessonUtil.getFilteredByTimeWithGoal(lessonsFilteredByDates,
                SecurityUtil.authUserDailyGoal(),
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX
        );
    }
}
