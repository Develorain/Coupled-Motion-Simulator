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
        leftCoord.setX(Constants.SIMULATION_WIDTH_PIXELS / 3);
        leftCoord.setY(Constants.SIMULATION_HEIGHT_PIXELS / 2);

        rightCoord.setX(Constants.SIMULATION_WIDTH_PIXELS * 2 / 3);
        rightCoord.setY(Constants.SIMULATION_HEIGHT_PIXELS / 2);
    }
}
