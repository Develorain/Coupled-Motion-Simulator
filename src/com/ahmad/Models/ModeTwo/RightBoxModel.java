package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class RightBoxModel extends BoxModel {
    private SystemModelModeTwo systemModelModeTwo;
    public double mu;
    public double friction;

    public RightBoxModel(SystemModelModeTwo systemModelModeTwo, double mass, double mu) {
        super(mass);

        this.systemModelModeTwo = systemModelModeTwo;
        this.mu = mu;

        calculateStartingPositionCoordinates();
    }

    public void updateFriction(double slopeAngle) {
        friction = Math.abs(mu * mass * Constants.GRAVITY * MathTools.cos(slopeAngle));
    }

    @Override
    public void calculateStartingPositionCoordinates() {
        double acuteAngle = 180 - systemModelModeTwo.getRightSlopeAngle();

        topLeftCorner.setX((systemModelModeTwo.rightSlope.leftCoord.getX() + systemModelModeTwo.rightSlope.rightCoord.getX()) / 2
                - (boxWidth / 2) * MathTools.cos(acuteAngle) + boxWidth * MathTools.sin(acuteAngle)
        );
        topLeftCorner.setY((systemModelModeTwo.rightSlope.leftCoord.getY() + systemModelModeTwo.rightSlope.rightCoord.getY()) / 2
                - (boxHeight / 2) * MathTools.sin(acuteAngle) - boxHeight * MathTools.cos(acuteAngle)
        );

        calculateBoxVerticesFromTopLeft();
    }

    @Override
    public void calculateBoxVerticesFromTopLeft() {
        double acuteAngle = 180 - systemModelModeTwo.getRightSlopeAngle();

        topRightCorner.setX((int) (topLeftCorner.getX() + boxWidth * MathTools.cos(acuteAngle)));
        topRightCorner.setY((int) (topLeftCorner.getY() + boxHeight * MathTools.sin(acuteAngle)));

        bottomLeftCorner.setX((int) (topLeftCorner.getX() - boxWidth * MathTools.sin(acuteAngle)));
        bottomLeftCorner.setY((int) (topLeftCorner.getY() + boxHeight * MathTools.cos(acuteAngle)));

        bottomRightCorner.setX((int) (bottomLeftCorner.getX() + boxWidth * MathTools.cos(acuteAngle)));
        bottomRightCorner.setY((int) (bottomLeftCorner.getY() + boxHeight * MathTools.sin(acuteAngle)));
    }

    public double getMu() {
        return mu;
    }
}