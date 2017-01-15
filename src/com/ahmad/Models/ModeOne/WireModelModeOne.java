package com.ahmad.Models.ModeOne;

import com.ahmad.Models.WireModel;
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

        leftStringRightCoord.setX(systemModelModeOne.pulley.topLeftCorner.getX() + systemModelModeOne.pulley.getRadius() - systemModelModeOne.pulley.getRadius() * MathTools.sin(systemModelModeOne.slopeAngle));
        leftStringRightCoord.setY(systemModelModeOne.pulley.topLeftCorner.getY() + systemModelModeOne.pulley.getRadius() - systemModelModeOne.pulley.getRadius() * MathTools.cos(systemModelModeOne.slopeAngle));

        rightStringLeftCoord.setX(systemModelModeOne.pulley.topLeftCorner.getX() + systemModelModeOne.pulley.getDiameter());
        rightStringLeftCoord.setY(systemModelModeOne.pulley.topLeftCorner.getY() + systemModelModeOne.pulley.getRadius());

        rightStringRightCoord.setX((systemModelModeOne.getDanglingBox().topLeftCorner.getX() + systemModelModeOne.getDanglingBox().topRightCorner.getX()) / 2);
        rightStringRightCoord.setY(systemModelModeOne.getDanglingBox().topLeftCorner.getY());
    }

    public void updateTension(double massRight, double acceleration) {
        if (acceleration > 0) {
            tension = massRight * (Constants.GRAVITY - acceleration);
        } else if (acceleration < 0) {
            tension = massRight * (acceleration + Constants.GRAVITY);
        }
    }
}