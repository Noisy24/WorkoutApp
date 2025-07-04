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
        if (table == null || table.getSelectionModel() == null)
        {
            showAlert("Ошибка: таблица не готова");
            return;
        }

        Workout selected = table.getSelectionModel().getSelectedItem();
        if (selected == null)
        {
            showAlert("Выберите тренировку для удаления");
            return;
        }

        if (selected.getDate() == null)
        {
            showAlert("Ошибка: у тренировки нет даты");
            return;
        }

        WorkoutStorage.removeWorkout(selected.getDate());
    }

    private static void showAlert(String message)
    {
        new Alert(Alert.AlertType.WARNING, message).showAndWait();
    }

}