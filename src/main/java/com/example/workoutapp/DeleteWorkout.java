package com.example.workoutapp;

import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

public class DeleteWorkout
{
    public static void delete(TableView<Workout> table) {
        Workout selected = table.getSelectionModel().getSelectedItem();
        if (selected == null)
        {
            showAlert("Выберите тренировку для удаления");
            return;
        }
        WorkoutStorage.removeWorkout(selected.getDate());
    }

    private static void showAlert(String message)
    {
        new Alert(Alert.AlertType.WARNING, message).showAndWait();
    }

}