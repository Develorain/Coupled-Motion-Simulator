package com.ahmad.Models.ModeTwo;

/** LeftBoxModel
 * Performs the calculations for the left box in system in mode two
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class LeftBoxModel extends BoxModel {
    private SystemModelModeTwo systemModelModeTwo;  // Declare the system model

    /** Default Constructor
     * @param systemModelModeTwo a reference to the system model
     * @param mass               the mass of the left box
     * @param mu                 the mu value of the left box */
    public LeftBoxModel(SystemModelModeTwo systemModelModeTwo, double mass, double mu) {
        super(mass, mu);

        this.systemModelModeTwo = systemModelModeTwo;

        calculateStartingPositionCoordinates();
    }

    /** Calculates the starting position of the box */
    @Override
    public void calculateStartingPositionCoordinates() {
        topLeftCorner.setX((systemModelModeTwo.getLeftSlope().getLeftCoord().getX() + systemModelModeTwo.getLeftSlope().getRightCoord().getX()) / 2
                - BOX_WIDTH * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle()) - (BOX_WIDTH / 2) * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle()));
        topLeftCorner.setY((systemModelModeTwo.getLeftSlope().getLeftCoord().getY() + systemModelModeTwo.getLeftSlope().getRightCoord().getY()) / 2
                - BOX_HEIGHT * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle()) + (BOX_HEIGHT / 2) * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle()));

        calculateBoxVerticesFromTopLeft();
    }

    /** Calculates top right, bottom left, and bottom right coordinates based on the top left corner */
    @Override
    public void calculateBoxVerticesFromTopLeft() {
        topRightCorner.setX((int) (topLeftCorner.getX() + BOX_WIDTH * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle())));
        topRightCorner.setY((int) (topLeftCorner.getY() - BOX_HEIGHT * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle())));

        bottomLeftCorner.setX((int) (topLeftCorner.getX() + BOX_WIDTH * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle())));
        bottomLeftCorner.setY((int) (topLeftCorner.getY() + BOX_HEIGHT * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle())));

        bottomRightCorner.setX((int) (bottomLeftCorner.getX() + BOX_WIDTH * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle())));
        bottomRightCorner.setY((int) (bottomLeftCorner.getY() - BOX_HEIGHT * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle())));
    }

    /** Updates the frictional force on the box
     * @param slopeAngle the angle the box is slope at in degrees */
    public void updateFriction(double slopeAngle) {
        friction = mu * mass * Constants.GRAVITY * MathTools.cos(slopeAngle);
    }
}