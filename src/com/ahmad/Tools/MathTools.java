package com.ahmad.Tools;

public class MathTools {
    public static final double DELTA_TIME_SECONDS = 1.0 / 60.0;

    public static double sin(double angleDegrees) {
        return Math.sin(Math.toRadians(angleDegrees));
    }

    public static double cos(double angleDegrees) {
        return Math.cos(Math.toRadians(angleDegrees));
    }

    public static double tan(double angleDegrees) {
        return Math.tan(Math.toRadians(angleDegrees));
    }

    public static double asin(double angleDegrees) {
        return Math.asin(Math.toRadians(angleDegrees));
    }

    public static double acos(double angleDegrees) {
        return Math.acos(Math.toRadians(angleDegrees));
    }

    public static double atan(double angleDegrees) {
        return Math.atan(Math.toRadians(angleDegrees));
    }
}
