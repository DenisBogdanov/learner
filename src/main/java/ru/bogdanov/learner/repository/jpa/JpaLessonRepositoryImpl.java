package ru.bogdanov.learner.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdanov.learner.model.Lesson;
import ru.bogdanov.learner.model.User;
import ru.bogdanov.learner.repository.LessonRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Denis, 20.09.2018
 */
@Repository
@Transactional(readOnly = true)
public class JpaLessonRepositoryImpl implements LessonRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Lesson save(Lesson lesson, int userId) {
        if (!lesson.isNew() && get(lesson.getId(), userId) == null) {
            return null;
        }
        lesson.setUser(em.getReference(User.class, userId));
        if (lesson.isNew()) {
            em.persist(lesson);
            return lesson;
        } else {
            return em.merge(lesson);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Lesson.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Lesson get(int id, int userId) {
        Lesson lesson = em.find(Lesson.class, id);
        return lesson != null && lesson.getUser().getId() == userId ? lesson : null;
    }

    @Override
    public List<Lesson> getAll(int userId) {
        return em.createNamedQuery(Lesson.ALL_SORTED, Lesson.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Lesson> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return em.createNamedQuery(Lesson.GET_BETWEEN, Lesson.class)
                .setParameter("userId", userId)
                .setParameter("startDateTime", startDateTime)
                .setParameter("endDateTime", endDateTime)
                .getResultList();
    }
}
