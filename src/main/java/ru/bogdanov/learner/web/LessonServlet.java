package ru.bogdanov.learner.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bogdanov.learner.util.LessonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Denis, 16.09.2018
 */
public class LessonServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LessonServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("getAll");
        request.setAttribute("lessons", LessonUtil.getWithGoal(LessonUtil.LESSONS, LessonUtil.DEFAULT_DAILY_GOAL));
        request.getRequestDispatcher("/lessons.jsp").forward(request, response);
    }
}
