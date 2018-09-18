package ru.bogdanov.learner;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.bogdanov.learner.model.Role;
import ru.bogdanov.learner.model.User;
import ru.bogdanov.learner.to.LessonWithGoal;
import ru.bogdanov.learner.web.lesson.LessonRestController;
import ru.bogdanov.learner.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * Denis, 17.09.2018
 */
public class SpringApp {
    public static void main(String[] args) {

        try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");) {

            System.out.println("Bean definition names: " + Arrays.toString(context.getBeanDefinitionNames()));

            AdminRestController adminRestController = context.getBean(AdminRestController.class);
            adminRestController.create(new User(null, "admin", "email@email.com", "admin", Role.ROLE_ADMIN));

            System.out.println("==========");

            LessonRestController lessonRestController = context.getBean(LessonRestController.class);
            List<LessonWithGoal> filteredLessonsWithGoals =
                    lessonRestController.getBetween(
                            LocalDate.of(2018, Month.SEPTEMBER, 14), LocalTime.of(7, 0),
                            LocalDate.of(2018, Month.SEPTEMBER, 16), LocalTime.of(13, 0));

            filteredLessonsWithGoals.forEach(System.out::println);

        }

    }
}
