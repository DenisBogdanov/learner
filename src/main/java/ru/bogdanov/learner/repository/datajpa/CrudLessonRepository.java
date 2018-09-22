package ru.bogdanov.learner.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdanov.learner.model.Lesson;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Denis, 22.09.2018
 */
@Transactional(readOnly = true)
public interface CrudLessonRepository extends JpaRepository<Lesson, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Lesson l WHERE l.id=:id AND l.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    Lesson save(Lesson lesson);

    @Query("SELECT l FROM Lesson l WHERE l.user.id=:userId ORDER BY l.startDateTime DESC")
    List<Lesson> getAll(@Param("userId") int userId);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT l FROM Lesson l WHERE l.user.id=:userId AND l.startDateTime BETWEEN :startDateTime AND :endDateTime ORDER BY l.startDateTime DESC")
    List<Lesson> getBetween(@Param("startDateTime") LocalDateTime startDateTime,
                            @Param("endDateTime") LocalDateTime endDateTime,
                            @Param("userId") int userId);

}
