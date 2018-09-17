package ru.bogdanov.learner.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.bogdanov.learner.model.User;
import ru.bogdanov.learner.repository.UserRepository;

import java.util.Collections;
import java.util.List;

/**
 * Denis, 17.09.2018
 */
@Repository
public class MockUserRepositoryImpl implements UserRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(MockUserRepositoryImpl.class);

    @Override
    public List<User> getAll() {
        LOGGER.info("getAll");
        return Collections.emptyList();
    }

    @Override
    public User save(User user) {
        LOGGER.info("save {}", user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        LOGGER.info("delete {}", id);
        return true;
    }

    @Override
    public User get(int id) {
        LOGGER.info("get {}", id);
        return null;
    }

    @Override
    public User getByEmail(String email) {
        LOGGER.info("getByEmail {}", email);
        return null;
    }
}
