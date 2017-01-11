package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.Model;
import com.ahmad.Models.SystemModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;
import com.ahmad.Views.ModeTwo.MainViewModeTwo;
import com.ahmad.Views.View;

public class SystemModelModeTwo extends Model implements SystemModel {
    public LeftBoxModel leftBox;
    public MiddleBoxModel middleBox;
    public RightBoxModel rightBox;

    public LeftSlopeModel leftSlope;
    public MiddleSlopeModel middleSlope;
    public RightSlopeModel rightSlope;

    public LeftPulleyModel leftPulley;
    public RightPulleyModel rightPulley;

    public LeftWireModel leftWire;
    public RightWireModel rightWire;

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

        leftSlope = new LeftSlopeModel(this);
        middleSlope = new MiddleSlopeModel(this);
        rightSlope = new RightSlopeModel(this);

        leftPulley = new LeftPulleyModel(this);
        rightPulley = new RightPulleyModel(this);

        leftBox = new LeftBoxModel(this, 1, 0);
        middleBox = new MiddleBoxModel(this, 1, 0);
        rightBox = new RightBoxModel(this, 1, 0);

        leftWire = new LeftWireModel(this);
        rightWire = new RightWireModel(this);
    }

    @Override
    public void takeInputAndInitializeConstantValues(View mainView) {
        MainViewModeTwo mainViewModeTwo = (MainViewModeTwo) mainView;

        switch (mainViewModeTwo.inputTypeComboBox.getSelectedIndex()) {
            case 0:
                // FIRST SCENARIO
                // ALL MASSES ARE GIVEN, ALL MU'S ARE GIVEN, ALL ANGLES ARE GIVEN
                // SOLVE FOR: ACCELERATION, TENSION IN ALL WIRES (maybe solve for friction on the way?)

                leftBox.mass = Double.parseDouble(mainViewModeTwo.leftBoxMassTextField.getText());
                middleBox.mass = Double.parseDouble(mainViewModeTwo.middleBoxMassTextField.getText());
                rightBox.mass = Double.parseDouble(mainViewModeTwo.rightBoxMassTextField.getText());

                leftBox.mu = Double.parseDouble(mainViewModeTwo.leftBoxMuTextField.getText());
                middleBox.mu = Double.parseDouble(mainViewModeTwo.middleBoxMuTextField.getText());
                rightBox.mu = Double.parseDouble(mainViewModeTwo.rightBoxMuTextField.getText());

                leftSlopeAngle = Double.parseDouble(mainViewModeTwo.leftSlopeAngleTextField.getText());
                rightSlopeAngle = Double.parseDouble(mainViewModeTwo.rightSlopeAngleTextField.getText());

                updateAcceleration();

                break;
        }
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

        leftWire.updatePosition();
        rightWire.updatePosition();

        updateView();
    }

    // TODO: wrong calculation
    private void updateFriction() {
        frictionOfSystem = leftBox.getMass() * Constants.GRAVITY * MathTools.cos(leftSlopeAngle) * leftBox.getMu();
    }

    // TODO: wrong calculation
    private void updateAcceleration() {
        accelerationOfSystem = (
                Constants.GRAVITY * (
                        rightBox.mass * MathTools.sin(rightSlopeAngle)
                                - leftBox.mass * MathTools.sin(leftSlopeAngle)
                                - rightBox.getMu() * rightBox.getMass() * MathTools.cos(rightSlopeAngle)
                                - middleBox.getMu() * middleBox.getMass()
                                - leftBox.getMu() * leftBox.getMass() * MathTools.cos(leftSlopeAngle)
                )
        )
                / (leftBox.getMass() + middleBox.getMass() + rightBox.getMass());

        System.out.println(accelerationOfSystem);

        accelerationOfSystem = 0;

        setBoxAccelerations();
    }

    public void setBoxAccelerations() {
        Vector accelerationA = Vector.createFromPolar(accelerationOfSystem, leftSlopeAngle);
        Vector accelerationB = Vector.createFromPolar(accelerationOfSystem, middleSlopeAngle);
        Vector accelerationC = Vector.createFromPolar(accelerationOfSystem, rightSlopeAngle + 180);

        leftBox.setAcceleration(accelerationA);
        middleBox.setAcceleration(accelerationB);
        rightBox.setAcceleration(accelerationC);
    }

    /*
    private void updateTension() {
        leftWire = new WireModel(middleBox);
        leftWire.calculateTension(accelerationOfSystem);

        System.out.println("Tension: " + leftWire.tension);
    }
    */

    public void setLeftSlopeAngle(double leftSlopeAngle) {
        this.leftSlopeAngle = leftSlopeAngle;

        leftSlope.calculateCoordinates();
        leftPulley.calculateCoordinates();
        leftBox.calculateStartingPositionCoordinates();
        leftWire.updatePosition();

        repaintView();
    }

    public void setRightSlopeAngle(double rightSlopeAngle) {
        this.rightSlopeAngle = rightSlopeAngle;

        rightSlope.calculateCoordinates();
        rightPulley.calculateCoordinates();
        rightBox.calculateStartingPositionCoordinates();
        rightWire.updatePosition();

        repaintView();
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
