package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.WireModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class RightWireModel extends WireModel {
    public SystemModelModeTwo systemModelModeTwo;

    public RightWireModel(SystemModelModeTwo systemModelModeTwo) {
        this.systemModelModeTwo = systemModelModeTwo;

        updatePosition();
    }

    public void updateTension(double rightMass, double acceleration, double rightSlopeAngle, double rightMu) {
        if (acceleration > 0) {
            tension = rightMass * (Constants.GRAVITY * MathTools.sin(rightSlopeAngle) - rightMu * Constants.GRAVITY * MathTools.cos(rightSlopeAngle) - acceleration);
        } else if (acceleration < 0) {
            tension = rightMass * (acceleration + Constants.GRAVITY * MathTools.sin(rightSlopeAngle) + rightMu * Constants.GRAVITY * MathTools.cos(rightSlopeAngle));
        }
    }

    @Override
    public void updatePosition() {
        leftStringLeftCoord.setX(systemModelModeTwo.middleBox.topRightCorner.getX());
        leftStringLeftCoord.setY((systemModelModeTwo.middleBox.topRightCorner.getY() + systemModelModeTwo.middleBox.bottomRightCorner.getY()) / 2);

        leftStringRightCoord.setX(systemModelModeTwo.rightPulley.getTopLeftCorner().getX() + systemModelModeTwo.rightPulley.getRadius());
        leftStringRightCoord.setY(systemModelModeTwo.rightPulley.getTopLeftCorner().getY());

        rightStringLeftCoord.setX(systemModelModeTwo.rightPulley.getTopLeftCorner().getX() + systemModelModeTwo.rightPulley.getRadius() + systemModelModeTwo.rightPulley.getRadius() * MathTools.sin(systemModelModeTwo.getRightSlopeAngle()));
        rightStringLeftCoord.setY(systemModelModeTwo.rightPulley.getTopLeftCorner().getY() + systemModelModeTwo.rightPulley.getRadius() + systemModelModeTwo.rightPulley.getRadius() * MathTools.cos(systemModelModeTwo.getRightSlopeAngle()));

        rightStringRightCoord.setX((systemModelModeTwo.rightBox.topLeftCorner.getX() + systemModelModeTwo.rightBox.bottomLeftCorner.getX()) / 2);
        rightStringRightCoord.setY((systemModelModeTwo.rightBox.topLeftCorner.getY() + systemModelModeTwo.rightBox.bottomLeftCorner.getY()) / 2);
    }
}
