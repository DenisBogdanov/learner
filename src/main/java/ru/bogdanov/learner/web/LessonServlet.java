package ru.bogdanov.learner.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.bogdanov.learner.model.Lesson;
import ru.bogdanov.learner.repository.LessonRepository;
import ru.bogdanov.learner.repository.mock.InMemoryLessonRepositoryImpl;
import ru.bogdanov.learner.util.LessonUtil;
import ru.bogdanov.learner.web.lesson.LessonRestController;
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

    private ConfigurableApplicationContext springContext;
    private LessonRestController lessonController;

    @Override
    public void init() throws ServletException {
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        lessonController = springContext.getBean(LessonRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "getAll" : action) {
            case "delete":
                int id = getId(request);
                lessonController.delete(id);
                response.sendRedirect("lessons");
                break;
            case "create":
            case "update":
                final Lesson lesson = "create".equals(action) ?
                        new Lesson(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 30)
                        : lessonController.get(getId(request));
                request.setAttribute("lesson", lesson);
                request.getRequestDispatcher("/lesson-form.jsp").forward(request, response);
                break;
            case "getAll":
            default:
                request.setAttribute("lessons", lessonController.getAll());
                request.getRequestDispatcher("/lessons.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Lesson lesson = new Lesson(LocalDateTime.parse(request.getParameter("startDateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("duration")));

        if (request.getParameter("id").isEmpty()) {
            lessonController.create(lesson);
        } else {
            lessonController.update(lesson, getId(request));
        }
        response.sendRedirect("lessons");
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
