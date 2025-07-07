package com.example.testproject;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;

import java.time.LocalDate;

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