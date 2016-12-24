package com.ahmad.Models.ModeOne;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class DanglingBoxModel extends BoxModel {
    public DanglingBoxModel(SystemModel systemModel, double mass) {
        super(systemModel, mass);
    }

    public void calculateCoordinates() {
        double tempX = Constants.SIMULATION_WIDTH_PIXELS / 2;
        double tempY = Constants.SIMULATION_HEIGHT_PIXELS / 2;

        while (true) {
            tempX += MathTools.cos(systemModel.getSlopeAngle());
            tempY -= MathTools.sin(systemModel.getSlopeAngle());

            if (tempX >= Constants.SIMULATION_WIDTH_PIXELS - (2 * boxWidth) || tempY <= boxHeight) {
                // Set the x and y value to the calculated in-bounds coordinates
                x = tempX;
                y = tempY;

                break;
            }
        }
    }
}