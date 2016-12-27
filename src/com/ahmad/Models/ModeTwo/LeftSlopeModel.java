package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.SlopeModel;
import com.ahmad.Tools.Globals;
import com.ahmad.Tools.MathTools;

public class LeftSlopeModel extends SlopeModel {
    private SystemModelModeTwo systemModelModeTwo;

    public LeftSlopeModel(SystemModelModeTwo systemModelModeTwo) {
        super();
        this.systemModelModeTwo = systemModelModeTwo;
        calculateCoordinates();
    }

    @Override
    public void calculateCoordinates() {
        x1 = (int) Math.round(systemModelModeTwo.leftBox.getX());
        y1 = (int) Math.round(systemModelModeTwo.leftBox.getY());

        x2 = Globals.SIMULATION_WIDTH_PIXELS / 3;
        y2 = Globals.SIMULATION_HEIGHT_PIXELS / 2;
    }
}
