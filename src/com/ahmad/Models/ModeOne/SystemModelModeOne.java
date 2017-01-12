package com.ahmad.Models.ModeOne;

import com.ahmad.Models.Model;
import com.ahmad.Models.SystemModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;
import com.ahmad.Views.ModeOne.MainViewModeOne;
import com.ahmad.Views.View;

public class SystemModelModeOne extends Model implements SystemModel {
    public SlopeModelModeOne slope;
    public PulleyModelModeOne pulley;

    public SlopedBoxModel slopedBox;
    public DanglingBoxModel danglingBox;

    public WireModelModeOne wire;

    public double accelerationOfSystem;
    public double slopeAngle;
    public long simulationStartTime;

    public SystemModelModeOne() {
        slopeAngle = 45;
        simulationStartTime = 0;

        slope = new SlopeModelModeOne(this);
        pulley = new PulleyModelModeOne(this);

        slopedBox = new SlopedBoxModel(this, 1, 0);
        danglingBox = new DanglingBoxModel(this, 1);

        wire = new WireModelModeOne(this);
    }

    public void takeInputAndInitializeConstantValues(View mainView) {
        MainViewModeOne mainViewModeOne = (MainViewModeOne) mainView;

        switch (mainViewModeOne.inputTypeComboBox.getSelectedIndex()) {
            case 0:
                slopedBox.mass = Double.parseDouble(mainViewModeOne.leftBoxMassTextField.getText());
                danglingBox.mass = Double.parseDouble(mainViewModeOne.rightBoxMassTextField.getText());
                slopedBox.mu = Double.parseDouble(mainViewModeOne.leftBoxMuTextField.getText());
                slopeAngle = Double.parseDouble(mainViewModeOne.leftSlopeAngleTextField.getText());

                slopedBox.updateFriction(slopeAngle);
                updateAcceleration(slopedBox.getMass(), danglingBox.getMass(), slopedBox.friction, slopeAngle); // TODO: there is no point of passing it in as parameters
                wire.updateTension(danglingBox.getMass(), accelerationOfSystem);
                break;

            case 1:
                wire.tension = Double.parseDouble(mainViewModeOne.tensionTextField.getText());
                slopedBox.mu = Double.parseDouble(mainViewModeOne.leftBoxMuTextField.getText());
                slopeAngle = Double.parseDouble(mainViewModeOne.leftSlopeAngleTextField.getText());
                danglingBox.mass = Double.parseDouble(mainViewModeOne.rightBoxMassTextField.getText());

                slopedBox.updateFriction(slopeAngle);
                updateAcceleration(wire.tension, danglingBox.getMass());
                slopedBox.updateMass(slopeAngle, danglingBox.mass, accelerationOfSystem, slopedBox.mu);
                break;
        }
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
            accelerationOfSystem = (massLeft * Constants.GRAVITY * MathTools.sin(angle) - friction - massRight * Constants.GRAVITY) / (massLeft + massRight);

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

    public void iterate() {
        if (simulationStartTime == 0) {
            simulationStartTime = System.nanoTime();
        }

        double elapsedSeconds = (System.nanoTime() - simulationStartTime) / 1000000000.0;

        // Updates the boxes' velocities
        slopedBox.updateVelocity(elapsedSeconds);
        danglingBox.updateVelocity(elapsedSeconds);

        // Updates the boxes' positions
        slopedBox.updatePosition(elapsedSeconds);
        danglingBox.updatePosition(elapsedSeconds);

        // Updates the wire's coordinates
        wire.updatePosition();

        updateView();
    }

    public void setSlopeAngle(double slopeAngle) {
        this.slopeAngle = slopeAngle;

        slope.calculateCoordinates();
        pulley.calculateCoordinates();

        slopedBox.calculateStartingPositionCoordinates();
        danglingBox.calculateStartingPositionCoordinates();

        wire.updatePosition();

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
