package com.ahmad.Models.ModeOne;

/** WireModelModeOne
 * Performs the calculations for wire in system in mode one
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.WireModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class WireModelModeOne extends WireModel {
    private SystemModelModeOne systemModelModeOne;  // Declare the system model

    /** Default Constructor
     * @param systemModelModeOne  a reference to the system model */
    public WireModelModeOne(SystemModelModeOne systemModelModeOne) {
        this.systemModelModeOne = systemModelModeOne;

        updatePosition();
    }

    /** Updates the position of the two coordinates of the wire */
    @Override
    public void updatePosition() {
        leftWireLeftCoord.setX((systemModelModeOne.getSlopedBox().getTopRightCorner().getX() + systemModelModeOne.getSlopedBox().getBottomRightCorner().getX()) / 2);
        leftWireLeftCoord.setY((systemModelModeOne.getSlopedBox().getTopRightCorner().getY() + systemModelModeOne.getSlopedBox().getBottomRightCorner().getY()) / 2);

        leftWireRightCoord.setX(systemModelModeOne.getPulley().getTopLeftCorner().getX() + systemModelModeOne.getPulley().getRadius() - systemModelModeOne.getPulley().getRadius() * MathTools.sin(systemModelModeOne.getSlopeAngle()));
        leftWireRightCoord.setY(systemModelModeOne.getPulley().getTopLeftCorner().getY() + systemModelModeOne.getPulley().getRadius() - systemModelModeOne.getPulley().getRadius() * MathTools.cos(systemModelModeOne.getSlopeAngle()));

        rightWireLeftCoord.setX(systemModelModeOne.getPulley().getTopLeftCorner().getX() + systemModelModeOne.getPulley().getDiameter());
        rightWireLeftCoord.setY(systemModelModeOne.getPulley().getTopLeftCorner().getY() + systemModelModeOne.getPulley().getRadius());

        rightWireRightCoord.setX((systemModelModeOne.getDanglingBox().getTopLeftCorner().getX() + systemModelModeOne.getDanglingBox().getTopRightCorner().getX()) / 2);
        rightWireRightCoord.setY(systemModelModeOne.getDanglingBox().getTopLeftCorner().getY());
    }

    /** Updates the tension of the wire
     * @param massRight    the mass the box is attached to on the right
     * @param acceleration the acceleration of the box system */
    public void updateTension(double massRight, double acceleration) {
        if (acceleration > 0) {
            tension = massRight * (Constants.GRAVITY - acceleration);
        } else if (acceleration < 0) {
            tension = massRight * (acceleration + Constants.GRAVITY);
        }
    }
}