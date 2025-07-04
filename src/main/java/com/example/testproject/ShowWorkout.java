package com.example.testproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class ShowWorkout {
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

        exercisesTable.setEditable(false);

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
}