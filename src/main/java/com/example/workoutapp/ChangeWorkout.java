package com.example.workoutapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class ChangeWorkout {
    @FXML
    private TableView<Exercise> exercisesTable;

    @FXML
    private TableColumn<Exercise, String> nameColumn;

    @FXML
    private TableColumn<Exercise, Integer> setsColumn;

    @FXML
    private TableColumn<Exercise, Integer> repsColumn;

    @FXML
    private TableColumn<Exercise, Double> weightColumn;

    private Workout originalWorkout;
    private final ObservableList<Exercise> editableExercises = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        setsColumn.setCellValueFactory(new PropertyValueFactory<>("sets"));
        repsColumn.setCellValueFactory(new PropertyValueFactory<>("reps"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        // Сделать таблицу и колонки редактируемыми
        exercisesTable.setEditable(true);

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        setsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        repsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        weightColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));


        // Обработка редактирования
        nameColumn.setOnEditCommit(event -> {
            Exercise exercise = event.getRowValue();
            exercise.setName(event.getNewValue());
        });

        setsColumn.setOnEditCommit(event -> {
            Exercise exercise = event.getRowValue();
            exercise.setSets(event.getNewValue());
        });

        repsColumn.setOnEditCommit(event -> {
            Exercise exercise = event.getRowValue();
            exercise.setReps(event.getNewValue());
        });

        weightColumn.setOnEditCommit(event -> {
            Exercise exercise = event.getRowValue();
            exercise.setWeight(event.getNewValue());
        });


        exercisesTable.setItems(editableExercises); // пустой на старте
    }

    public void setWorkoutToEdit(Workout workout) {
        this.originalWorkout = workout;

        if (workout != null && workout.getExercises() != null) {
            editableExercises.setAll(workout.getExercises());
        }
    }


    @FXML
    private void handleCancel() {
        Stage stage = (Stage) exercisesTable.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleSave() {
        if (originalWorkout == null) {
            showAlert("Не выбрана тренировка для редактирования.");
            return;
        }

        // Заменим упражнения на отредактированные
        originalWorkout.getExercises().clear();
        originalWorkout.getExercises().addAll(editableExercises);

        // Обновим список в WorkoutStorage
        WorkoutStorage.getObservableList().setAll(WorkoutStorage.getAllWorkouts());
        WorkoutStorage.saveToFile();

        // Закрываем окно
        Stage stage = (Stage) exercisesTable.getScene().getWindow();
        stage.close();
    }
}
