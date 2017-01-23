package com.ahmad.Models.ModeTwo;

/** LeftWireModel
 * Performs the calculations for the left wire in system in mode two
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.WireModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class LeftWireModel extends WireModel {
    private SystemModelModeTwo systemModelModeTwo;  // Declare the system model

    /** Default Constructor
     * @param systemModelModeTwo  a reference to the system model */
    public LeftWireModel(SystemModelModeTwo systemModelModeTwo) {
        this.systemModelModeTwo = systemModelModeTwo;

        updatePosition();
    }

    /** Updates the position of the two coordinates of the wire */
    @Override
    public void updatePosition() {
        leftWireLeftCoord.setX((systemModelModeTwo.getLeftBox().getTopRightCorner().getX() + systemModelModeTwo.getLeftBox().getBottomRightCorner().getX()) / 2);
        leftWireLeftCoord.setY((systemModelModeTwo.getLeftBox().getTopRightCorner().getY() + systemModelModeTwo.getLeftBox().getBottomRightCorner().getY()) / 2);

        leftWireRightCoord.setX(systemModelModeTwo.getLeftPulley().getTopLeftCorner().getX() + systemModelModeTwo.getLeftPulley().getRadius() - systemModelModeTwo.getLeftPulley().getRadius() * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle()));
        leftWireRightCoord.setY(systemModelModeTwo.getLeftPulley().getTopLeftCorner().getY() + systemModelModeTwo.getLeftPulley().getRadius() - systemModelModeTwo.getLeftPulley().getRadius() * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle()));

        rightWireLeftCoord.setX(systemModelModeTwo.getLeftPulley().getTopLeftCorner().getX() + systemModelModeTwo.getLeftPulley().getRadius());
        rightWireLeftCoord.setY(systemModelModeTwo.getLeftPulley().getTopLeftCorner().getY());

        rightWireRightCoord.setX(systemModelModeTwo.getMiddleBox().getTopLeftCorner().getX());
        rightWireRightCoord.setY((systemModelModeTwo.getMiddleBox().getTopLeftCorner().getY() + systemModelModeTwo.getMiddleBox().getBottomLeftCorner().getY()) / 2);
    }

    /** Updates the tension of the wire
     * @param leftMass       the mass the wire is attached to on the left
     * @param acceleration   the acceleration of the box system
     * @param leftSlopeAngle the slope of the left box
     * @param leftMu         the mu value of the left box */
    public void updateTension(double leftMass, double acceleration, double leftSlopeAngle, double leftMu) {
        if (acceleration > 0) {
            tension = leftMass * (acceleration + Constants.GRAVITY * MathTools.sin(leftSlopeAngle) + leftMu * Constants.GRAVITY * MathTools.cos(leftSlopeAngle));
        } else if (acceleration < 0) {
            tension = leftMass * (Constants.GRAVITY * MathTools.sin(leftSlopeAngle) - leftMu * Constants.GRAVITY * MathTools.cos(leftSlopeAngle) - acceleration);
        }
    }
}
