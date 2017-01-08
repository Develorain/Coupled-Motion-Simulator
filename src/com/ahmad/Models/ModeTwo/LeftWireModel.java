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

        /*
        coordOnLeftSideOfPulley = Vector.createFromCartesian(
                systemModelModeTwo.pulley.topLeftCorner.getX() + systemModelModeTwo.pulley.radius - systemModelModeTwo.pulley.radius * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle()),
                1
        );
        */

        /*
        x1 = (int) ((systemModelModeOne.getSlopedBox().topRightCorner.getX() + systemModelModeOne.getSlopedBox().bottomRightCorner.getX()) / 2);
        y1 = (int) ((systemModelModeOne.getSlopedBox().topRightCorner.getY() + systemModelModeOne.getSlopedBox().bottomRightCorner.getY()) / 2);

        x2 = (int) (systemModelModeOne.pulley.topLeftCorner.getX() + systemModelModeOne.pulley.radius - systemModelModeOne.pulley.radius * MathTools.sin(systemModelModeOne.slopeAngle));
        y2 = (int) (systemModelModeOne.pulley.topLeftCorner.getY() + systemModelModeOne.pulley.radius - systemModelModeOne.pulley.radius * MathTools.cos(systemModelModeOne.slopeAngle));

        x3 = (int) (systemModelModeOne.pulley.topLeftCorner.getX() + systemModelModeOne.pulley.diameter);
        y3 = (int) (systemModelModeOne.pulley.topLeftCorner.getY() + systemModelModeOne.pulley.radius);

        x4 = (int) ((systemModelModeOne.getDanglingBox().topLeftCorner.getX() + systemModelModeOne.getDanglingBox().topRightCorner.getX()) / 2);
        y4 = (int) (systemModelModeOne.getDanglingBox().topLeftCorner.getY());
        */
    }
}
