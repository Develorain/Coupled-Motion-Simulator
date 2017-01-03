package com.ahmad.Models.ModeOne;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class DanglingBoxModel extends BoxModel {
    private SystemModelModeOne systemModelModeOne;

    public DanglingBoxModel(SystemModelModeOne systemModelModeOne, double mass) {
        super(mass);

        this.systemModelModeOne = systemModelModeOne;

        calculateCoordinates();
    }

    public void updateMass(double tension, double acceleration) {
        mass = tension / (Constants.GRAVITY - acceleration);
    }

    @Override
    public void calculateCoordinates() {
        double tempX = Constants.SIMULATION_WIDTH_PIXELS / 2;
        double tempY = Constants.SIMULATION_HEIGHT_PIXELS / 2;

        while (true) {
            tempX += MathTools.cos(systemModelModeOne.getSlopeAngle());
            tempY -= MathTools.sin(systemModelModeOne.getSlopeAngle());

            if (tempX >= Constants.SIMULATION_WIDTH_PIXELS - (2 * boxWidth) || tempY <= boxHeight) {
                // Set the x and y value to the calculated in-bounds coordinates
                x = tempX;
                y = tempY;

                break;
            }
        }
    }
}