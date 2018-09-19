package ru.bogdanov.learner.repository;

import ru.bogdanov.learner.model.User;

import java.util.List;

/**
 * Denis, 16.09.2018
 */
public interface UserRepository {

    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}
