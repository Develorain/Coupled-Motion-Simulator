package com.ahmad.Models.ModeOne;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.Vector;

public class DanglingBoxModel extends BoxModel {
    private SystemModelModeOne systemModelModeOne;

    public DanglingBoxModel(SystemModelModeOne systemModelModeOne, double mass) {
        super(mass);

        this.systemModelModeOne = systemModelModeOne;

        calculateStartingPositionCoordinates();
    }

    public void updateMass(double tension, double acceleration) {
        mass = tension / (Constants.GRAVITY - acceleration);
    }

    @Override
    public void calculateStartingPositionCoordinates() {
        topLeftCorner = Vector.createFromCartesian(
                systemModelModeOne.pulley.topLeftCorner.getX() + systemModelModeOne.pulley.radius,
                systemModelModeOne.pulley.topLeftCorner.getY() + systemModelModeOne.pulley.diameter
        );

        topRightCorner = Vector.createFromCartesian(
                (int) (topLeftCorner.getX() + boxWidth),
                (int) (topLeftCorner.getY())
        );

        bottomLeftCorner = Vector.createFromCartesian(
                (int) (topLeftCorner.getX()),
                (int) (topLeftCorner.getY() + boxHeight)
        );

        bottomRightCorner = Vector.createFromCartesian(
                (int) (topLeftCorner.getX() + boxWidth),
                (int) (topLeftCorner.getY() + boxHeight)
        );
    }
}