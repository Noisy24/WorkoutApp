package com.example.testproject;

import java.util.*;

public class WorkoutStorage {
    private static final Map<String, Workout> workouts = new TreeMap<>(); // TreeMap — сортировка по дате

    public static void addWorkout(Workout workout) {
        workouts.put(workout.getDate(), workout);
    }

    public static Workout getWorkoutByDate(String date) {
        return workouts.get(date);
    }

    public static Set<String> getAllDates() {
        return workouts.keySet(); // чтобы показать список в меню
    }

    public static Collection<Workout> getAllWorkouts() {
        return workouts.values();
    }




    // fsfs
}
