package com.example.testproject;

import java.time.LocalDate;
import java.util.List;

public class Workout {
    private LocalDate date;
    private String type;
    private List<Exercise> exercises;

    public Workout(LocalDate date, String type, List<Exercise> exercises) {
        this.date = date;
        this.type = type;
        this.exercises = exercises;
    }

    public LocalDate getDate() { return date; }
    public String getType() { return type; }
    public List<Exercise> getExercises() { return exercises; }
}