package com.ahmad.Models.ModeOne;

import com.ahmad.Models.Model;
import com.ahmad.Models.SystemModel;
import com.ahmad.Models.WireModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public class SystemModelModeOne extends Model implements SystemModel {
    public SlopedBoxModel slopedBox;
    public DanglingBoxModel danglingBox;

    public SlopeModelModeOne slope;

    public WireModel wire;

    public double accelerationOfSystem;

    public double slopeAngle;

    public long simulationStartTime = 0;

    public SystemModelModeOne() {
        slopeAngle = 45;

        slopedBox = new SlopedBoxModel(this, 1, 0);
        danglingBox = new DanglingBoxModel(this, 1);
        slope = new SlopeModelModeOne(this);
        wire = new WireModel(this);
    }

    public void initializeConstantValues(int scenario) {
        switch (scenario) {
            case 1:
                slopedBox.updateFriction(slopedBox.getMass(), slopedBox.getMu(), slopeAngle);
                updateAcceleration(slopedBox.getMass(), danglingBox.getMass(), slopedBox.friction, slopeAngle);
                updateTension(danglingBox.getMass(), accelerationOfSystem);
                break;

            case 2:
                updateAcceleration(wire.tension, danglingBox.getMass());
                updateMassLeft(slopeAngle, slopedBox.getMu(), slopedBox.friction);
                break;

            case 3:
                break;
        }
    }

    private void updateMassLeft(double angle, double muLeft, double friction) {
        slopedBox.mass = friction / (muLeft * Constants.GRAVITY * MathTools.cos(angle));
    }

    private void updateMassRight(double tension, double acceleration) {
        danglingBox.mass = tension / (Constants.GRAVITY - acceleration);
    }

    private void updateAcceleration(double massLeft, double massRight, double friction, double angle) {
        double accelerationOfSystemWithoutFriction = (massRight * Constants.GRAVITY - massLeft * Constants.GRAVITY * MathTools.sin(angle)) / (massLeft + massRight);

        if (accelerationOfSystemWithoutFriction == 0) {
            accelerationOfSystem = 0;
        } else if (accelerationOfSystemWithoutFriction > 0) {
            accelerationOfSystem = (massRight * Constants.GRAVITY - massLeft * Constants.GRAVITY * MathTools.sin(angle) - friction) / (massLeft + massRight);

            // Friction only limits motion
            if (accelerationOfSystem < 0) {
                accelerationOfSystem = 0;
            }

        } else if (accelerationOfSystemWithoutFriction < 0) {
            accelerationOfSystem = (massRight * Constants.GRAVITY - massLeft * Constants.GRAVITY * MathTools.sin(angle) + friction) / (massLeft + massRight);

            // Friction only limits motion
            if (accelerationOfSystem > 0) {
                accelerationOfSystem = 0;
            }
        }

        setBoxAccelerations();
    }

    private void updateAcceleration(double tension, double massRight) {
        accelerationOfSystem = (massRight * Constants.GRAVITY - tension) / massRight;
        setBoxAccelerations();
    }

    private void setBoxAccelerations() {
        Vector accelerationLeft = Vector.createFromPolar(accelerationOfSystem, slopeAngle);
        Vector accelerationRight = Vector.createFromPolar(accelerationOfSystem, -90);

        slopedBox.setAcceleration(accelerationLeft);
        danglingBox.setAcceleration(accelerationRight);
    }

    private void updateTension(double massRight, double acceleration) {
        wire.calculateTension(massRight, acceleration);
    }

    public void iterate() {
        if (simulationStartTime == 0) {
            simulationStartTime = System.nanoTime();
        }

        double elapsedSeconds = (System.nanoTime() - simulationStartTime) / 1000000000.0;

        //System.out.println(elapsedSeconds);

        // Updates the boxes' velocities
        slopedBox.updateVelocity(elapsedSeconds);
        danglingBox.updateVelocity(elapsedSeconds);

        // Updates the boxes' positions
        slopedBox.updatePosition(elapsedSeconds);
        danglingBox.updatePosition(elapsedSeconds);

        updateView();
    }

    public void setSlopeAngle(double slopeAngle) {
        this.slopeAngle = slopeAngle;

        slopedBox.calculateCoordinates();
        danglingBox.calculateCoordinates();
        slope.calculateCoordinates();
        wire.calculateCoordinates();

        repaintView();
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
}
