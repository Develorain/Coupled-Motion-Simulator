package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.SlopeModel;
import com.ahmad.Tools.Constants;

public class MiddleSlopeModel extends SlopeModel {
    private SystemModelModeTwo systemModelModeTwo;

    public MiddleSlopeModel(SystemModelModeTwo systemModelModeTwo) {
        super();
        this.systemModelModeTwo = systemModelModeTwo;
        calculateCoordinates();
    }

    @Override
    public void calculateCoordinates() {
        x1 = Constants.SIMULATION_WIDTH_PIXELS / 3;
        y1 = Constants.SIMULATION_HEIGHT_PIXELS / 2;

        x2 = Constants.SIMULATION_WIDTH_PIXELS * 2 / 3;
        y2 = Constants.SIMULATION_HEIGHT_PIXELS / 2;
    }
}
