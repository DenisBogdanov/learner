package ru.bogdanov.learner.repository.mock;

import org.springframework.stereotype.Repository;
import ru.bogdanov.learner.model.User;
import ru.bogdanov.learner.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Denis, 17.09.2018
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger id = new AtomicInteger(0);

    @Override
    public List<User> getAll() {
        return repository.values().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(id.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }
        return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        return repository.values().stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst()
                .orElse(null);
    }
}