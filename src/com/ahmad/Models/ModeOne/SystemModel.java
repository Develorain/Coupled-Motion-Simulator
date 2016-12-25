package com.ahmad.Models.ModeOne;

import com.ahmad.Models.Model;
import com.ahmad.Models.SlopeModel;
import com.ahmad.Models.WireModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public class SystemModel extends Model {
    private SlopedBoxModel boxA;
    private DanglingBoxModel boxB;

    public SlopeModel slopeA;
    private WireModel wireA;

    private double frictionOfSystem;
    private double accelerationOfSystem;

    private double slopeAngle;

    public SystemModel() {
        boxA = new SlopedBoxModel(this, 1, 0);
        boxB = new DanglingBoxModel(this, 1);
        slopeA = new SlopeModel(this);
    }

    public void initializeConstantValues() {
        updateFriction();
        updateAcceleration();
        updateTension();
    }

    private void updateFriction() {
        frictionOfSystem = boxA.getMass() * Constants.GRAVITY * MathTools.cos(slopeAngle) * boxA.getMu();
        //frictionOfSystem = 10;
    }

    // this method is here because we need access to all boxes to calculate accelerationOfSystem
    private void updateAcceleration() {
        accelerationOfSystem = (boxB.getMass() * Constants.GRAVITY - boxA.getMass() * Constants.GRAVITY * MathTools.sin(slopeAngle) - frictionOfSystem)
                / (boxA.getMass() + boxB.getMass());

        Vector accelerationA = Vector.createFromPolar(accelerationOfSystem, slopeAngle);
        Vector accelerationB = Vector.createFromPolar(accelerationOfSystem, -90);

        boxA.setAcceleration(accelerationA);
        boxB.setAcceleration(accelerationB);
    }

    private void updateTension() {
        wireA = new WireModel(boxB);
        wireA.calculateTension(accelerationOfSystem);

        System.out.println("Tension: " + wireA.tension);
    }

    public void setSlopeAngle(double slopeAngle) {
        this.slopeAngle = slopeAngle;

        boxA.calculateCoordinates();
        boxB.calculateCoordinates();
        slopeA.calculateCoordinates();

        updateView();
    }

    public double getSlopeAngle() {
        return slopeAngle;
    }

    public double getAccelerationOfSystem() {
        return accelerationOfSystem;
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
