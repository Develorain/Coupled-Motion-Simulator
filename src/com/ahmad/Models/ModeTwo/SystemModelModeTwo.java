package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.Model;
import com.ahmad.Tools.Globals;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public class SystemModelModeTwo extends Model {
    private LeftBoxModel leftBox;
    private MiddleBoxModel middleBox;
    private RightBoxModel rightBox;

    private LeftSlopeModel leftSlope;
    private MiddleSlopeModel middleSlope;
    private RightSlopeModel rightSlope;

    //private WireModel leftWire;
    //private WireModel rightWire;

    private double frictionOfSystem;
    private double accelerationOfSystem;

    private double leftSlopeAngle;
    private double rightSlopeAngle;

    public SystemModelModeTwo() {
        leftSlopeAngle = 45;
        rightSlopeAngle = 45;

        leftBox = new LeftBoxModel(this, 1, 0);
        middleBox = new MiddleBoxModel(this, 1, 0);
        rightBox = new RightBoxModel(this, 1, 0);

        leftSlope = new LeftSlopeModel();
        middleSlope = new MiddleSlopeModel();
        rightSlope = new RightSlopeModel();

        //leftWire = new WireModel();
        //rightWire = new WireModel();
    }

    public void initializeConstantValues() {
        updateFriction();
        updateAcceleration();
        //updateTension();
    }

    private void updateFriction() {
        frictionOfSystem = leftBox.getMass() * Globals.GRAVITY * MathTools.cos(leftSlopeAngle) * leftBox.getMu();
        //frictionOfSystem = 10;
    }

    // this method is here because we need access to all boxes to calculate accelerationOfSystem
    private void updateAcceleration() {
        accelerationOfSystem = (middleBox.getMass() * Globals.GRAVITY - leftBox.getMass() * Globals.GRAVITY * MathTools.sin(leftSlopeAngle) - frictionOfSystem)
                / (leftBox.getMass() + middleBox.getMass());

        Vector accelerationA = Vector.createFromPolar(accelerationOfSystem, leftSlopeAngle);
        Vector accelerationB = Vector.createFromPolar(accelerationOfSystem, -90);

        leftBox.setAcceleration(accelerationA);
        middleBox.setAcceleration(accelerationB);
    }

    //private void updateTension() {
//        leftWire = new WireModel(middleBox);
//        leftWire.calculateTension(accelerationOfSystem);
//
//        System.out.println("Tension: " + leftWire.tension);
//    }

    public void setLeftSlopeAngle(double leftSlopeAngle) {
        this.leftSlopeAngle = leftSlopeAngle;

        leftBox.calculateCoordinates();
        middleBox.calculateCoordinates();
        rightBox.calculateCoordinates();

        leftSlope.calculateCoordinates();
        middleSlope.calculateCoordinates();
        rightSlope.calculateCoordinates();

        updateView();
    }

    public double getLeftSlopeAngle() {
        return leftSlopeAngle;
    }

    public double getRightSlopeAngle() {
        return rightSlopeAngle;
    }

    public double getAccelerationOfSystem() {
        return accelerationOfSystem;
    }

    public void iterate() {
        // Updates the boxes' velocities
        leftBox.updateVelocity();
        middleBox.updateVelocity();

        // Updates the boxes' positions
        leftBox.updatePosition();
        middleBox.updatePosition();

        updateView();
    }
}
