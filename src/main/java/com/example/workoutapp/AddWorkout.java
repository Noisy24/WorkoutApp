package com.example.workoutapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TextFormatter;

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

        // Запрещаем редактирование текста DatePicker
        datePicker.setEditable(false);
        datePicker.getEditor().setEditable(false);

        // Разрешаем только буквы и пробелы в полях "name" и "workoutType"
        name.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("[a-zA-Zа-яА-Я\\s]*") ? change : null));
        workoutType.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("[a-zA-Zа-яА-Я\\s]*") ? change : null));

        // Ограничиваем ввод только числами
        sets.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null));
        reps.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null));
        weight.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*(\\.\\d*)?") ? change : null));
        weight.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused && !weight.getText().isEmpty()) {
                try {
                    double value = Double.parseDouble(weight.getText());
                    if (value > 1000.0) {
                        weight.setText("1000");
                    }
                } catch (NumberFormatException ignored) {}
            }
        });
    }

    @FXML
    public void addExercise(ActionEvent actionEvent) {
        exercises.add(new Exercise(name, sets, reps, weight));
        name.clear();
        sets.clear();
        reps.clear();
        weight.clear();
        datePicker.setDisable(true);
        workoutType.setDisable(true);
    }

    @FXML
    public void delExercise(ActionEvent actionEvent) { // выбор, проверка и удаление нужной строчки
        Exercise selectedExercise = exerciseTable.getSelectionModel().getSelectedItem();

        if (selectedExercise != null) {

            exercises.remove(selectedExercise);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, выберите упражнение для удаления.");
            alert.showAndWait();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void saveWorkout(ActionEvent actionEvent) {
        LocalDate date = datePicker.getValue();
        String type = workoutType.getText();
        List<Exercise> exercisesList = new ArrayList<>(exercises);

        if (date == null || type.isBlank() || exercisesList.isEmpty()) {
            showAlert("Пожалуйста, заполните все поля и добавьте хотя бы одно упражнение.");
            return;
        }

        if (WorkoutStorage.containsDate(date)) {
            showAlert("Тренировка с этой датой уже существует.");
            return;
        }

        Workout workout = new Workout(date, type, exercisesList);
        WorkoutStorage.addWorkout(workout);

        Stage stage = (Stage) datePicker.getScene().getWindow();
        stage.close();

        System.out.println("Сохранена тренировка: " + date + ", " + type);
    }

}
