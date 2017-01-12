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

    // TODO: assumes boxes are moving to the right, consider when they go left
    public void updateTension(double rightMass, double acceleration, double rightSlopeAngle, double rightMu) {
        tension = rightMass * (Constants.GRAVITY * MathTools.sin(rightSlopeAngle) - rightMu * Constants.GRAVITY * MathTools.cos(rightSlopeAngle) - acceleration);
    }

    @Override
    public void updatePosition() {
        leftStringLeftCoord.setX(systemModelModeTwo.middleBox.topRightCorner.getX());
        leftStringLeftCoord.setY((systemModelModeTwo.middleBox.topRightCorner.getY() + systemModelModeTwo.middleBox.bottomRightCorner.getY()) / 2);

        leftStringRightCoord.setX(systemModelModeTwo.rightPulley.topLeftCorner.getX() + systemModelModeTwo.rightPulley.radius);
        leftStringRightCoord.setY(systemModelModeTwo.rightPulley.topLeftCorner.getY());

        // TODO: doesn't look parallel to ground
        rightStringLeftCoord.setX(systemModelModeTwo.rightPulley.topLeftCorner.getX() + systemModelModeTwo.rightPulley.radius + systemModelModeTwo.rightPulley.radius * MathTools.sin(systemModelModeTwo.getRightSlopeAngle()));
        rightStringLeftCoord.setY(systemModelModeTwo.rightPulley.topLeftCorner.getY() + systemModelModeTwo.rightPulley.radius + systemModelModeTwo.rightPulley.radius * MathTools.cos(systemModelModeTwo.getRightSlopeAngle()));

        rightStringRightCoord.setX((systemModelModeTwo.rightBox.topLeftCorner.getX() + systemModelModeTwo.rightBox.bottomLeftCorner.getX()) / 2);
        rightStringRightCoord.setY((systemModelModeTwo.rightBox.topLeftCorner.getY() + systemModelModeTwo.rightBox.bottomLeftCorner.getY()) / 2);
    }
}
