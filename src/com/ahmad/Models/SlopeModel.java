package com.ahmad.Models;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Tools.MathTools;

public class SlopeModel {
    private SystemModelModeOne systemModelModeOne;

    public int x1;  // bottom left x
    public int y1;  // bottom left y
    public int x2;  // top right x
    public int y2;  // top right y

    public SlopeModel(SystemModelModeOne systemModelModeOne) {
        this.systemModelModeOne = systemModelModeOne;

        calculateCoordinates();
    }

    public void calculateCoordinates() {
        x1 = (int) (systemModelModeOne.getSlopedBox().getX() + systemModelModeOne.getSlopedBox().getBoxWidth() * MathTools.sin(systemModelModeOne.getSlopeAngle()));
        y1 = (int) (systemModelModeOne.getSlopedBox().getY() + systemModelModeOne.getSlopedBox().getBoxHeight() * MathTools.cos(systemModelModeOne.getSlopeAngle()));

        x2 = systemModelModeOne.getDanglingBox().getX();
        y2 = systemModelModeOne.getDanglingBox().getY();
    }
}
