package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.WireModel;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public class LeftWireModel extends WireModel {
    public SystemModelModeTwo systemModelModeTwo;

    public LeftWireModel(SystemModelModeTwo systemModelModeTwo) {
        this.systemModelModeTwo = systemModelModeTwo;

        calculateCoordinates();
    }

    @Override
    public void calculateCoordinates() {
        // TODO: i don't feel like this line is parallel with the ground
        leftStringLeftCoord = Vector.createFromCartesian(
                (systemModelModeTwo.leftBox.topRightCorner.getX() + systemModelModeTwo.leftBox.bottomRightCorner.getX()) / 2,
                (systemModelModeTwo.leftBox.topRightCorner.getY() + systemModelModeTwo.leftBox.bottomRightCorner.getY()) / 2
        );

        leftStringRightCoord = Vector.createFromCartesian(
                systemModelModeTwo.leftPulley.topLeftCorner.getX() + systemModelModeTwo.leftPulley.radius - systemModelModeTwo.leftPulley.radius * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle()),
                systemModelModeTwo.leftPulley.topLeftCorner.getY() + systemModelModeTwo.leftPulley.radius - systemModelModeTwo.leftPulley.radius * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle())
        );

        rightStringLeftCoord = Vector.createFromCartesian(
                systemModelModeTwo.leftPulley.topLeftCorner.getX() + systemModelModeTwo.leftPulley.radius,
                systemModelModeTwo.leftPulley.topLeftCorner.getY()
        );

        rightStringRightCoord = Vector.createFromCartesian(
                systemModelModeTwo.middleBox.topLeftCorner.getX(),
                (systemModelModeTwo.middleBox.topLeftCorner.getY() + systemModelModeTwo.middleBox.bottomLeftCorner.getY()) / 2
        );
    }
}
