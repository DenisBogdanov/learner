package ru.bogdanov.learner.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bogdanov.learner.UserTestData;
import ru.bogdanov.learner.exception.NotFoundException;
import ru.bogdanov.learner.model.User;
import ru.bogdanov.learner.repository.mock.InMemoryUserRepositoryImpl;
import ru.bogdanov.learner.web.user.AdminRestController;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static ru.bogdanov.learner.UserTestData.ADMIN;

/**
 * Denis, 18.09.2018
 */
@ContextConfiguration("classpath:spring/spring-app.xml")
@RunWith(SpringRunner.class)
public class InMemoryAdminRestControllerSpringTest {

    @Autowired
    private AdminRestController controller;

    @Autowired
    private InMemoryUserRepositoryImpl repository;

    @Before
    public void setUp() throws Exception {
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
