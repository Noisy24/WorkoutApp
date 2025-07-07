package com.example.workoutapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;
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

        nameColumn.setCellFactory(column -> createNameCell());
        setsColumn.setCellFactory(column -> createSetsCell());
        repsColumn.setCellFactory(column -> createRepsCell());
        weightColumn.setCellFactory(column -> createWeightCell());


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


    // Метод для показа ошибок
    private void showInputError(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка ввода");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Метод для имени (только буквы, пробелы и дефис)
    private TableCell<Exercise, String> createNameCell() {
        TextFieldTableCell<Exercise, String> cell = new TextFieldTableCell<>(new DefaultStringConverter()) {
            @Override
            public void startEdit() {
                super.startEdit();
                TextField textField = (TextField) getGraphic();
                textField.setTextFormatter(new TextFormatter<>(change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("[a-zA-Zа-яА-Я\\s-]*")) { // Только буквы, пробелы и дефис
                        return change;
                    }
                    return null;
                }));
            }

            @Override
            public void commitEdit(String newValue) {
                if (newValue == null || newValue.trim().isEmpty()) {
                    showInputError("Название не может быть пустым");
                    cancelEdit();
                }
                else if (newValue.length() > 30) {
                    showInputError("Максимум 30 символов");
                    cancelEdit();
                }
                else {
                    super.commitEdit(newValue.trim());
                }
            }
        };
        return cell;
    }


    // Метод для подходов (1-20)
    private TableCell<Exercise, Integer> createSetsCell() {
        TextFieldTableCell<Exercise, Integer> cell = new TextFieldTableCell<>(new IntegerStringConverter()) {
            @Override
            public void startEdit() {
                super.startEdit();
                TextField textField = (TextField) getGraphic();
                textField.setTextFormatter(new TextFormatter<>(change -> {
                    if (change.getControlNewText().matches("\\d*")) { // Только цифры
                        return change;
                    }
                    return null;
                }));
            }

            @Override
            public void commitEdit(Integer newValue) {
                if (newValue == null) {
                    showInputError("Введите число подходов");
                    cancelEdit();
                }
                else if (newValue < 1) {
                    showInputError("Минимум 1 подход");
                    cancelEdit();
                }
                else if (newValue > 20) {
                    showInputError("Максимум 20 подходов");
                    cancelEdit();
                }
                else {
                    super.commitEdit(newValue);
                }
            }
        };
        return cell;
    }

    // Метод для повторений (1-100)
    private TableCell<Exercise, Integer> createRepsCell() {
        TextFieldTableCell<Exercise, Integer> cell = new TextFieldTableCell<>(new IntegerStringConverter()) {
            @Override
            public void startEdit() {
                super.startEdit();
                TextField textField = (TextField) getGraphic();
                textField.setTextFormatter(new TextFormatter<>(change -> {
                    if (change.getControlNewText().matches("\\d*")) { // Только цифры
                        return change;
                    }
                    return null;
                }));
            }

            @Override
            public void commitEdit(Integer newValue) {
                if (newValue == null) {
                    showInputError("Введите число повторений");
                    cancelEdit();
                }
                else if (newValue < 1) {
                    showInputError("Минимум 1 повторение");
                    cancelEdit();
                }
                else if (newValue > 100) {
                    showInputError("Максимум 100 повторений");
                    cancelEdit();
                }
                else {
                    super.commitEdit(newValue);
                }
            }
        };
        return cell;
    }

    // Метод для веса (0-1000 кг)
    private TableCell<Exercise, Double> createWeightCell() {
        TextFieldTableCell<Exercise, Double> cell = new TextFieldTableCell<>(new DoubleStringConverter()) {
            @Override
            public void startEdit() {
                super.startEdit();
                TextField textField = (TextField) getGraphic();
                textField.setTextFormatter(new TextFormatter<>(change -> {
                    String newText = change.getControlNewText();
                    if (newText.isEmpty() || newText.matches("\\d*\\.?\\d*")) { // Разрешаем только числа и точку
                        return change;
                    }
                    return null; // Блокируем ввод
                }));
            }

            @Override
            public void commitEdit(Double newValue) {
                if (newValue == null) {
                    showInputError("Введите вес");
                    cancelEdit();
                }
                else if (newValue < 0) {
                    showInputError("Вес не может быть отрицательным");
                    cancelEdit();
                }
                else if (newValue > 1000) {
                    showInputError("Слишком большой вес (макс. 1000 кг)");
                    cancelEdit();
                }
                else {
                    super.commitEdit(newValue);
                }
            }
        };
        return cell;
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
