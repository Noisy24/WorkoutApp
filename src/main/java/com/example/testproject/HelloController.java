package com.example.testproject;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class HelloController {
    @FXML
    protected TableView<Workout> workoutTable;
    @FXML
    protected TableColumn<Workout, LocalDate> dateColumn;
    @FXML
    protected TableColumn<Workout, String> typeColumn;
    @FXML
    private Button deleteButton;

    @FXML
    public void initialize() {
        // Привязка данных к колонкам
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        // Устанавливаем список тренировок в таблицу
        workoutTable.setItems(WorkoutStorage.getObservableList());

        // Включаем сортировку для каждой колонки
        dateColumn.setSortable(true);
        typeColumn.setSortable(true);

        // Устанавливаем компараторы для каждой колонки
        dateColumn.setComparator(LocalDate::compareTo); // Сортировка по дате
        typeColumn.setComparator(String.CASE_INSENSITIVE_ORDER); // Сортировка по типу (без учёта регистра)

        // Делаем колонки сортируемыми через клик пользователя
        workoutTable.getColumns().setAll(dateColumn, typeColumn);
    }

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
            // Загружаем FXML нового окна
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("change-workout.fxml"));
            Parent root = loader.load();

            // Получаем контроллер
            ChangeWorkout controller = loader.getController();

            // Передаём тренировку в контроллер
            controller.setWorkoutToEdit(selectedWorkout);

            // Создаём новое окно
            Stage stage = new Stage();
            stage.setTitle("Добавить тренировку");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // блокирует главное окно, пока не закроют это
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
    protected void onDeleteButtonClick()
    {
        DeleteWorkout.delete(workoutTable);
    }

}