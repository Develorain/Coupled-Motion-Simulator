package com.ahmad.Models;

/** WireModel
 * Performs the calculations for the wire in system
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Tools.Vector;

public abstract class WireModel {
    protected Vector leftWireLeftCoord;   // Declare the the left coordinate of the first wire (on the left)
    protected Vector leftWireRightCoord;  // Declare the the right coordinate of the first wire (on the left)

    protected Vector rightWireLeftCoord;  // Declare the the left coordinate of the second wire (on the right)
    protected Vector rightWireRightCoord; // Declare the the right coordinate of the second wire (on the right)

    protected double tension;   // Declare the tension

    /** Default Constructor */
    public WireModel() {
        leftWireLeftCoord = Vector.createFromCartesian(0, 0);
        leftWireRightCoord = Vector.createFromCartesian(0, 0);
        rightWireLeftCoord = Vector.createFromCartesian(0, 0);
        rightWireRightCoord = Vector.createFromCartesian(0, 0);
    }

    /** Updates the position of the wire */
    protected abstract void updatePosition();

    /** Returns the left wire's left coordinate
     * @return the left wire's left coordinate */
    public Vector getLeftWireLeftCoord() {
        return leftWireLeftCoord;
    }

    /** Returns the left wire's right coordinate
     * @return the left wire's right coordinate */
    public Vector getLeftWireRightCoord() {
        return leftWireRightCoord;
    }

    /** Returns the right wire's left coordinate
     * @return the right wire's left coordinate */
    public Vector getRightWireLeftCoord() {
        return rightWireLeftCoord;
    }

    /** Returns the right wire's right coordinate
     * @return the right wire's right coordinate */
    public Vector getRightWireRightCoord() {
        return rightWireRightCoord;
    }

    /** Returns the tension of the wire
     * @return the tension of the wire */
    public double getTension() {
        return tension;
    }

    /** Sets the tension of the wire to the value provided
     * @param tension the new tension value */
    public void setTension(double tension) {
        this.tension = tension;
    }
}
