package ru.bogdanov.learner.model;

import java.time.LocalDateTime;

/**
 * Denis, 16.09.2018
 */
public class LessonWithGoal {
    private final LocalDateTime startDateTime;
    private final String description;
    private final int duration;
    private final boolean goalAchieved;

    public LessonWithGoal(LocalDateTime startDateTime, String description, int duration, boolean goalAchieved) {
        this.startDateTime = startDateTime;
        this.description = description;
        this.duration = duration;
        this.goalAchieved = goalAchieved;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LessonWithGoal{");
        sb.append("startDateTime=").append(startDateTime);
        sb.append(", description='").append(description).append('\'');
        sb.append(", duration=").append(duration);
        sb.append(", goalAchieved=").append(goalAchieved);
        sb.append('}');
        return sb.toString();
    }
}
