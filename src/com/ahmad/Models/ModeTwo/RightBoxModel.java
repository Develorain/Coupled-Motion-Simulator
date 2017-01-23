package com.ahmad.Models.ModeTwo;

/** RightBoxModel
 * Performs the calculations for the right box in system in mode two
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class RightBoxModel extends BoxModel {
    private SystemModelModeTwo systemModelModeTwo;  // Declare the system model

    /** Default Constructor
     * @param systemModelModeTwo a reference to the system model
     * @param mass               the mass of the left box
     * @param mu                 the mu value of the left box */
    public RightBoxModel(SystemModelModeTwo systemModelModeTwo, double mass, double mu) {
        super(mass, mu);

        this.systemModelModeTwo = systemModelModeTwo;

        calculateStartingPositionCoordinates();
    }

    /** Calculates the starting position of the box */
    @Override
    public void calculateStartingPositionCoordinates() {
        double acuteAngle = 180 - systemModelModeTwo.getRightSlopeAngle();

        topLeftCorner.setX((systemModelModeTwo.getRightSlope().getLeftCoord().getX() + systemModelModeTwo.getRightSlope().getRightCoord().getX()) / 2
                - (BOX_WIDTH / 2) * MathTools.cos(acuteAngle) + BOX_WIDTH * MathTools.sin(acuteAngle));
        topLeftCorner.setY((systemModelModeTwo.getRightSlope().getLeftCoord().getY() + systemModelModeTwo.getRightSlope().getRightCoord().getY()) / 2
                - (BOX_HEIGHT / 2) * MathTools.sin(acuteAngle) - BOX_HEIGHT * MathTools.cos(acuteAngle));

        calculateBoxVerticesFromTopLeft();
    }

    /** Calculates top right, bottom left, and bottom right coordinates based on the top left corner */
    @Override
    public void calculateBoxVerticesFromTopLeft() {
        double acuteAngle = 180 - systemModelModeTwo.getRightSlopeAngle();

        topRightCorner.setX((int) (topLeftCorner.getX() + BOX_WIDTH * MathTools.cos(acuteAngle)));
        topRightCorner.setY((int) (topLeftCorner.getY() + BOX_HEIGHT * MathTools.sin(acuteAngle)));

        bottomLeftCorner.setX((int) (topLeftCorner.getX() - BOX_WIDTH * MathTools.sin(acuteAngle)));
        bottomLeftCorner.setY((int) (topLeftCorner.getY() + BOX_HEIGHT * MathTools.cos(acuteAngle)));

        bottomRightCorner.setX((int) (bottomLeftCorner.getX() + BOX_WIDTH * MathTools.cos(acuteAngle)));
        bottomRightCorner.setY((int) (bottomLeftCorner.getY() + BOX_HEIGHT * MathTools.sin(acuteAngle)));
    }

    /** Updates the frictional force on the box
     * @param slopeAngle the angle the box is slope at in degrees */
    public void updateFriction(double slopeAngle) {
        friction = Math.abs(mu * mass * Constants.GRAVITY * MathTools.cos(slopeAngle));
    }
}