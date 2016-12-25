package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.Model;
import com.ahmad.Models.SlopeModel;
import com.ahmad.Models.WireModel;

public class SystemModel extends Model {
    private LeftBoxModel leftBox;
    private MiddleBoxModel middleBox;
    private RightBoxModel rightBox;

    public SlopeModel leftSlope;
    public SlopeModel rightSlope;

    private WireModel leftWire;
    private WireModel rightWire;

    private double frictionOfSystem;
    private double accelerationOfSystem;

    private double leftSlopeAngle;
    private double rightSlopeAngle;

    /*
    public SystemModel() {
        leftBox = new LeftBoxModel(this, 1, 0);
        middleBox = new MiddleBoxModel(this, 1);
        rightBox = new RightBoxModel(this, 1);

        leftSlope = new SlopeModel(this);
        rightSlope = new SlopeModel(this);
    }

    public void initializeConstantValues() {
        updateFriction();
        updateAcceleration();
        updateTension();
    }

    private void updateFriction() {
        frictionOfSystem = leftBox.getMass() * Constants.GRAVITY * MathTools.cos(leftSlopeAngle) * leftBox.getMu();
        //frictionOfSystem = 10;
    }

    // this method is here because we need access to all boxes to calculate accelerationOfSystem
    private void updateAcceleration() {
        accelerationOfSystem = (middleBox.getMass() * Constants.GRAVITY - leftBox.getMass() * Constants.GRAVITY * MathTools.sin(leftSlopeAngle) - frictionOfSystem)
                / (leftBox.getMass() + middleBox.getMass());

        Vector accelerationA = Vector.createFromPolar(accelerationOfSystem, leftSlopeAngle);
        Vector accelerationB = Vector.createFromPolar(accelerationOfSystem, -90);

        leftBox.setAcceleration(accelerationA);
        middleBox.setAcceleration(accelerationB);
    }

    private void updateTension() {
        leftWire = new WireModel(middleBox);
        leftWire.calculateTension(accelerationOfSystem);

        System.out.println("Tension: " + leftWire.tension);
    }

    public void setLeftSlopeAngle(double leftSlopeAngle) {
        this.leftSlopeAngle = leftSlopeAngle;

        leftBox.calculateCoordinates();
        middleBox.calculateCoordinates();
        leftSlope.calculateCoordinates();

        updateView();
    }

    public double getLeftSlopeAngle() {
        return leftSlopeAngle;
    }

    public double getAccelerationOfSystem() {
        return accelerationOfSystem;
    }

    public SlopedBoxModel getLeftBox() {
        return leftBox;
    }

    public DanglingBoxModel getMiddleBox() {
        return middleBox;
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
    */
}
