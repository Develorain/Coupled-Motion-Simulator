package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.SlopeModel;
import com.ahmad.Tools.Globals;
import com.ahmad.Tools.MathTools;

public class RightSlopeModel extends SlopeModel {
    private SystemModelModeTwo systemModelModeTwo;

    public RightSlopeModel(SystemModelModeTwo systemModelModeTwo) {
        super();
        this.systemModelModeTwo = systemModelModeTwo;
        calculateCoordinates();
    }

    @Override
    public void calculateCoordinates() {
        x1 = Globals.SIMULATION_WIDTH_PIXELS * 2 / 3;
        y1 = Globals.SIMULATION_HEIGHT_PIXELS / 2;

        x2 = (int) Math.round(systemModelModeTwo.rightBox.getX() + systemModelModeTwo.rightBox.getBoxWidth() * MathTools.cos(systemModelModeTwo.getRightSlopeAngle()));
        y2 = (int) Math.round(systemModelModeTwo.rightBox.getY() + systemModelModeTwo.rightBox.getBoxHeight() * MathTools.sin(systemModelModeTwo.getRightSlopeAngle()));

        //x2 = systemModelModeTwo.rightBox.getX();
        //y2 = systemModelModeTwo.rightBox.getY();
    }
}
