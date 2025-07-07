package com.example.workoutapp;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class MainController {
    @FXML
    protected TableView<Workout> workoutTable;
    @FXML
    protected TableColumn<Workout, LocalDate> dateColumn;
    @FXML
    protected TableColumn<Workout, String> typeColumn;

    @FXML
    public void initialize() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        workoutTable.setItems(WorkoutStorage.getObservableList());

        dateColumn.setSortable(true);
        typeColumn.setSortable(true);

        // Устанавливаем компараторы для каждой колонки
        dateColumn.setComparator(LocalDate::compareTo); // Сортировка по дате
        typeColumn.setComparator(String.CASE_INSENSITIVE_ORDER); // Сортировка по типу (без учёта регистра)

        workoutTable.getColumns().setAll(dateColumn, typeColumn);
    }

    @FXML
    protected void handleAddWorkoutButton() {
        try {
            // Загружаем FXML нового окна
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("add-workout.fxml"));
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
        Workout selectedWorkout = workoutTable.getSelectionModel().getSelectedItem(); // TableView с тренировками
        if (selectedWorkout == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, выберите тренировку для редактирования.");
            alert.showAndWait();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("change-workout.fxml"));
            Parent root = loader.load();

            ChangeWorkout controller = loader.getController();

            controller.setWorkoutToEdit(selectedWorkout);

            Stage stage = new Stage();
            stage.setTitle("Изменить тренировку");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            // После закрытия окна обновляем таблицу
            workoutTable.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleStatsWorkoutButton() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("stats-workout.fxml"));
            Parent root = loader.load();


            Stage stage = new Stage();
            stage.setTitle("Статистика тренировок");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleShowWorkoutButton() {
        Workout selectedWorkout = workoutTable.getSelectionModel().getSelectedItem(); // TableView с тренировками
        if (selectedWorkout == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, выберите тренировку для редактирования.");
            alert.showAndWait();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("show-workout.fxml"));
            Parent root = loader.load();

            ShowWorkout controller = loader.getController();

            controller.setWorkoutToEdit(selectedWorkout);

            Stage stage = new Stage();
            stage.setTitle("Просмотр тренировку");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onDeleteButtonClick()
    {
        DeleteWorkout.delete(workoutTable);
    }

}