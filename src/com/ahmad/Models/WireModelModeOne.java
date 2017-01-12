package com.ahmad.Models;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class WireModelModeOne extends WireModel {
    private SystemModelModeOne systemModelModeOne;

    public WireModelModeOne(SystemModelModeOne systemModelModeOne) {
        this.systemModelModeOne = systemModelModeOne;

        updatePosition();
    }

    @Override
    public void updatePosition() {
        leftStringLeftCoord.setX((systemModelModeOne.getSlopedBox().topRightCorner.getX() + systemModelModeOne.getSlopedBox().bottomRightCorner.getX()) / 2);
        leftStringLeftCoord.setY((systemModelModeOne.getSlopedBox().topRightCorner.getY() + systemModelModeOne.getSlopedBox().bottomRightCorner.getY()) / 2);

        leftStringRightCoord.setX(systemModelModeOne.pulley.topLeftCorner.getX() + systemModelModeOne.pulley.radius - systemModelModeOne.pulley.radius * MathTools.sin(systemModelModeOne.slopeAngle));
        leftStringRightCoord.setY(systemModelModeOne.pulley.topLeftCorner.getY() + systemModelModeOne.pulley.radius - systemModelModeOne.pulley.radius * MathTools.cos(systemModelModeOne.slopeAngle));

        rightStringLeftCoord.setX(systemModelModeOne.pulley.topLeftCorner.getX() + systemModelModeOne.pulley.diameter);
        rightStringLeftCoord.setY(systemModelModeOne.pulley.topLeftCorner.getY() + systemModelModeOne.pulley.radius);

        rightStringRightCoord.setX((systemModelModeOne.getDanglingBox().topLeftCorner.getX() + systemModelModeOne.getDanglingBox().topRightCorner.getX()) / 2);
        rightStringRightCoord.setY(systemModelModeOne.getDanglingBox().topLeftCorner.getY());
    }

    // TODO: assumes box is moving right, add case for when moving left
    public void updateTension(double massRight, double acceleration) {
        tension = massRight * (Constants.GRAVITY - acceleration);
    }
}