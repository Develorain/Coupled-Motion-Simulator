package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.BoxModel;
import com.ahmad.Models.ModeOne.SystemModelOne;

public class RightBoxModel extends BoxModel {
    private double mu;

    public RightBoxModel(SystemModelOne systemModelOne, double mass, double mu) {
        super(systemModelOne, mass);

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
