package ru.bogdanov.learner.service;

import ru.bogdanov.learner.exception.NotFoundException;
import ru.bogdanov.learner.model.User;
import ru.bogdanov.learner.repository.UserRepository;

import java.util.List;

import static ru.bogdanov.learner.util.ValidationUtil.checkNotFound;
import static ru.bogdanov.learner.util.ValidationUtil.checkNotFoundWithId;

/**
 * Denis, 16.09.2018
 */
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(User user) {
        checkNotFoundWithId(repository.save(user), user.getId());
    }
}
