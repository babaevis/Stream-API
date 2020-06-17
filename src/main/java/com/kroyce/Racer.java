package com.kroyce;

import java.util.Objects;

public class Racer {
    private final String name;
    private final String team;
    private final String abbreviation;
    private final LapTime lapTime;

    public Racer(String name, String team, String abbreviation, LapTime lapTime) {
        this.name = name;
        this.team = team;
        this.abbreviation = abbreviation;
        this.lapTime = lapTime;
    }

    public LapTime getLapTime() {
        return lapTime;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "Racer{" +
                "name='" + name + '\'' +
                ", team='" + team + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", lapTime=" + lapTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Racer racer = (Racer) o;
        return name.equals(racer.name) &&
                team.equals(racer.team) &&
                abbreviation.equals(racer.abbreviation) &&
                lapTime.equals(racer.lapTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, team, abbreviation, lapTime);
    }
}
