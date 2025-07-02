package com.example.testproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AddWorkout {

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField workoutType;
    @FXML
    private TextField name;
    @FXML
    private TextField sets;
    @FXML
    private TextField reps;
    @FXML
    private TextField weight;
    @FXML
    private TableView<Exercise> exerciseTable;
    @FXML
    private TableColumn<Exercise, String> nameCol;
    @FXML
    private TableColumn<Exercise, Integer> setsCol;
    @FXML
    private TableColumn<Exercise, Integer> repsCol;
    @FXML
    private TableColumn<Exercise, Double> weightCol;

    ObservableList<Exercise> exercises = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        setsCol.setCellValueFactory(new PropertyValueFactory<>("sets"));
        repsCol.setCellValueFactory(new PropertyValueFactory<>("reps"));
        weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));



        exerciseTable.setItems(exercises);
    }
    @FXML
    public void addExercise(ActionEvent actionEvent) {
        exercises.add(new Exercise(name, sets, reps, weight));
    }
    @FXML
    public void delExercise(ActionEvent actionEvent) {
        // Получаем выбранную строку
        Exercise selectedExercise = exerciseTable.getSelectionModel().getSelectedItem();

        // Проверяем, что что-то выбрано
        if (selectedExercise != null) {
            // Удаляем из списка, который привязан к таблице
            exercises.remove(selectedExercise);
        } else {
            // Можно показать предупреждение, если ничего не выбрано
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, выберите упражнение для удаления.");
            alert.showAndWait();
        }
    }
    @FXML
    public void saveWorkout(ActionEvent actionEvent) {
        LocalDate date = datePicker.getValue();
        String type = workoutType.getText();
        List<Exercise> exercisesList = new ArrayList<>(exercises);

        // Сохранение тренировки (здесь можно добавить логику сохранения)
        System.out.println("Сохранена тренировка: " + date + ", " + type + ", " + exercisesList);
    }
    @FXML
    public void back(ActionEvent actionEvent) {

    }
}
