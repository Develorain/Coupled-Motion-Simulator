package com.ahmad.Models.ModeTwo;

/** SystemModelModeTwo
 * Performs the calculations for the box system in mode two
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.Model;
import com.ahmad.Models.PulleyModel;
import com.ahmad.Models.SystemModel;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;
import com.ahmad.Views.ModeTwo.MainViewModeTwo;
import com.ahmad.Views.View;

public class SystemModelModeTwo extends Model implements SystemModel {
    private boolean isActive = false;      // Whether the simulation is running or not

    private LeftBoxModel leftBox;          // Declare the left box model
    private MiddleBoxModel middleBox;      // Declare the middle box model
    private RightBoxModel rightBox;        // Declare the right box model

    private LeftSlopeModel leftSlope;      // Declare the left slope model
    private MiddleSlopeModel middleSlope;  // Declare the middle slope model
    private RightSlopeModel rightSlope;    // Declare the right slope model

    private PulleyModel leftPulley;        // Declare the left pulley model
    private PulleyModel rightPulley;       // Declare the right pulley model

    private LeftWireModel leftWire;        // Declare the left wire model
    private RightWireModel rightWire;      // Declare the right wire model

    private double accelerationOfSystem;   // Declare the acceleration of all the boxes in the box system
    private double leftSlopeAngle;         // Declare the angle of the left slope
    private double middleSlopeAngle;       // Declare the angle of the middle slope
    private double rightSlopeAngle;        // Declare the angle of the right slope
    private long simulationStartTime;      // Declare the time when the simulation starts

    /** Default Constructor */
    public SystemModelModeTwo() {
        leftSlopeAngle = 45;
        middleSlopeAngle = 0;
        rightSlopeAngle = 135;

        simulationStartTime = 0;

        leftSlope = new LeftSlopeModel(this);
        middleSlope = new MiddleSlopeModel();
        rightSlope = new RightSlopeModel(this);

        leftPulley = createLeftPulley();
        rightPulley = createRightPulley();

        leftBox = new LeftBoxModel(this, 1, 0);
        middleBox = new MiddleBoxModel(this, 1, 0);
        rightBox = new RightBoxModel(this, 1, 0);

        leftWire = new LeftWireModel(this);
        rightWire = new RightWireModel(this);
    }

    /** Takes input from the view and calls the appropriate methods to initialize constant values
     * @param mainView a reference to the main view */
    @Override
    public void takeInputAndInitializeConstantValues(View mainView) {
        MainViewModeTwo mainViewModeTwo = (MainViewModeTwo) mainView;

        leftBox.setMass(Double.parseDouble(mainViewModeTwo.getLeftBoxMassTextField().getText()));
        middleBox.setMass(Double.parseDouble(mainViewModeTwo.getMiddleBoxMassTextField().getText()));
        rightBox.setMass(Double.parseDouble(mainViewModeTwo.getRightBoxMassTextField().getText()));

        leftBox.setMu(Double.parseDouble(mainViewModeTwo.getLeftBoxMuTextField().getText()));
        middleBox.setMu(Double.parseDouble(mainViewModeTwo.getMiddleBoxMuTextField().getText()));
        rightBox.setMu(Double.parseDouble(mainViewModeTwo.getRightBoxMuTextField().getText()));

        leftSlopeAngle = Double.parseDouble(mainViewModeTwo.getLeftSlopeAngleTextField().getText());
        rightSlopeAngle = 180 - Double.parseDouble(mainViewModeTwo.getRightSlopeAngleTextField().getText());

        leftBox.updateFriction(leftSlopeAngle);
        middleBox.updateFriction(middleSlopeAngle);
        rightBox.updateFriction(rightSlopeAngle);

        leftBox.updateGravForce(leftSlopeAngle);
        rightBox.updateGravForce(180 - rightSlopeAngle);

        updateAcceleration();

        leftWire.updateTension(leftBox.getMass(), accelerationOfSystem, leftSlopeAngle, leftBox.getMu());
        rightWire.updateTension(rightBox.getMass(), accelerationOfSystem, 180 - rightSlopeAngle, rightBox.getMu());
    }

    /** Iterates the physics in the system */
    @Override
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

    /** Sets the simulation to active */
    @Override
    public void setActive() {
        isActive = true;
    }

    /** Checks if the any of the boxes within the box system has moved off screen or has hit a pulley */
    private void checkIfBoxesStillHaveRoomToMove() {
        if (leftBox.getBottomRightCorner().getX() > leftPulley.getTopLeftCorner().getX() && leftBox.getTopRightCorner().getY() < (leftPulley.getTopLeftCorner().getY() + leftPulley.getDiameter())
                || rightBox.getBottomLeftCorner().getX() < (rightPulley.getTopLeftCorner().getX() + leftPulley.getDiameter()) && rightBox.getTopLeftCorner().getY() < (rightPulley.getTopLeftCorner().getY() + rightPulley.getDiameter())) {
            isActive = false;
        }
    }

    /** Calculate the box system acceleration using the known data from the input */
    private void updateAcceleration() {
        double accelerationWithoutFriction = (
                rightBox.getGravForce() - leftBox.getGravForce())
                / (leftBox.getMass() + middleBox.getMass() + rightBox.getMass());

        if (accelerationWithoutFriction == 0) {
            accelerationOfSystem = 0;
        } else if (accelerationWithoutFriction > 0) {
            accelerationOfSystem = (rightBox.getGravForce() - leftBox.getGravForce()
                    - leftBox.getFriction() - rightBox.getFriction() - middleBox.getFriction())
                    / (leftBox.getMass() + middleBox.getMass() + rightBox.getMass());

            // Friction only limits movement
            if (accelerationOfSystem < 0) {
                accelerationOfSystem = 0;
            }
        } else if (accelerationWithoutFriction < 0) {
            accelerationOfSystem = -(leftBox.getGravForce() - rightBox.getGravForce()
                    - leftBox.getFriction() - middleBox.getFriction() - rightBox.getFriction())
                    / (leftBox.getMass() + middleBox.getMass() + rightBox.getMass());

            // Friction only limits movement
            if (accelerationOfSystem > 0) {
                accelerationOfSystem = 0;
            }
        }

        setBoxAccelerations();
    }

    /** Set the acceleration of all boxes in system based off system acceleration */
    private void setBoxAccelerations() {
        Vector leftAcceleration = Vector.createFromPolar(accelerationOfSystem, leftSlopeAngle);
        Vector middleAcceleration = Vector.createFromPolar(accelerationOfSystem, middleSlopeAngle);
        Vector rightAcceleration = Vector.createFromPolar(accelerationOfSystem, rightSlopeAngle + 180);

        leftBox.setAcceleration(leftAcceleration);
        middleBox.setAcceleration(middleAcceleration);
        rightBox.setAcceleration(rightAcceleration);
    }

    /** Creates a new left pulley model instance
     * @return a new left pulley model instance */
    private PulleyModel createLeftPulley() {
        final int radius = 25;

        return new PulleyModel(radius,
                leftSlope.getRightCoord().getX() - radius * MathTools.sin(getLeftSlopeAngle()) - radius,
                leftSlope.getRightCoord().getY() - (radius * 2));
    }

    /** Creates a new right pulley model instance
     * @return a new right pulley model instance */
    private PulleyModel createRightPulley() {
        final int radius = 25;

        return new PulleyModel(radius,
                rightSlope.getLeftCoord().getX() - radius + radius * MathTools.sin(getRightSlopeAngle()),
                rightSlope.getLeftCoord().getY() - (radius * 2));
    }

    /** Sets the angle of the left slope in degrees and update all objects' coordinates that rely on the left slope's angle
     * @param leftSlopeAngle the new value for the left slope angle */
    public void setLeftSlopeAngle(double leftSlopeAngle) {
        this.leftSlopeAngle = leftSlopeAngle;

        leftSlope.calculateCoordinates();
        leftPulley = createLeftPulley();
        leftBox.calculateStartingPositionCoordinates();
        leftWire.updatePosition();

        repaintView();
    }

    /** Sets the angle of the right slope in degrees and update all objects' coordinates that rely on the right slope's angle
     * @param rightSlopeAngle the new value for the right slope angle */
    public void setRightSlopeAngle(double rightSlopeAngle) {
        this.rightSlopeAngle = rightSlopeAngle;

        rightSlope.calculateCoordinates();
        rightPulley = createRightPulley();
        rightBox.calculateStartingPositionCoordinates();
        rightWire.updatePosition();

        repaintView();
    }

    /** Returns the angle of the left slope in degrees
     * @return the angle of the left slope in degrees */
    public double getLeftSlopeAngle() {
        return leftSlopeAngle;
    }

    /** Returns the angle of the middle slope in degrees
     * @return the angle of the middle slope in degrees */
    public double getMiddleSlopeAngle() {
        return middleSlopeAngle;
    }

    /** Returns the angle of the right slope in degrees
     * @return the angle of the right slope in degrees */
    public double getRightSlopeAngle() {
        return rightSlopeAngle;
    }

    /** Returns the accelerations of the box system in meters per second squared
     * @return the accelerations of the box system in meters per second squared */
    public double getAccelerationOfSystem() {
        return accelerationOfSystem;
    }

    /** Returns the left box model of the box system
     * @return the left box object in the box system */
    public LeftBoxModel getLeftBox() {
        return leftBox;
    }

    /** Returns the middle box model of the box system
     * @return the middle box object in the box system */
    public MiddleBoxModel getMiddleBox() {
        return middleBox;
    }

    /** Returns the right box model of the box system
     * @return the right box object in the box system */
    public RightBoxModel getRightBox() {
        return rightBox;
    }

    /** Returns the left pulley model of the box system
     * @return the left pulley object in the box system */
    public PulleyModel getLeftPulley() {
        return leftPulley;
    }

    /** Returns the right pulley model of the box system
     * @return the right pulley object in the box system */
    public PulleyModel getRightPulley() {
        return rightPulley;
    }

    /** Returns the left slope model of the box system
     * @return the left slope object in the box system */
    public LeftSlopeModel getLeftSlope() {
        return leftSlope;
    }

    /** Returns the middle slope model of the box system
     * @return the middle slope object in the box system */
    public MiddleSlopeModel getMiddleSlope() {
        return middleSlope;
    }

    /** Returns the right slope model of the box system
     * @return the right slope object in the box system */
    public RightSlopeModel getRightSlope() {
        return rightSlope;
    }

    /** Returns the left wire model of the box system
     * @return the left wire object in the box system */
    public LeftWireModel getLeftWire() {
        return leftWire;
    }

    /** Returns the right wire model of the box system
     * @return the right wire object in the box system */
    public RightWireModel getRightWire() {
        return rightWire;
    }
}
