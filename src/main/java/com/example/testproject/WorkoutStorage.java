package com.example.testproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.util.*;


public class WorkoutStorage {
    private static final Map<LocalDate, Workout> workouts = new TreeMap<>();
    private static final ObservableList<Workout> workoutList = FXCollections.observableArrayList();

    public static void addWorkout(Workout workout) {
        workouts.put(workout.getDate(), workout);
        refreshList();
    }

    public static void removeWorkout(LocalDate date) {
        workouts.remove(date);
        refreshList();
    }

    public static Workout getWorkoutByDate(LocalDate date) {
        return workouts.get(date);
    }

    public static Collection<Workout> getAllWorkouts() {
        return workouts.values();
    }

    public static ObservableList<Workout> getObservableList() {
        return workoutList;
    }

    private static void refreshList() {
        workoutList.setAll(workouts.values());
    }

    public static boolean containsDate(LocalDate date) {
        return workouts.containsKey(date);
    }
}
