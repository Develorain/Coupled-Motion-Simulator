package com.ahmad.Models.ModeOne;

import com.ahmad.Models.Model;
import com.ahmad.Models.SlopeModel;
import com.ahmad.Models.WireModel;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public class SystemModel extends Model {
    private static final double GRAVITY = 9.8;

    private SlopedBoxModel boxA;
    private DanglingBoxModel boxB;

    public SlopeModel slopeA;

    private WireModel wireA;

    private double frictionSystem;
    private double acceleration;

    private double slopeAngle;

    public SystemModel() {
        boxA = new SlopedBoxModel(this, 1, 0);
        boxB = new DanglingBoxModel(this, 1);
        slopeA = new SlopeModel(this);

        setSlopeAngle(45);
    }

    public void initializeConstantValues() {
        updateFriction();
        updateAcceleration();
        updateTension();
    }

    private void updateFriction() {
        frictionSystem = boxA.getMass() * GRAVITY * MathTools.cos(slopeAngle) * boxA.getMu();
        //frictionSystem = 10;
    }

    // this method is here because we need access to all boxes to calculate acceleration
    private void updateAcceleration() {
        acceleration = (boxB.getMass() * GRAVITY - boxA.getMass() * GRAVITY * MathTools.sin(slopeAngle) - frictionSystem)
                / (boxA.getMass() + boxB.getMass());

        Vector accelerationA = Vector.createFromPolar(acceleration, slopeAngle);
        Vector accelerationB = Vector.createFromPolar(acceleration, -90);

        boxA.setAcceleration(accelerationA);
        boxB.setAcceleration(accelerationB);
    }

    private void updateTension() {
        wireA = new WireModel(boxB);
        wireA.calculateTension(acceleration);

        System.out.println("Tension: " + wireA.tension);
    }

    public void setSlopeAngle(double slopeAngle) {
        this.slopeAngle = slopeAngle;
        boxA.calculateCoordinates();
        boxB.calculateCoordinates();
        slopeA.calculateCoordinates();
    }

    public double getSlopeAngle() {
        return slopeAngle;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public SlopedBoxModel getBoxA() {
        return boxA;
    }

    public DanglingBoxModel getBoxB() {
        return boxB;
    }

    public void iterate() {
        // Updates the boxes' velocities
        boxA.updateVelocity();
        boxB.updateVelocity();

        // Updates the boxes' positions
        boxA.updatePosition();
        boxB.updatePosition();

        updateView();
    }
}
