package com.example.testproject;

import javafx.scene.control.TextField;

import java.io.Serializable;

public class Exercise implements Serializable {
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

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public int getSets() { return sets; }
    public int getReps() { return reps; }
    public double getWeight() { return weight; }

}
