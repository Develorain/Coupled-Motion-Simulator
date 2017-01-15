package com.ahmad.Tools;

public class Vector {
    private double x;
    private double y;

    public static Vector createFromCartesian(double x, double y) {
        Vector vector = new Vector();
        vector.setX(x);
        vector.setY(y);

        return vector;
    }

    public static Vector createFromPolar(double r, double theta) {
        Vector vector = new Vector();
        vector.setRAndTheta(r, theta);

        return vector;
    }

    public double getR() {
        return Math.sqrt(x * x + y * y);
    }

    public double getTheta() {
        return Math.atan(y / x);
    }

    public void setRAndTheta(double r, double theta) {
        x = r * MathTools.cos(theta);
        y = r * MathTools.sin(theta);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
