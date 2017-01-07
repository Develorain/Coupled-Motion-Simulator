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
        System.out.println(systemModelModeTwo.getRightSlopeAngle());

        double acuteAngle = 180 - systemModelModeTwo.getRightSlopeAngle();

        bottomRightCorner = Vector.createFromCartesian(
                systemModelModeTwo.rightSlope.x2,
                systemModelModeTwo.rightSlope.y2
        );

        bottomLeftCorner = Vector.createFromCartesian(
                bottomRightCorner.getX() - boxWidth * MathTools.cos(acuteAngle),
                bottomRightCorner.getY() - boxHeight * MathTools.sin(acuteAngle)
        );

        topLeftCorner = Vector.createFromCartesian(
                bottomLeftCorner.getX() + boxWidth * MathTools.sin(acuteAngle),
                bottomLeftCorner.getY() - boxHeight * MathTools.cos(acuteAngle)
        );

        topRightCorner = Vector.createFromCartesian(
                bottomRightCorner.getX() + boxWidth * MathTools.sin(acuteAngle),
                bottomRightCorner.getY() - boxHeight * MathTools.cos(acuteAngle)
        );
    }

    public double getMu() {
        return mu;
    }
}