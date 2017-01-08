package com.ahmad.Models;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public class WireModelModeOne extends WireModel {
    private SystemModelModeOne systemModelModeOne;

    public WireModelModeOne(SystemModelModeOne systemModelModeOne) {
        this.systemModelModeOne = systemModelModeOne;

        calculateCoordinates();
    }

    @Override
    public void calculateCoordinates() {
        leftStringLeftCoord = Vector.createFromCartesian(
                (systemModelModeOne.getSlopedBox().topRightCorner.getX() + systemModelModeOne.getSlopedBox().bottomRightCorner.getX()) / 2,
                (systemModelModeOne.getSlopedBox().topRightCorner.getY() + systemModelModeOne.getSlopedBox().bottomRightCorner.getY()) / 2
        );

        leftStringRightCoord = Vector.createFromCartesian(
                systemModelModeOne.pulley.topLeftCorner.getX() + systemModelModeOne.pulley.radius - systemModelModeOne.pulley.radius * MathTools.sin(systemModelModeOne.slopeAngle),
                systemModelModeOne.pulley.topLeftCorner.getY() + systemModelModeOne.pulley.radius - systemModelModeOne.pulley.radius * MathTools.cos(systemModelModeOne.slopeAngle)
        );

        rightStringLeftCoord = Vector.createFromCartesian(
                systemModelModeOne.pulley.topLeftCorner.getX() + systemModelModeOne.pulley.diameter,
                systemModelModeOne.pulley.topLeftCorner.getY() + systemModelModeOne.pulley.radius
        );

        rightStringRightCoord = Vector.createFromCartesian(
                (systemModelModeOne.getDanglingBox().topLeftCorner.getX() + systemModelModeOne.getDanglingBox().topRightCorner.getX()) / 2,
                systemModelModeOne.getDanglingBox().topLeftCorner.getY()
        );
    }

    public void updateTension(double massRight, double acceleration) {
        tension = massRight * (Constants.GRAVITY - acceleration);
    }
}