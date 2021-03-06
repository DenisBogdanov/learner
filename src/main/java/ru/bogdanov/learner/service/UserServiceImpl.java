package ru.bogdanov.learner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.bogdanov.learner.exception.NotFoundException;
import ru.bogdanov.learner.model.User;
import ru.bogdanov.learner.repository.UserRepository;

import java.util.List;

import static ru.bogdanov.learner.util.ValidationUtil.checkNotFound;
import static ru.bogdanov.learner.util.ValidationUtil.checkNotFoundWithId;

/**
 * Denis, 16.09.2018
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User create(User user) {
        Assert.notNull(user, "user shouldn't be null");
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
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
        Assert.notNull(email, "email shouldn't be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(User user) {
        Assert.notNull(user, "user shouldn't be null");
        checkNotFoundWithId(repository.save(user), user.getId());
    }
}
