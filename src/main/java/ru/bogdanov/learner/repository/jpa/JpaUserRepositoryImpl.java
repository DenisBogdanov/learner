package ru.bogdanov.learner.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.bogdanov.learner.model.User;
import ru.bogdanov.learner.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Denis, 20.09.2018
 */
@Repository
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    public boolean delete(int id) {
        Query query = em.createQuery("DELETE FROM User u WHERE u.id=:id");
        return query.setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
