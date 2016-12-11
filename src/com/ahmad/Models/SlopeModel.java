package com.ahmad.Models;

import com.ahmad.Models.ModeOne.SystemModel;
import com.ahmad.Tools.MathTools;

public class SlopeModel {
    private SystemModel boxSystem;

    public int x1;  // bottom left x
    public int y1;  // bottom left y
    public int x2;  // top right x
    public int y2;  // top right y

    public SlopeModel(SystemModel boxSystem) {
        this.boxSystem = boxSystem;

        calculateCoordinates(45);
    }

    public void calculateCoordinates(double slopeAngle) {
        x1 = (int) (boxSystem.getBoxA().getX() + 100 * MathTools.sin(slopeAngle));
        y1 = (int) (boxSystem.getBoxA().getY() + 100 * MathTools.cos(slopeAngle));

        x2 = boxSystem.getBoxB().getX();
        y2 = boxSystem.getBoxB().getY();
    }
}
