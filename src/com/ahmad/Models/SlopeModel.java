package com.ahmad.Models;

import com.ahmad.Models.ModeOne.SystemModelOne;
import com.ahmad.Tools.MathTools;

public class SlopeModel {
    private SystemModelOne systemModelOne;

    public int x1;  // bottom left x
    public int y1;  // bottom left y
    public int x2;  // top right x
    public int y2;  // top right y

    public SlopeModel(SystemModelOne systemModelOne) {
        this.systemModelOne = systemModelOne;

        calculateCoordinates();
    }

    public void calculateCoordinates() {
        x1 = (int) (systemModelOne.getSlopedBox().getX() + systemModelOne.getSlopedBox().getBoxWidth() * MathTools.sin(systemModelOne.getSlopeAngle()));
        y1 = (int) (systemModelOne.getSlopedBox().getY() + systemModelOne.getSlopedBox().getBoxHeight() * MathTools.cos(systemModelOne.getSlopeAngle()));

        x2 = systemModelOne.getDanglingBox().getX();
        y2 = systemModelOne.getDanglingBox().getY();
    }
}
