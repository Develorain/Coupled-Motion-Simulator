package com.ahmad.Models.ModeOne;

import com.ahmad.Models.SlopeModel;
import com.ahmad.Tools.MathTools;

public class SlopeModelModeOne extends SlopeModel {
    private SystemModelModeOne systemModelModeOne;

    public SlopeModelModeOne(SystemModelModeOne systemModelModeOne) {
        super();
        this.systemModelModeOne = systemModelModeOne;
        calculateCoordinates();
    }

    @Override
    public void calculateCoordinates() {
        x1 = (int) (systemModelModeOne.getSlopedBox().getX() + systemModelModeOne.getSlopedBox().getBoxWidth() * MathTools.sin(systemModelModeOne.getSlopeAngle()));
        y1 = (int) (systemModelModeOne.getSlopedBox().getY() + systemModelModeOne.getSlopedBox().getBoxHeight() * MathTools.cos(systemModelModeOne.getSlopeAngle()));

        x2 = systemModelModeOne.getDanglingBox().getX();
        y2 = systemModelModeOne.getDanglingBox().getY();
    }
}
