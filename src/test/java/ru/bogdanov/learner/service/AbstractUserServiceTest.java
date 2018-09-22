package ru.bogdanov.learner.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.bogdanov.learner.exception.NotFoundException;
import ru.bogdanov.learner.model.Role;
import ru.bogdanov.learner.model.User;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ru.bogdanov.learner.UserTestData.*;

/**
 * Denis, 19.09.2018
 */
public abstract class AbstractUserServiceTest extends AbstractServiceTest {
    private static final int VACANT_ID = 1;

    @Autowired
    private UserService service;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
    }

    @Test
    public void create() {
        User newUser = new User(null, "newUser", "a@b.c", "newPassword", 90, false, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, newUser, USER);
    }

    @Test
    public void delete() {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(VACANT_ID);
    }

    @Test
    public void get() {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(VACANT_ID);
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail("user@gmail.com");
        assertMatch(user, USER);
    }

    @Test
    public void update() {
        User updated = new User(USER);
        updated.setName("updated");
        updated.setDailyGoal(150);
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void getAll() {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }
}