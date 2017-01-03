package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.SlopeModel;
import com.ahmad.Tools.Constants;
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
        x1 = (int) Math.round(systemModelModeTwo.leftBox.getX() + systemModelModeTwo.leftBox.getBoxWidth() * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle()));
        y1 = (int) Math.round(systemModelModeTwo.leftBox.getY() + systemModelModeTwo.leftBox.getBoxHeight() * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle()));

        x2 = Constants.SIMULATION_WIDTH_PIXELS / 3;
        y2 = Constants.SIMULATION_HEIGHT_PIXELS / 2;
    }
}
