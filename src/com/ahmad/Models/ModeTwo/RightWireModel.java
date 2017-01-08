package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.WireModel;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public class RightWireModel extends WireModel {
    public SystemModelModeTwo systemModelModeTwo;

    public RightWireModel(SystemModelModeTwo systemModelModeTwo) {
        this.systemModelModeTwo = systemModelModeTwo;

        calculateCoordinates();
    }

    @Override
    public void calculateCoordinates() {
        leftStringLeftCoord = Vector.createFromCartesian(
                systemModelModeTwo.middleBox.topRightCorner.getX(),
                (systemModelModeTwo.middleBox.topRightCorner.getY() + systemModelModeTwo.middleBox.bottomRightCorner.getY()) / 2
        );

        leftStringRightCoord = Vector.createFromCartesian(
                systemModelModeTwo.rightPulley.topLeftCorner.getX() + systemModelModeTwo.rightPulley.radius,
                systemModelModeTwo.rightPulley.topLeftCorner.getY()
        );

        // TODO: wrong!!
        rightStringLeftCoord = Vector.createFromCartesian(
                systemModelModeTwo.rightPulley.topLeftCorner.getX() + systemModelModeTwo.rightPulley.radius + systemModelModeTwo.rightPulley.radius * MathTools.sin(systemModelModeTwo.getRightSlopeAngle()),
                systemModelModeTwo.rightPulley.topLeftCorner.getY() + systemModelModeTwo.rightPulley.radius + systemModelModeTwo.rightPulley.radius * MathTools.cos(systemModelModeTwo.getRightSlopeAngle())
        );

        rightStringRightCoord = Vector.createFromCartesian(
                (systemModelModeTwo.rightBox.topLeftCorner.getX() + systemModelModeTwo.rightBox.bottomLeftCorner.getX()) / 2,
                (systemModelModeTwo.rightBox.topLeftCorner.getY() + systemModelModeTwo.rightBox.bottomLeftCorner.getY()) / 2
        );
    }
}
