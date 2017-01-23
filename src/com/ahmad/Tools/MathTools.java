package com.ahmad.Tools;

/** MathTools
 * Utility class to provide trigonometric functions in degrees
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

public class MathTools {
    /** Sine function in degrees
     * @param angleDegrees angle in degrees
     * @return the sine of the angle in degrees */
    public static double sin(double angleDegrees) {
        return Math.sin(Math.toRadians(angleDegrees));
    }

    /** Consine function in degrees
     * @param angleDegrees angle in degrees
     * @return the cosine of the angle in degrees */
    public static double cos(double angleDegrees) {
        return Math.cos(Math.toRadians(angleDegrees));
    }
}
