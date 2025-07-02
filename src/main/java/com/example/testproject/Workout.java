package com.example.testproject;


public class Workout {
    private String date;
    private String exercise;
    private int sets;
    private int reps;
    private double weight;

    public Workout(String date, String exercise, int sets, int reps, double weight) {
        this.date = date;
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    // Геттеры
    public String getDate() { return date; }
    public String getExercise() { return exercise; }
    public int getSets() { return sets; }
    public int getReps() { return reps; }
    public double getWeight() { return weight; }
}

