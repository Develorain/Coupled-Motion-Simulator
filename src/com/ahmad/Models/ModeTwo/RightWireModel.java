package com.ahmad.Models.ModeTwo;

/** RightWireModel
 * Performs the calculations for the right wire in system in mode two
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.WireModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class RightWireModel extends WireModel {
    private SystemModelModeTwo systemModelModeTwo;  // Declare the system model

    /** Default Constructor
     * @param systemModelModeTwo  a reference to the system model */
    public RightWireModel(SystemModelModeTwo systemModelModeTwo) {
        this.systemModelModeTwo = systemModelModeTwo;

        updatePosition();
    }

    /** Updates the position of the two coordinates of the wire */
    @Override
    public void updatePosition() {
        leftWireLeftCoord.setX(systemModelModeTwo.getMiddleBox().getTopRightCorner().getX());
        leftWireLeftCoord.setY((systemModelModeTwo.getMiddleBox().getTopRightCorner().getY() + systemModelModeTwo.getMiddleBox().getBottomRightCorner().getY()) / 2);

        leftWireRightCoord.setX(systemModelModeTwo.getRightPulley().getTopLeftCorner().getX() + systemModelModeTwo.getRightPulley().getRadius());
        leftWireRightCoord.setY(systemModelModeTwo.getRightPulley().getTopLeftCorner().getY());

        rightWireLeftCoord.setX(systemModelModeTwo.getRightPulley().getTopLeftCorner().getX() + systemModelModeTwo.getRightPulley().getRadius() + systemModelModeTwo.getRightPulley().getRadius() * MathTools.sin(systemModelModeTwo.getRightSlopeAngle()));
        rightWireLeftCoord.setY(systemModelModeTwo.getRightPulley().getTopLeftCorner().getY() + systemModelModeTwo.getRightPulley().getRadius() + systemModelModeTwo.getRightPulley().getRadius() * MathTools.cos(systemModelModeTwo.getRightSlopeAngle()));

        rightWireRightCoord.setX((systemModelModeTwo.getRightBox().getTopLeftCorner().getX() + systemModelModeTwo.getRightBox().getBottomLeftCorner().getX()) / 2);
        rightWireRightCoord.setY((systemModelModeTwo.getRightBox().getTopLeftCorner().getY() + systemModelModeTwo.getRightBox().getBottomLeftCorner().getY()) / 2);
    }

    /** Updates the tension of the wire
     * @param rightMass       the mass the wire is attached to on the right
     * @param acceleration    the acceleration of the box system
     * @param rightSlopeAngle the slope of the right box
     * @param rightMu         the mu value of the right box */
    public void updateTension(double rightMass, double acceleration, double rightSlopeAngle, double rightMu) {
        if (acceleration > 0) {
            tension = rightMass * (Constants.GRAVITY * MathTools.sin(rightSlopeAngle) - rightMu * Constants.GRAVITY * MathTools.cos(rightSlopeAngle) - acceleration);
        } else if (acceleration < 0) {
            tension = rightMass * (acceleration + Constants.GRAVITY * MathTools.sin(rightSlopeAngle) + rightMu * Constants.GRAVITY * MathTools.cos(rightSlopeAngle));
        }
    }
}
