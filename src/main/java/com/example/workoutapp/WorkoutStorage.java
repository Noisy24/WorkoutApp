package com.example.workoutapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.LocalDate;
import java.util.*;


public class WorkoutStorage {
    private static final Map<LocalDate, Workout> workouts = new TreeMap<>();
    private static final ObservableList<Workout> workoutList = FXCollections.observableArrayList();

    public static void addWorkout(Workout workout) {
        workouts.put(workout.getDate(), workout);
        refreshList();
        saveToFile();
    }

    public static void removeWorkout(LocalDate date) {
        workouts.remove(date);
        refreshList();
        saveToFile();
    }

    private static final String FILE_NAME = "workouts.dat";

    // Сохранение в файл
    public static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(new TreeMap<>(workouts));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Загрузка из файла
    @SuppressWarnings("unchecked")
    public static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                Map<LocalDate, Workout> loaded = (Map<LocalDate, Workout>) in.readObject();
                workouts.clear();
                workouts.putAll(loaded);
                refreshList();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
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
