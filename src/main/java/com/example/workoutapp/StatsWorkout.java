package com.example.workoutapp;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.*;

public class StatsWorkout {
    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private Label maxVolumeLabel;

    @FXML
    private Label mostFrequentExerciseLabel;

    @FXML
    private Label heaviestLiftLabel;

    @FXML
    public void initialize() {
        Collection<Workout> workouts = WorkoutStorage.getAllWorkouts();

        if (workouts == null || workouts.isEmpty()) {
            maxVolumeLabel.setText("Нет данных");
            mostFrequentExerciseLabel.setText("Нет данных");
            heaviestLiftLabel.setText("Нет данных");
            return;
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Объём тренировок");

        // Метрики
        double maxVolume = 0;
        String maxVolumeDate = "-";

        Map<String, Integer> exerciseFrequency = new HashMap<>();
        String heaviestExercise = "-";
        double heaviestWeight = 0;

        for (Workout workout : workouts) {
            double workoutVolume = 0;

            for (Exercise ex : workout.getExercises()) {
                double exVolume = ex.getSets() * ex.getReps() * ex.getWeight();
                workoutVolume += exVolume;

                exerciseFrequency.merge(ex.getName(), 1, Integer::sum);

                if (ex.getWeight() > heaviestWeight) {
                    heaviestWeight = ex.getWeight();
                    heaviestExercise = ex.getName();
                }
            }

            series.getData().add(new XYChart.Data<>(workout.getDate().toString(), workoutVolume));

            if (workoutVolume > maxVolume) {
                maxVolume = workoutVolume;
                maxVolumeDate = workout.getDate().toString();
            }
        }

        barChart.getData().add(series);

        String mostFrequent = exerciseFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> e.getKey() + " (" + e.getValue() + " раз)")
                .orElse("Нет данных");

        maxVolumeLabel.setText(maxVolumeDate + " — " + (int) maxVolume + " кг");
        mostFrequentExerciseLabel.setText(mostFrequent);
        heaviestLiftLabel.setText(heaviestExercise + " — " + (int) heaviestWeight + " кг");
    }
}
