package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.SlopeModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.Vector;

public class MiddleSlopeModel extends SlopeModel {
    private SystemModelModeTwo systemModelModeTwo;

    public MiddleSlopeModel(SystemModelModeTwo systemModelModeTwo) {
        super();
        this.systemModelModeTwo = systemModelModeTwo;
        calculateCoordinates();
    }

    @Override
    public void calculateCoordinates() {
        leftCoord = Vector.createFromCartesian(Constants.SIMULATION_WIDTH_PIXELS / 3, Constants.SIMULATION_HEIGHT_PIXELS / 2);
        rightCoord = Vector.createFromCartesian(Constants.SIMULATION_WIDTH_PIXELS * 2 / 3, Constants.SIMULATION_HEIGHT_PIXELS / 2);
    }
}
