package com.ahmad.Models;

import com.ahmad.Models.ModeOne.SystemModel;
import com.ahmad.Tools.MathTools;

public class SlopeModel {
    private SystemModel systemModel;

    public int x1;  // bottom left x
    public int y1;  // bottom left y
    public int x2;  // top right x
    public int y2;  // top right y

    public SlopeModel(SystemModel systemModel) {
        this.systemModel = systemModel;

        calculateCoordinates();
    }

    public void calculateCoordinates() {
        x1 = (int) (systemModel.getBoxA().getX() + 100 * MathTools.sin(systemModel.getSlopeAngle()));
        y1 = (int) (systemModel.getBoxA().getY() + 100 * MathTools.cos(systemModel.getSlopeAngle()));

        x2 = systemModel.getBoxB().getX();
        y2 = systemModel.getBoxB().getY();
    }
}
