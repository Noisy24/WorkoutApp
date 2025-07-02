package com.example.testproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    protected void handleAddWorkoutButton() {
        try {
            // Загружаем FXML нового окна
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("add-workout.fxml"));
            Parent root = loader.load();

            // Создаём новое окно
            Stage stage = new Stage();
            stage.setTitle("Добавить тренировку");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // блокирует главное окно, пока не закроют это
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleChangeWorkoutButton() {
        try {
            // Загружаем FXML нового окна
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("change-workout.fxml"));
            Parent root = loader.load();

            // Создаём новое окно
            Stage stage = new Stage();
            stage.setTitle("Добавить тренировку");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // блокирует главное окно, пока не закроют это
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleStatsWorkoutButton() {
        try {
            // Загружаем FXML нового окна
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("change-workout.fxml"));
            Parent root = loader.load();

            // Создаём новое окно
            Stage stage = new Stage();
            stage.setTitle("Добавить тренировку");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // блокирует главное окно, пока не закроют это
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}