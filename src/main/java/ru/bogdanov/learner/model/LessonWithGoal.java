package ru.bogdanov.learner.model;

import java.time.LocalDateTime;

/**
 * Denis, 16.09.2018
 */
public class LessonWithGoal {
    private Integer id;
    private final LocalDateTime startDateTime;
    private final String description;
    private final int duration;
    private final boolean goalAchieved;

    public LessonWithGoal(Integer id, LocalDateTime startDateTime, String description, int duration, boolean goalAchieved) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.description = description;
        this.duration = duration;
        this.goalAchieved = goalAchieved;
    }

    public Integer getId() {
        return id;
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

    public boolean isGoalAchieved() {
        return goalAchieved;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LessonWithGoal{");
        sb.append("id=").append(id);
        sb.append(", startDateTime=").append(startDateTime);
        sb.append(", description='").append(description).append('\'');
        sb.append(", duration=").append(duration);
        sb.append(", goalAchieved=").append(goalAchieved);
        sb.append('}');
        return sb.toString();
    }
}