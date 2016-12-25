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
        x1 = (int) (systemModel.getSlopedBox().getX() + systemModel.getSlopedBox().getBoxWidth() * MathTools.sin(systemModel.getSlopeAngle()));
        y1 = (int) (systemModel.getSlopedBox().getY() + systemModel.getSlopedBox().getBoxHeight() * MathTools.cos(systemModel.getSlopeAngle()));

        x2 = systemModel.getDanglingBox().getX();
        y2 = systemModel.getDanglingBox().getY();
    }
}
