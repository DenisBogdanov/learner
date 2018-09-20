package ru.bogdanov.learner.model;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Denis, 16.09.2018
 */
public class Lesson extends AbstractBaseEntity {

    private LocalDateTime startDateTime;
    private String description;
    private int duration;

    @ManyToOne(fetch = FetchType.LAZY)
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
