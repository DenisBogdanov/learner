package ru.bogdanov.learner.service;

import ru.bogdanov.learner.exception.NotFoundException;
import ru.bogdanov.learner.model.User;

import java.util.List;

/**
 * Denis, 16.09.2018
 */
public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    List<User> getAll();
}
