package com.ahmad.Models.ModeOne;

import com.ahmad.Models.Model;
import com.ahmad.Models.PulleyModelModeOne;
import com.ahmad.Models.SystemModel;
import com.ahmad.Models.WireModelModeOne;
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
    public long simulationStartTime = 0;

    public SystemModelModeOne() {
        slopeAngle = 45;

        slope = new SlopeModelModeOne(this);
        pulley = new PulleyModelModeOne(this);

        slopedBox = new SlopedBoxModel(this, 1, 0);
        danglingBox = new DanglingBoxModel(this, 1);

        wire = new WireModelModeOne(this);
    }

    public void takeInputAndInitializeConstantValues(View mainView) {
        // Take input

        MainViewModeOne mainViewModeOne = (MainViewModeOne) mainView;

        int scenario = -1;

        if (!mainViewModeOne.leftBoxMassTextField.getText().isEmpty() && !mainViewModeOne.rightBoxMassTextField.getText().isEmpty()
                && !mainViewModeOne.leftBoxMuTextField.getText().isEmpty() && !mainViewModeOne.leftSlopeAngleTextField.getText().isEmpty()) {
            scenario = 1;

            getSlopedBox().mass = Double.parseDouble(mainViewModeOne.leftBoxMassTextField.getText());
            getDanglingBox().mass = Double.parseDouble(mainViewModeOne.rightBoxMassTextField.getText());
            getSlopedBox().mu = Double.parseDouble(mainViewModeOne.leftBoxMuTextField.getText());
            slopeAngle = Double.parseDouble(mainViewModeOne.leftSlopeAngleTextField.getText());
        } else if (!mainViewModeOne.rightBoxMassTextField.getText().isEmpty() && !mainViewModeOne.tensionTextField.getText().isEmpty()
                && !mainViewModeOne.leftSlopeAngleTextField.getText().isEmpty() && !mainViewModeOne.leftBoxFrictionTextField.getText().isEmpty()
                && !mainViewModeOne.leftBoxMuTextField.getText().isEmpty()) {
            scenario = 2;

            getDanglingBox().mass = Double.parseDouble(mainViewModeOne.rightBoxMassTextField.getText());
            wire.tension = Double.parseDouble(mainViewModeOne.tensionTextField.getText());
            slopeAngle = Double.parseDouble(mainViewModeOne.leftSlopeAngleTextField.getText());
            slopedBox.friction = Double.parseDouble(mainViewModeOne.leftBoxFrictionTextField.getText());
            getSlopedBox().mu = Double.parseDouble(mainViewModeOne.leftBoxMuTextField.getText());
        } else if (!mainViewModeOne.accelerationTextField.getText().isEmpty() && !mainViewModeOne.tensionTextField.getText().isEmpty()
                && !mainViewModeOne.leftSlopeAngleTextField.getText().isEmpty() && !mainViewModeOne.leftBoxMassTextField.getText().isEmpty()) {
            scenario = 3;

            accelerationOfSystem = Double.parseDouble(mainViewModeOne.accelerationTextField.getText());
            wire.tension = Double.parseDouble(mainViewModeOne.tensionTextField.getText());
            slopeAngle = Double.parseDouble(mainViewModeOne.leftSlopeAngleTextField.getText());
            getSlopedBox().mass = Double.parseDouble(mainViewModeOne.leftBoxMassTextField.getText());
        } else {
            System.out.println("Missing input");
        }


        // Initialize constant values
        switch (scenario) {
            case 1:
                slopedBox.updateFriction(slopeAngle);
                updateAcceleration(slopedBox.getMass(), danglingBox.getMass(), slopedBox.friction, slopeAngle);
                wire.updateTension(danglingBox.getMass(), accelerationOfSystem);
                break;

            case 2:
                updateAcceleration(wire.tension, danglingBox.getMass());
                slopedBox.updateMass(slopeAngle);
                break;

            case 3:
                setBoxAccelerations();

                danglingBox.updateMass(wire.tension, accelerationOfSystem);
                slopedBox.updateFriction(danglingBox.getMass(), accelerationOfSystem, slopeAngle);
                slopedBox.updateMu(slopeAngle);
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
        wire.calculateCoordinates();

        updateView();
    }

    public void setSlopeAngle(double slopeAngle) {
        this.slopeAngle = slopeAngle;

        slope.calculateCoordinates();
        pulley.calculateCoordinates();

        slopedBox.calculateStartingPositionCoordinates();
        danglingBox.calculateStartingPositionCoordinates();
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
