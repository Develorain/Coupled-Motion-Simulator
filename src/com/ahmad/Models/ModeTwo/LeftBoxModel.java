package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.BoxModel;

public class LeftBoxModel extends BoxModel {
    private double mu;

    public LeftBoxModel(SystemModelTwo systemModelTwo, double mass, double mu) {
        super(systemModelTwo, mass);

        this.mu = mu;
    }

    @Override
    public void calculateCoordinates() {
        // TODO: implement this method
    }

    public double getMu() {
        return mu;
    }
}
