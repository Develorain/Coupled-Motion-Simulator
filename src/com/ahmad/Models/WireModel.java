package com.ahmad.Models;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Tools.MathTools;

public class WireModel {
    public int x1;
    public int y1;
    public int x2;
    public int y2;

    private SystemModelModeOne systemModelModeOne;
    public double tension;

    public WireModel(SystemModelModeOne systemModelModeOne) {
        this.systemModelModeOne = systemModelModeOne;

        calculateCoordinates();
    }

    // top left x:     systemModelModeOne.getSlopedBox().getX()
    // top left y:     systemModelModeOne.getSlopedBox().getY()

    // bottom left x:  (int) (systemModelModeOne.getSlopedBox().getX() + systemModelModeOne.getSlopedBox().getBoxWidth() * MathTools.sin(systemModelModeOne.getSlopeAngle()))
    // bottom left y:  (int) (systemModelModeOne.getSlopedBox().getY() + systemModelModeOne.getSlopedBox().getBoxHeight() * MathTools.cos(systemModelModeOne.getSlopeAngle()))

    // top right x:    (int) (systemModelModeOne.getSlopedBox().getX() + systemModelModeOne.getSlopedBox().getBoxWidth() * MathTools.cos(systemModelModeOne.getSlopeAngle()))
    // top right y:    (int) (systemModelModeOne.getSlopedBox().getY() - systemModelModeOne.getSlopedBox().getBoxHeight() * MathTools.sin(systemModelModeOne.getSlopeAngle()))

    // bottom right x: (int) (systemModelModeOne.getSlopedBox().getX() + systemModelModeOne.getSlopedBox().getBoxWidth() * MathTools.sin(systemModelModeOne.getSlopeAngle()) + systemModelModeOne.getSlopedBox().getBoxWidth() * MathTools.cos(systemModelModeOne.getSlopeAngle()))
    // bottom right y: (int) (systemModelModeOne.getSlopedBox().getY() + systemModelModeOne.getSlopedBox().getBoxHeight() * MathTools.cos(systemModelModeOne.getSlopeAngle()) - systemModelModeOne.getSlopedBox().getBoxHeight() * MathTools.sin(systemModelModeOne.getSlopeAngle()))

    public void calculateCoordinates() {
        x1 = (int) (
                (systemModelModeOne.getSlopedBox().getX() + systemModelModeOne.getSlopedBox().getBoxWidth() * MathTools.cos(systemModelModeOne.getSlopeAngle())
                        + systemModelModeOne.getSlopedBox().getX() + systemModelModeOne.getSlopedBox().getBoxWidth() * MathTools.sin(systemModelModeOne.getSlopeAngle()) + systemModelModeOne.getSlopedBox().getBoxWidth() * MathTools.cos(systemModelModeOne.getSlopeAngle()))
                        / 2
        );

        y1 = (int) (
                (systemModelModeOne.getSlopedBox().getY() - systemModelModeOne.getSlopedBox().getBoxHeight() * MathTools.sin(systemModelModeOne.getSlopeAngle())
                        + systemModelModeOne.getSlopedBox().getY() + systemModelModeOne.getSlopedBox().getBoxHeight() * MathTools.cos(systemModelModeOne.getSlopeAngle()) - systemModelModeOne.getSlopedBox().getBoxHeight() * MathTools.sin(systemModelModeOne.getSlopeAngle()))
                        / 2
        );

        x2 = (int) (systemModelModeOne.getDanglingBox().getX() + 25); // - 50 * MathTools.cos(systemModelModeOne.getSlopeAngle())
        y2 = (int) (systemModelModeOne.getDanglingBox().getY() - 50); //+ 50 * MathTools.sin(systemModelModeOne.getSlopeAngle())
    }

    public void calculateTension(double acceleration) {
        tension = systemModelModeOne.getDanglingBox().getMass() * 9.8 - systemModelModeOne.getDanglingBox().getMass() * acceleration;
    }
}
