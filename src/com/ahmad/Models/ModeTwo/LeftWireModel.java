package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.WireModel;
import com.ahmad.Tools.MathTools;

public class LeftWireModel extends WireModel {
    public SystemModelModeTwo systemModelModeTwo;

    public LeftWireModel(SystemModelModeTwo systemModelModeTwo) {
        this.systemModelModeTwo = systemModelModeTwo;

        updatePosition();
    }

    @Override
    public void updatePosition() {
        // TODO: i don't feel like this line is parallel with the ground
        leftStringLeftCoord.setX((systemModelModeTwo.leftBox.topRightCorner.getX() + systemModelModeTwo.leftBox.bottomRightCorner.getX()) / 2);
        leftStringLeftCoord.setY((systemModelModeTwo.leftBox.topRightCorner.getY() + systemModelModeTwo.leftBox.bottomRightCorner.getY()) / 2);

        leftStringRightCoord.setX(systemModelModeTwo.leftPulley.topLeftCorner.getX() + systemModelModeTwo.leftPulley.radius - systemModelModeTwo.leftPulley.radius * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle()));
        leftStringRightCoord.setY(systemModelModeTwo.leftPulley.topLeftCorner.getY() + systemModelModeTwo.leftPulley.radius - systemModelModeTwo.leftPulley.radius * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle()));

        rightStringLeftCoord.setX(systemModelModeTwo.leftPulley.topLeftCorner.getX() + systemModelModeTwo.leftPulley.radius);
        rightStringLeftCoord.setY(systemModelModeTwo.leftPulley.topLeftCorner.getY());

        rightStringRightCoord.setX(systemModelModeTwo.middleBox.topLeftCorner.getX());
        rightStringRightCoord.setY((systemModelModeTwo.middleBox.topLeftCorner.getY() + systemModelModeTwo.middleBox.bottomLeftCorner.getY()) / 2);
    }
}
