package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.BoxModel;

public class MiddleBoxModel extends BoxModel {
    private SystemModelModeTwo systemModelModeTwo;
    private double mu;

    public MiddleBoxModel(SystemModelModeTwo systemModelModeTwo, double mass, double mu) {
        super(mass);

        this.systemModelModeTwo = systemModelModeTwo;
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
