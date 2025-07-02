package com.example.testproject;

import javafx.scene.control.TextField;

public class Exercise {
    private String name;
    private int sets;
    private int reps;
    private double weight;

    public Exercise(String name, int sets, int reps, double weight) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    public Exercise(TextField name, TextField sets, TextField reps, TextField weight) {
        this.name = name.getText();
        this.sets = Integer.parseInt(sets.getText());
        this.reps = Integer.parseInt(reps.getText());
        this.weight = Double.parseDouble(weight.getText());

    }

    public String getName() { return name; }
    public int getSets() { return sets; }
    public int getReps() { return reps; }
    public double getWeight() { return weight; }
}
