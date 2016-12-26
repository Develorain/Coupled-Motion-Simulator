package com.ahmad.Models.ModeOne;

import com.ahmad.Models.Model;
import com.ahmad.Models.SystemModel;
import com.ahmad.Models.WireModel;
import com.ahmad.Tools.Globals;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public class SystemModelModeOne extends Model implements SystemModel {
    private SlopedBoxModel slopedBox;
    private DanglingBoxModel danglingBox;

    public SlopeModelModeOne slope;

    private WireModel wire;

    private double frictionOfSystem;
    private double accelerationOfSystem;

    private double slopeAngle;

    public SystemModelModeOne() {
        slopeAngle = 45;

        slopedBox = new SlopedBoxModel(this, 1, 0);
        danglingBox = new DanglingBoxModel(this, 1);
        slope = new SlopeModelModeOne(this);
        wire = new WireModel(danglingBox);
    }

    public void initializeConstantValues() {
        updateFriction();
        updateAcceleration();
        updateTension();
    }

    private void updateFriction() {
        frictionOfSystem = slopedBox.getMass() * Globals.GRAVITY * MathTools.cos(slopeAngle) * slopedBox.getMu();
        //frictionOfSystem = 10;
    }

    // this method is here because we need access to all boxes to calculate accelerationOfSystem
    private void updateAcceleration() {
        accelerationOfSystem = (danglingBox.getMass() * Globals.GRAVITY - slopedBox.getMass() * Globals.GRAVITY * MathTools.sin(slopeAngle) - frictionOfSystem)
                / (slopedBox.getMass() + danglingBox.getMass());

        Vector accelerationA = Vector.createFromPolar(accelerationOfSystem, slopeAngle);
        Vector accelerationB = Vector.createFromPolar(accelerationOfSystem, -90);

        slopedBox.setAcceleration(accelerationA);
        danglingBox.setAcceleration(accelerationB);
    }

    private void updateTension() {
        wire.calculateTension(accelerationOfSystem);

        System.out.println("Tension: " + wire.tension);
    }

    public void setSlopeAngle(double slopeAngle) {
        this.slopeAngle = slopeAngle;

        slopedBox.calculateCoordinates();
        danglingBox.calculateCoordinates();
        slope.calculateCoordinates();

        updateView();
    }

    public double getSlopeAngle() {
        return slopeAngle;
    }

    public double getAccelerationOfSystem() {
        return accelerationOfSystem;
    }

    public SlopedBoxModel getSlopedBox() {
        return slopedBox;
    }

    public DanglingBoxModel getDanglingBox() {
        return danglingBox;
    }

    public void iterate() {
        // Updates the boxes' velocities
        slopedBox.updateVelocity();
        danglingBox.updateVelocity();

        // Updates the boxes' positions
        slopedBox.updatePosition();
        danglingBox.updatePosition();

        updateView();
    }
}
