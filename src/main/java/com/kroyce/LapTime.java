package com.kroyce;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class LapTime {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Duration duration;

    public LapTime(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;

        this.duration = Duration.between(startTime, endTime);
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "LapTime{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LapTime lapTime = (LapTime) o;
        return startTime.equals(lapTime.startTime) &&
                endTime.equals(lapTime.endTime) &&
                duration.equals(lapTime.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, duration);
    }
}
