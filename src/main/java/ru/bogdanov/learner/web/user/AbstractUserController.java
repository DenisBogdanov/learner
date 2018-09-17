package ru.bogdanov.learner.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bogdanov.learner.model.User;
import ru.bogdanov.learner.service.UserService;

import java.util.List;

import static ru.bogdanov.learner.util.ValidationUtil.assureIdConsistent;
import static ru.bogdanov.learner.util.ValidationUtil.checkNew;

/**
 * Denis, 16.09.2018
 */
public abstract class AbstractUserController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractUserController.class);

    @Autowired
    private UserService service;

    public List<User> getAll() {
        LOGGER.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        LOGGER.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        LOGGER.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        LOGGER.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        LOGGER.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }

    public User getByEmail(String email) {
        LOGGER.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
