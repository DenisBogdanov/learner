package ru.bogdanov.learner.to;

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
        return "LessonWithGoal{" + "id=" + id +
                ", startDateTime=" + startDateTime +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", goalAchieved=" + goalAchieved +
                '}';
    }
}
