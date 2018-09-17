package ru.bogdanov.learner;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.bogdanov.learner.model.Role;
import ru.bogdanov.learner.model.User;
import ru.bogdanov.learner.web.user.AdminRestController;

import java.util.Arrays;

/**
 * Denis, 17.09.2018
 */
public class SpringApp {
    public static void main(String[] args) {

        try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");) {

            System.out.println("Bean definition names: " + Arrays.toString(context.getBeanDefinitionNames()));

            AdminRestController adminRestController = context.getBean(AdminRestController.class);
            adminRestController.create(new User(null, "admin", "email@email.com", "admin", Role.ROLE_ADMIN));
        }

    }
}
