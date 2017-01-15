package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.Model;
import com.ahmad.Models.SystemModel;
import com.ahmad.Tools.Vector;
import com.ahmad.Views.ModeTwo.MainViewModeTwo;
import com.ahmad.Views.View;

public class SystemModelModeTwo extends Model implements SystemModel {
    public boolean isActive = false;

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
                leftBox.mass = Double.parseDouble(mainViewModeTwo.leftBoxMassTextField.getText());
                middleBox.mass = Double.parseDouble(mainViewModeTwo.middleBoxMassTextField.getText());
                rightBox.mass = Double.parseDouble(mainViewModeTwo.rightBoxMassTextField.getText());

                leftBox.mu = Double.parseDouble(mainViewModeTwo.leftBoxMuTextField.getText());
                middleBox.mu = Double.parseDouble(mainViewModeTwo.middleBoxMuTextField.getText());
                rightBox.mu = Double.parseDouble(mainViewModeTwo.rightBoxMuTextField.getText());

                leftSlopeAngle = Double.parseDouble(mainViewModeTwo.leftSlopeAngleTextField.getText());
                rightSlopeAngle = 180 - Double.parseDouble(mainViewModeTwo.rightSlopeAngleTextField.getText());

                leftBox.updateFriction(leftSlopeAngle);
                middleBox.updateFriction(middleSlopeAngle);
                rightBox.updateFriction(rightSlopeAngle);

                leftBox.updateXComponentOfGravitationalForce(leftSlopeAngle);
                rightBox.updateXComponentOfGravitationalForce(180 - rightSlopeAngle);

                updateAcceleration();

                leftWire.updateTension(leftBox.mass, accelerationOfSystem, leftSlopeAngle, leftBox.mu);
                rightWire.updateTension(rightBox.mass, accelerationOfSystem, rightSlopeAngle, rightBox.mu);
                break;
        }
    }

    public void iterate() {
        checkIfBoxesStillHaveRoomToMove();

        if (isActive) {
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
    }

    public void checkIfBoxesStillHaveRoomToMove() {
        if (leftBox.bottomRightCorner.getX() > leftPulley.topLeftCorner.getX() && leftBox.topRightCorner.getY() < (leftPulley.topLeftCorner.getY() + leftPulley.diameter)
                || rightBox.bottomLeftCorner.getX() < (rightPulley.topLeftCorner.getX() + leftPulley.diameter) && rightBox.topLeftCorner.getY() < (rightPulley.topLeftCorner.getY() + rightPulley.diameter)) {
            isActive = false;
        }
    }

    private void updateAcceleration() {
        double accelerationWithoutFriction = (
                rightBox.xComponentOfGravitationalForce - leftBox.xComponentOfGravitationalForce)
                / (leftBox.getMass() + middleBox.getMass() + rightBox.getMass());

        if (accelerationWithoutFriction == 0) {
            accelerationOfSystem = 0;
        } else if (accelerationWithoutFriction > 0) {
            accelerationOfSystem = (rightBox.xComponentOfGravitationalForce - leftBox.xComponentOfGravitationalForce
                    - leftBox.friction - rightBox.friction - middleBox.friction)
                    / (leftBox.getMass() + middleBox.getMass() + rightBox.getMass());

            // Friction only limits movement
            if (accelerationOfSystem < 0) {
                accelerationOfSystem = 0;
            }
        } else if (accelerationWithoutFriction < 0) {
            accelerationOfSystem = -(leftBox.xComponentOfGravitationalForce - rightBox.xComponentOfGravitationalForce
                    - leftBox.friction - middleBox.friction - rightBox.friction)
                    / (leftBox.getMass() + middleBox.getMass() + rightBox.getMass());

            // Friction only limits movement
            if (accelerationOfSystem > 0) {
                accelerationOfSystem = 0;
            }
        }

        setBoxAccelerations();
    }

    public void setBoxAccelerations() {
        Vector leftAcceleration = Vector.createFromPolar(accelerationOfSystem, leftSlopeAngle);
        Vector middleAcceleration = Vector.createFromPolar(accelerationOfSystem, middleSlopeAngle);
        Vector rightAcceleration = Vector.createFromPolar(accelerationOfSystem, rightSlopeAngle + 180);

        leftBox.setAcceleration(leftAcceleration);
        middleBox.setAcceleration(middleAcceleration);
        rightBox.setAcceleration(rightAcceleration);
    }

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
