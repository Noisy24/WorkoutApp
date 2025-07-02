package com.example.testproject;

import java.time.LocalDate;
import java.util.*;

public class WorkoutStorage {
    private static final Map<LocalDate, Workout> workouts = new TreeMap<>();

    public static void addWorkout(Workout workout) {
        workouts.put(workout.getDate(), workout);
    }

    public static Workout getWorkoutByDate(LocalDate date) {
        return workouts.get(date);
    }

    public static Set<LocalDate> getAllDates() {
        return workouts.keySet();
    }

    public static Collection<Workout> getAllWorkouts() {
        return workouts.values();
    }

    public static boolean containsDate(LocalDate date) {
        return workouts.containsKey(date);
    }
}
