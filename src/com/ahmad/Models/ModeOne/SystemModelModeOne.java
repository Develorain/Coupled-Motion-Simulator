package com.ahmad.Models.ModeOne;

/** SystemModelModeOne
  * Performs the calculations for the box system in mode one
  * @since January 22, 2017
  * @author Ahmad Gharib
  */

import com.ahmad.Models.Model;
import com.ahmad.Models.PulleyModel;
import com.ahmad.Models.SystemModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;
import com.ahmad.Views.ModeOne.MainViewModeOne;
import com.ahmad.Views.View;

public class SystemModelModeOne extends Model implements SystemModel {
    private boolean isActive = false;         // Whether the simulation is running or not

    private SlopeModelModeOne slope;          // Declare the slope model
    private PulleyModel pulley;               // Declare the pulley model
    private WireModelModeOne wire;            // Declare the wire model connecting the sloped box, dangling box, and pulley

    private SlopedBoxModel slopedBox;         // Declare the sloped box model
    private DanglingBoxModel danglingBox;     // Declare the dangling box model

    private double accelerationOfSystem;      // Declare the acceleration of all the boxes in the box system
    private double slopeAngle;                // Declare the angle of the sloped box and slope
    private long simulationStartTime;         // Declare the time when the simulation starts

    /** Default constructor */
    public SystemModelModeOne() {
        slopeAngle = 45;
        simulationStartTime = 0;

        slope = new SlopeModelModeOne(this);
        pulley = createNewPulley();

        slopedBox = new SlopedBoxModel(this, 1, 0);
        danglingBox = new DanglingBoxModel(this, 1);

        wire = new WireModelModeOne(this);
    }

    /** Takes input from the view and calls the appropriate methods to initialize constant values
     * @param mainView a reference to the main view */
    public void takeInputAndInitializeConstantValues(View mainView) {
        MainViewModeOne mainViewModeOne = (MainViewModeOne) mainView;

        // Decides which input type is chosen
        switch (mainViewModeOne.getInputTypeComboBox().getSelectedIndex()) {
            case 0:
                // Set all the values based on the user's input
                slopedBox.setMass(Double.parseDouble(mainViewModeOne.getLeftBoxMassTextField().getText()));
                danglingBox.setMass(Double.parseDouble(mainViewModeOne.getRightBoxMassTextField().getText()));
                slopedBox.setMu(Double.parseDouble(mainViewModeOne.getLeftBoxMuTextField().getText()));
                slopeAngle = Double.parseDouble(mainViewModeOne.getLeftSlopeAngleTextField().getText());

                // Calculate acceleration, tension, and all secondary unknown values
                slopedBox.updateFriction(slopeAngle);
                slopedBox.updateGravForce(slopeAngle);
                danglingBox.updateGravForce();
                updateAccelerationInputTypeOne();
                wire.updateTension(danglingBox.getMass(), accelerationOfSystem);
                break;

            case 1:
                // Set all the values based on the user's input
                wire.setTension(Double.parseDouble(mainViewModeOne.getTensionTextField().getText()));
                slopedBox.setMu(Double.parseDouble(mainViewModeOne.getLeftBoxMuTextField().getText()));
                slopeAngle = Double.parseDouble(mainViewModeOne.getLeftSlopeAngleTextField().getText());
                danglingBox.setMass(Double.parseDouble(mainViewModeOne.getRightBoxMassTextField().getText()));

                // Calculate left acceleration, left mass, and all secondary unknown values
                slopedBox.updateFriction(slopeAngle);
                danglingBox.updateGravForce();
                updateAccelerationInputTypeTwo();
                slopedBox.updateMass(slopeAngle, danglingBox.getMass(), accelerationOfSystem, slopedBox.getMu());
                break;
        }
    }

    /** Calculate the box system acceleration using the known data from input type one */
    private void updateAccelerationInputTypeOne() {
        // Calculate the acceleration of the system without friction to determine direction of movement
        double accelerationOfSystemWithoutFriction = (danglingBox.getGravForce() - slopedBox.getGravForce())
                / (slopedBox.getMass() + danglingBox.getMass());

        if (accelerationOfSystemWithoutFriction == 0) {
            // If there is no acceleration with friction, there is no acceleration without friction
            accelerationOfSystem = 0;
        } else if (accelerationOfSystemWithoutFriction > 0) {
            // Calculate acceleration of system if the box is moving towards the right
            accelerationOfSystem = (danglingBox.getGravForce() - slopedBox.getGravForce() - slopedBox.getFriction())
                    / (slopedBox.getMass() + danglingBox.getMass());

            // Friction only limits motion
            if (accelerationOfSystem < 0) {
                accelerationOfSystem = 0;   // Set the acceleration of the system to zero if the friction is stronger than the acceleration
            }

        } else if (accelerationOfSystemWithoutFriction < 0) {
            // Calculate acceleration of system if the box is moving towards the left
            accelerationOfSystem = -(slopedBox.getGravForce() - slopedBox.getFriction() - danglingBox.getGravForce())
                    / (slopedBox.getMass() + danglingBox.getMass());

            // Friction only limits motion
            if (accelerationOfSystem > 0) {
                accelerationOfSystem = 0;   // Set the acceleration of the system to zero if the friction is stronger than the acceleration
            }
        }

        setBoxAccelerations();   // Update every box's acceleration
    }

    /** Calculate the box system acceleration using the known data from input type two */
    private void updateAccelerationInputTypeTwo() {
        accelerationOfSystem = (danglingBox.getGravForce() - wire.getTension()) / danglingBox.getMass();

        setBoxAccelerations();
    }

    /** Set the acceleration of all boxes in system based off system acceleration */
    private void setBoxAccelerations() {
        // Create accelerations using slope angles of each box, and maginitude of the system acceleration
        Vector accelerationLeft = Vector.createFromPolar(accelerationOfSystem, slopeAngle);
        Vector accelerationRight = Vector.createFromPolar(accelerationOfSystem, -90);

        // Set each box's acceleration
        slopedBox.setAcceleration(accelerationLeft);
        danglingBox.setAcceleration(accelerationRight);
    }

    /** Iterates the physics in the system */
    @Override
    public void iterate() {
        checkIfBoxesStillHaveRoomToMove();

        if (isActive) {
            // Determine the time when the simulation starts
            if (simulationStartTime == 0) {
                simulationStartTime = System.nanoTime();
            }

            double elapsedSeconds = (System.nanoTime() - simulationStartTime) / 1000000000.0;  // Time since simulation started in seconds

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
    }

    /** Checks if the any of the boxes within the box system has moved off screen or has hit a pulley */
    private void checkIfBoxesStillHaveRoomToMove() {
        if ((slopedBox.getBottomRightCorner().getX() > pulley.getTopLeftCorner().getX() && slopedBox.getTopRightCorner().getY() < (pulley.getTopLeftCorner().getY() + pulley.getDiameter()))
                || slopedBox.getBottomLeftCorner().getX() < slope.getLeftCoord().getX()
                || danglingBox.getTopLeftCorner().getY() < pulley.getTopLeftCorner().getY() + pulley.getDiameter()
                || danglingBox.getBottomLeftCorner().getY() > Constants.SIMULATION_HEIGHT_PIXELS) {
            isActive = false;      // Stop simulation when boxes go out of bounds
        }
    }

    /** Creates a new pulley model instance
     * @return a new pulley model instance */
    private PulleyModel createNewPulley() {
        final int radius = 50;

        return new PulleyModel(radius,
                slope.getRightCoord().getX() - radius + radius * MathTools.cos(slopeAngle),
                slope.getRightCoord().getY() - radius - radius * MathTools.sin(slopeAngle));
    }

    /** Sets the simulation to active */
    public void setActive() {
        isActive = true;
    }

    /** Sets the angle of the slope in degrees and update all objects' coordinates that rely on the slope's angle 
      * @param slopeAngle the new value for the slope angle */
    public void setSlopeAngle(double slopeAngle) {
        this.slopeAngle = slopeAngle;

        // Calculate the new position of the slope and
        slope.calculateCoordinates();
        pulley = createNewPulley();

        // Calculate new position of all the boxes in the system
        slopedBox.calculateStartingPositionCoordinates();
        danglingBox.calculateStartingPositionCoordinates();

        wire.updatePosition();

        repaintView();
    }

    /** Returns the angle of the slope in degrees
      * @return the angle of the slope in degrees */
    public double getSlopeAngle() {
        return slopeAngle;
    }

    /** Returns the accelerations of the box system in meters per second squared
      * @return the accelerations of the box system in meters per second squared */
    public double getAccelerationOfSystem() {
        return accelerationOfSystem;
    }

    /** Returns the sloped box model of the box system
      * @return the sloped box object in the box system */
    public SlopedBoxModel getSlopedBox() {
        return slopedBox;
    }

    /** Returns the dangling box model of the box system
      * @return the dangling box object in the box system */
    public DanglingBoxModel getDanglingBox() {
        return danglingBox;
    }

    /** Returns the pulley model of the box system
      * @return the pulley object in the box system */
    public PulleyModel getPulley() {
        return pulley;
    }

    /** Returns the slope model of the box system
      * @return the slope object in the box system */
    public SlopeModelModeOne getSlope() {
        return slope;
    }

    /** Returns the wire model of the box system
      * @return the wire object in the box system */
    public WireModelModeOne getWire() {
        return wire;
    }

}
