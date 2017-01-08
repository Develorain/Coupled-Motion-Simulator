package com.ahmad.Models.ModeOne;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;

public class DanglingBoxModel extends BoxModel {
    private SystemModelModeOne systemModelModeOne;

    public DanglingBoxModel(SystemModelModeOne systemModelModeOne, double mass) {
        super(mass);

        this.systemModelModeOne = systemModelModeOne;

        calculateStartingPositionCoordinates();
    }

    @Override
    public void calculateStartingPositionCoordinates() {
        topLeftCorner.setX(systemModelModeOne.pulley.topLeftCorner.getX() + systemModelModeOne.pulley.radius);
        topLeftCorner.setY(systemModelModeOne.pulley.topLeftCorner.getY() + systemModelModeOne.pulley.diameter);

        calculateBoxVerticesFromTopLeft();
    }

    @Override
    public void calculateBoxVerticesFromTopLeft() {
        topRightCorner.setX((int) (topLeftCorner.getX() + boxWidth));
        topRightCorner.setY((int) (topLeftCorner.getY()));

        bottomLeftCorner.setX((int) (topLeftCorner.getX()));
        bottomLeftCorner.setY((int) (topLeftCorner.getY() + boxHeight));

        bottomRightCorner.setX((int) (topLeftCorner.getX() + boxWidth));
        bottomRightCorner.setY((int) (topLeftCorner.getY() + boxHeight));
    }

    public void updateMass(double tension, double acceleration) {
        mass = tension / (Constants.GRAVITY - acceleration);
    }
}