package com.ahmad.Models.ModeOne;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.MathTools;

public class DanglingBoxModel extends BoxModel {
    public DanglingBoxModel(SystemModel systemModel, double mass, double mu) {
        super(systemModel, mass, mu);
    }

    public void calculateCoordinates() {
        double tempX = 600;
        double tempY = 350;

        while (true) {
            tempX += MathTools.cos(systemModel.getSlopeAngle());
            tempY -= MathTools.sin(systemModel.getSlopeAngle());

            if (tempX <= 100 || tempX >= 1000 || tempY <= 100 || tempY >= 600) {
                // Set the x and y value to the calculated in-bounds coordinates
                x = tempX;
                y = tempY;

                break;
            }
        }
    }
}