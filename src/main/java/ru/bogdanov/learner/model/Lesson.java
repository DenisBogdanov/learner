package ru.bogdanov.learner.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Denis, 16.09.2018
 */
public class Lesson {
    private final LocalDateTime startDateTime;
    private final String description;
    private final int duration;

    public Lesson(LocalDateTime startDateTime, String description, int duration) {
        this.startDateTime = startDateTime;
        this.description = description;
        this.duration = duration;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Lesson{");
        sb.append("startDateTime=").append(startDateTime);
        sb.append(", description='").append(description).append('\'');
        sb.append(", duration=").append(duration);
        sb.append('}');
        return sb.toString();
    }
}
