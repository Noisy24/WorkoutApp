module com.example.testproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.workoutapp to javafx.fxml;
    exports com.example.workoutapp;
}