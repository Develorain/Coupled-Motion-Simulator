package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.Model;
import com.ahmad.Models.SystemModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;
import com.ahmad.Views.View;

// TODO:
// draw slopes
// draw pulleys
// fix acceleration equation to use all 3 boxes
// add tension and wires
// add proper friction calculations

public class SystemModelModeTwo extends Model implements SystemModel {
    public LeftBoxModel leftBox;
    public MiddleBoxModel middleBox;
    public RightBoxModel rightBox;

    public LeftSlopeModel leftSlope;
    public MiddleSlopeModel middleSlope;
    public RightSlopeModel rightSlope;

    //private WireModel leftWire;
    //private WireModel rightWire;

    private double frictionOfSystem;
    private double accelerationOfSystem;

    private double leftSlopeAngle;
    private double middleSlopeAngle;
    private double rightSlopeAngle;

    public long simulationStartTime = 0;

    public SystemModelModeTwo() {
        leftSlopeAngle = 45;
        middleSlopeAngle = 0;
        rightSlopeAngle = 135;

        leftBox = new LeftBoxModel(this, 1, 0);
        middleBox = new MiddleBoxModel(this, 1, 0);
        rightBox = new RightBoxModel(this, 1, 0);

        leftSlope = new LeftSlopeModel(this);
        middleSlope = new MiddleSlopeModel(this);
        rightSlope = new RightSlopeModel(this);

        //leftWire = new WireModel();
        //rightWire = new WireModel();
    }

    @Override
    public void takeInputAndInitializeConstantValues(View mainView) {
        updateFriction();
        updateAcceleration();
        //updateTension();
    }

    public void iterate() {
        if (simulationStartTime == 0) {
            simulationStartTime = System.nanoTime();
        }

        double elapsedSeconds = (System.nanoTime() - simulationStartTime) / 1000000000.0;

        // Updates the boxes' velocities
        leftBox.updateVelocity(elapsedSeconds);
        middleBox.updateVelocity(elapsedSeconds);
        rightBox.updateVelocity(elapsedSeconds);

        // Updates the boxes' positions
        leftBox.updatePosition(elapsedSeconds);
        middleBox.updatePosition(elapsedSeconds);
        rightBox.updatePosition(elapsedSeconds);

        updateView();
    }

    private void updateFriction() {
        frictionOfSystem = leftBox.getMass() * Constants.GRAVITY * MathTools.cos(leftSlopeAngle) * leftBox.getMu();
        //frictionOfSlopedBox = 10;
    }

    // this method is here because we need access to all boxes to calculate accelerationOfSystem
    private void updateAcceleration() {
        accelerationOfSystem = (middleBox.getMass() * Constants.GRAVITY - leftBox.getMass() * Constants.GRAVITY * MathTools.sin(leftSlopeAngle) - frictionOfSystem)
                / (leftBox.getMass() + middleBox.getMass());

        Vector accelerationA = Vector.createFromPolar(accelerationOfSystem, leftSlopeAngle);
        Vector accelerationB = Vector.createFromPolar(accelerationOfSystem, middleSlopeAngle);
        Vector accelerationC = Vector.createFromPolar(accelerationOfSystem, rightSlopeAngle + 180);

        leftBox.setAcceleration(accelerationA);
        middleBox.setAcceleration(accelerationB);
        rightBox.setAcceleration(accelerationC);
    }

//    private void updateTension() {
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

    public void setRightSlopeAngle(double rightSlopeAngle) {
        this.rightSlopeAngle = rightSlopeAngle;

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

    public double getMiddleSlopeAngle() {
        return middleSlopeAngle;
    }

    public double getRightSlopeAngle() {
        return rightSlopeAngle;
    }

    public double getAccelerationOfSystem() {
        return accelerationOfSystem;
    }
}
