package com.ahmad.Controllers.ModeTwo;

/** StartButtonControllerModeTwo
 * Handles input for the start button in mode two
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Controllers.StartButtonController;
import com.ahmad.Models.ModeTwo.SystemModelModeTwo;
import com.ahmad.Views.ModeTwo.MainViewModeTwo;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class StartButtonControllerModeTwo extends StartButtonController {
    private MainViewModeTwo mainViewModeTwo;   // Declare the main view

    /** Default Constructor
     * @param mainViewModeTwo     a reference to the view
     * @param systemModelModeTwo  a reference to the system model */
    public StartButtonControllerModeTwo(MainViewModeTwo mainViewModeTwo, SystemModelModeTwo systemModelModeTwo) {
        super(mainViewModeTwo, systemModelModeTwo);

        this.mainViewModeTwo = mainViewModeTwo;
    }

    /** Listens for an action to be performed on the start button
     * @param actionEvent the event created upon interaction */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (!checkLeftMass() || !checkMiddleMass() || !checkRightMass() || !checkLeftMu() || !checkMiddleMu() || !checkRightMu() || !checkLeftAngle() || !checkRightAngle()) {
            return;
        }

        super.actionPerformed(actionEvent);

        mainViewModeTwo.getAccelerationTextField().setEditable(false);
        mainViewModeTwo.getVelocityTextField().setEditable(false);
        mainViewModeTwo.getLeftBoxMassTextField().setEditable(false);
        mainViewModeTwo.getLeftBoxMuTextField().setEditable(false);
        mainViewModeTwo.getLeftBoxFrictionTextField().setEditable(false);
        mainViewModeTwo.getLeftWireTensionTextField().setEditable(false);
        mainViewModeTwo.getLeftSlopeAngleTextField().setEditable(false);
        mainViewModeTwo.getMiddleBoxMassTextField().setEditable(false);
        mainViewModeTwo.getMiddleBoxMuTextField().setEditable(false);
        mainViewModeTwo.getMiddleBoxFrictionTextField().setEditable(false);
        mainViewModeTwo.getRightBoxMassTextField().setEditable(false);
        mainViewModeTwo.getRightBoxMuTextField().setEditable(false);
        mainViewModeTwo.getRightBoxFrictionTextField().setEditable(false);
        mainViewModeTwo.getRightWireTensionTextField().setEditable(false);
        mainViewModeTwo.getRightSlopeAngleTextField().setEditable(false);
        mainViewModeTwo.getStartButton().setEnabled(false);

        mainViewModeTwo.getAccelerationTextField().setFocusable(false);
        mainViewModeTwo.getVelocityTextField().setFocusable(false);
        mainViewModeTwo.getLeftBoxMassTextField().setFocusable(false);
        mainViewModeTwo.getLeftBoxMuTextField().setFocusable(false);
        mainViewModeTwo.getLeftBoxFrictionTextField().setFocusable(false);
        mainViewModeTwo.getLeftWireTensionTextField().setFocusable(false);
        mainViewModeTwo.getLeftSlopeAngleTextField().setFocusable(false);
        mainViewModeTwo.getMiddleBoxMassTextField().setFocusable(false);
        mainViewModeTwo.getMiddleBoxMuTextField().setFocusable(false);
        mainViewModeTwo.getMiddleBoxFrictionTextField().setFocusable(false);
        mainViewModeTwo.getRightBoxMassTextField().setFocusable(false);
        mainViewModeTwo.getRightBoxMuTextField().setFocusable(false);
        mainViewModeTwo.getRightBoxFrictionTextField().setFocusable(false);
        mainViewModeTwo.getRightWireTensionTextField().setFocusable(false);
        mainViewModeTwo.getRightSlopeAngleTextField().setFocusable(false);
        mainViewModeTwo.getStartButton().setFocusable(false);
    }

    /** Validates left mass input
     * @return whether or not the input is valid */
    private boolean checkLeftMass() {
        try {
            double leftMass = Double.parseDouble(mainViewModeTwo.getLeftBoxMassTextField().getText());

            if (leftMass < 0) {
                JOptionPane.showMessageDialog(null, "The left mass must be greater than 0.");

                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The left mass must be a number.");

            return false;
        }

        return true;
    }

    /** Validates middle mass input
     * @return whether or not the input is valid */
    private boolean checkMiddleMass() {
        try {
            double middleMass = Double.parseDouble(mainViewModeTwo.getMiddleBoxMassTextField().getText());

            if (middleMass < 0) {
                JOptionPane.showMessageDialog(null, "The middle mass must be greater than 0.");

                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The middle mass must be a number.");

            return false;
        }

        return true;
    }

    /** Validates right mass input
     * @return whether or not the input is valid */
    private boolean checkRightMass() {
        try {
            double rightMass = Double.parseDouble(mainViewModeTwo.getRightBoxMassTextField().getText());

            if (rightMass < 0) {
                JOptionPane.showMessageDialog(null, "The right mass must be greater than 0.");

                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The right mass must be a number.");

            return false;
        }

        return true;
    }

    /** Validates left mu input
     * @return whether or not the input is valid */
    private boolean checkLeftMu() {
        try {
            double mu = Double.parseDouble(mainViewModeTwo.getLeftBoxMuTextField().getText());

            if (mu < 0) {
                JOptionPane.showMessageDialog(null, "The left mu must be greater than 0.");

                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The left mu must be a number.");

            return false;
        }

        return true;
    }

    /** Validates middle mu input
     * @return whether or not the input is valid */
    private boolean checkMiddleMu() {
        try {
            double mu = Double.parseDouble(mainViewModeTwo.getMiddleBoxMuTextField().getText());

            if (mu < 0) {
                JOptionPane.showMessageDialog(null, "The middle mu must be greater than 0.");

                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The middle mu must be a number.");

            return false;
        }

        return true;
    }

    /** Validates right mu input
     * @return whether or not the input is valid */
    private boolean checkRightMu() {
        try {
            double mu = Double.parseDouble(mainViewModeTwo.getRightBoxMuTextField().getText());

            if (mu < 0) {
                JOptionPane.showMessageDialog(null, "The right mu must be greater than 0.");

                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The right mu must be a number.");

            return false;
        }

        return true;
    }

    /** Validates left angle input
     * @return whether or not the input is valid */
    private boolean checkLeftAngle() {
        if (mainViewModeTwo.getLeftSlopeAngleTextField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "The left angle must be a number.");

            return false;
        }

        return true;
    }

    /** Validates right angle input
     * @return whether or not the input is valid */
    private boolean checkRightAngle() {
        if (mainViewModeTwo.getRightSlopeAngleTextField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "The right angle must be a number.");

            return false;
        }

        return true;
    }
}