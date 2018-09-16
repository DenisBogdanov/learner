package ru.bogdanov.learner.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Denis, 16.09.2018
 */
public class Lesson {
    private Integer id;
    private final LocalDateTime startDateTime;
    private final String description;
    private final int duration;

    public Lesson(LocalDateTime startDateTime, String description, int duration) {
        this(null, startDateTime, description, duration);
    }

    public Lesson(Integer id, LocalDateTime startDateTime, String description, int duration) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.description = description;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDate getDate() {
        return startDateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return startDateTime.toLocalTime();
    }

    public boolean isNew() {
        return id == null;
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
