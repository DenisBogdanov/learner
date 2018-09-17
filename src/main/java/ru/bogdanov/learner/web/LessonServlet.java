package ru.bogdanov.learner.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bogdanov.learner.model.Lesson;
import ru.bogdanov.learner.repository.LessonRepository;
import ru.bogdanov.learner.repository.mock.InMemoryLessonRepositoryImpl;
import ru.bogdanov.learner.util.LessonUtil;
import ru.bogdanov.learner.web.user.SecurityUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Denis, 16.09.2018
 */
public class LessonServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LessonServlet.class);

    private LessonRepository repository;

    @Override
    public void init() throws ServletException {
        repository = new InMemoryLessonRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "getAll" : action) {
            case "delete":
                int id = getId(request);
                LOGGER.info("Delete {}", id);
                repository.delete(id, SecurityUtil.authUserId());
                response.sendRedirect("lessons");
                break;
            case "create":
            case "update":
                final Lesson lesson = "create".equals(action) ?
                        new Lesson(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 30)
                        : repository.get(getId(request), SecurityUtil.authUserId());
                request.setAttribute("lesson", lesson);
                request.getRequestDispatcher("/lesson-form.jsp").forward(request, response);
                break;
            case "getAll":
                LOGGER.info("getAll");
                request.setAttribute("lessons", LessonUtil.getWithGoal(repository.getAll(SecurityUtil.authUserId()), LessonUtil.DEFAULT_DAILY_GOAL));
                request.getRequestDispatcher("/lessons.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Lesson lesson = new Lesson(id.isEmpty() ? null : Integer.parseInt(id),
                LocalDateTime.parse(request.getParameter("startDateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("duration")));

        LOGGER.info(lesson.isNew() ? "Create {}" : "Update {}", lesson);
        repository.save(lesson, SecurityUtil.authUserId());
        response.sendRedirect("lessons");
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
