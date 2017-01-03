package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;

public class MiddleBoxModel extends BoxModel {
    private SystemModelModeTwo systemModelModeTwo;
    private double mu;

    public MiddleBoxModel(SystemModelModeTwo systemModelModeTwo, double mass, double mu) {
        super(mass);

        this.systemModelModeTwo = systemModelModeTwo;
        this.mu = mu;

        calculateCoordinates();
    }

    @Override
    public void calculateCoordinates() {
        x = Constants.SIMULATION_WIDTH_PIXELS / 2 - boxWidth / 2;
        y = Constants.SIMULATION_HEIGHT_PIXELS / 2 - boxHeight;
    }

    public double getMu() {
        return mu;
    }
}
