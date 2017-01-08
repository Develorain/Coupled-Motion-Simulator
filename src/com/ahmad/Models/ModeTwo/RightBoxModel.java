package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public class RightBoxModel extends BoxModel {
    private SystemModelModeTwo systemModelModeTwo;
    private double mu;

    public RightBoxModel(SystemModelModeTwo systemModelModeTwo, double mass, double mu) {
        super(mass);

        this.systemModelModeTwo = systemModelModeTwo;
        this.mu = mu;

        calculateStartingPositionCoordinates();
    }

    @Override
    public void calculateStartingPositionCoordinates() {
        double acuteAngle = 180 - systemModelModeTwo.getRightSlopeAngle();

        topLeftCorner = Vector.createFromCartesian(
                systemModelModeTwo.rightSlope.rightCoord.getX() - boxWidth * MathTools.cos(acuteAngle) + boxWidth * MathTools.sin(acuteAngle),
                systemModelModeTwo.rightSlope.rightCoord.getY() - boxHeight * MathTools.sin(acuteAngle) - boxHeight * MathTools.cos(acuteAngle)
        );

        calculateBoxVerticesFromTopLeft();
    }

    @Override
    public void calculateBoxVerticesFromTopLeft() {
        double acuteAngle = 180 - systemModelModeTwo.getRightSlopeAngle();

        topRightCorner = Vector.createFromCartesian(
                (int) (topLeftCorner.getX() + boxWidth * MathTools.cos(acuteAngle)),
                (int) (topLeftCorner.getY() + boxHeight * MathTools.sin(acuteAngle))
        );

        bottomLeftCorner = Vector.createFromCartesian(
                (int) (topLeftCorner.getX() - boxWidth * MathTools.sin(acuteAngle)),
                (int) (topLeftCorner.getY() + boxHeight * MathTools.cos(acuteAngle))
        );

        bottomRightCorner = Vector.createFromCartesian(
                (int) (bottomLeftCorner.getX() + boxWidth * MathTools.cos(acuteAngle)),
                (int) (bottomLeftCorner.getY() + boxHeight * MathTools.sin(acuteAngle))
        );
    }

    public double getMu() {
        return mu;
    }
}