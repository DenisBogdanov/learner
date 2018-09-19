package ru.bogdanov.learner.web;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.bogdanov.learner.UserTestData;
import ru.bogdanov.learner.exception.NotFoundException;
import ru.bogdanov.learner.model.User;
import ru.bogdanov.learner.repository.mock.InMemoryUserRepositoryImpl;
import ru.bogdanov.learner.web.user.AdminRestController;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static ru.bogdanov.learner.UserTestData.ADMIN;

/**
 * Denis, 18.09.2018
 */
public class InMemoryAdminRestControllerTest {
    private static ConfigurableApplicationContext appContext;
    private static AdminRestController controller;

    @BeforeClass
    public static void beforeClass() {
        appContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("\n" + Arrays.toString(appContext.getBeanDefinitionNames()) + "\n");
        controller = appContext.getBean(AdminRestController.class);
    }

    @AfterClass
    public static void afterClass() {
        appContext.close();
    }

    @Before
    public void setUp() throws Exception {
        InMemoryUserRepositoryImpl repository = appContext.getBean(InMemoryUserRepositoryImpl.class);
        repository.init();
    }

    @Test
    public void delete() throws Exception {
        controller.delete(UserTestData.USER_ID);
        Collection<User> users = controller.getAll();
        assertEquals(users.size(), 1);
        assertEquals(users.iterator().next(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() throws Exception {
        controller.delete(10);
    }
}
