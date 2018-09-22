package ru.bogdanov.learner.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Denis, 16.09.2018
 */
@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Lesson.ALL_SORTED, query = "SELECT l FROM Lesson l WHERE l.user.id=:userId ORDER BY l.startDateTime DESC"),
        @NamedQuery(name = Lesson.DELETE, query = "DELETE FROM Lesson l WHERE l.id=:id AND l.user.id=:userId"),
        @NamedQuery(name = Lesson.GET_BETWEEN, query = "" +
                "SELECT l FROM Lesson l " +
                "WHERE l.user.id=:userId AND l.startDateTime BETWEEN :startDateTime AND :endDateTime " +
                "ORDER BY l.startDateTime DESC"),
})
@Entity
@Table(name = "lessons", uniqueConstraints = {@UniqueConstraint(
        columnNames = {"user_id", "start_date_time"}, name = "lessons_unique_user_datetime_idx")})
public class Lesson extends AbstractBaseEntity {

    public static final String ALL_SORTED = "Lesson.getAll";
    public static final String DELETE = "Lesson.delete";
    public static final String GET_BETWEEN = "Lesson.getBetween";

    @Column(name = "start_date_time", nullable = false)
    @NotNull
    private LocalDateTime startDateTime;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String description;

    @Column(name = "duration", nullable = false)
    @Range(min = 10, max = 1000)
    private int duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Lesson() {
    }

    public Lesson(LocalDateTime startDateTime, String description, int duration) {
        this(null, startDateTime, description, duration);
    }

    public Lesson(Integer id, LocalDateTime startDateTime, String description, int duration) {
        super(id);
        this.startDateTime = startDateTime;
        this.description = description;
        this.duration = duration;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getDate() {
        return startDateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return startDateTime.toLocalTime();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Lesson{" + "id=" + id +
                ", startDateTime=" + startDateTime +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                '}';
    }
}
