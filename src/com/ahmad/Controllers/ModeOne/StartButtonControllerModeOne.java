package com.ahmad.Controllers.ModeOne;

/** StartButtonControllerModeOne
 * Handles input for the start button in mode one
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Controllers.StartButtonController;
import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Views.ModeOne.MainViewModeOne;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class StartButtonControllerModeOne extends StartButtonController {
    private MainViewModeOne mainViewModeOne;   // Declare the main view

    /** Default Constructor
     * @param mainViewModeOne     a reference to the view
     * @param systemModelModeOne  a reference to the system model */
    public StartButtonControllerModeOne(MainViewModeOne mainViewModeOne, SystemModelModeOne systemModelModeOne) {
        super(mainViewModeOne, systemModelModeOne);

        this.mainViewModeOne = mainViewModeOne;
    }

    /** Listens for an action to be performed on the start button
     * @param actionEvent the event created upon interaction */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (mainViewModeOne.getInputTypeComboBox().getSelectedIndex() == 0) {
            if (!checkLeftMass() || !checkLeftMu() || !checkRightMass() || !checkAngle()) {
                return;
            }
        } else {
            if (!checkTension() || !checkLeftMu() || !checkRightMass() || !checkAngle()) {
                return;
            }
        }

        super.actionPerformed(actionEvent);

        mainViewModeOne.getAccelerationTextField().setEditable(false);
        mainViewModeOne.getVelocityTextField().setEditable(false);
        mainViewModeOne.getTensionTextField().setEditable(false);
        mainViewModeOne.getLeftBoxMassTextField().setEditable(false);
        mainViewModeOne.getLeftBoxMuTextField().setEditable(false);
        mainViewModeOne.getLeftBoxFrictionTextField().setEditable(false);
        mainViewModeOne.getLeftSlopeAngleTextField().setEditable(false);
        mainViewModeOne.getLeftSlopeAngleTextField().setFocusable(false);
        mainViewModeOne.getRightBoxMassTextField().setEditable(false);
        mainViewModeOne.getInputTypeComboBox().setEnabled(false);
        mainViewModeOne.getStartButton().setEnabled(false);

        mainViewModeOne.getAccelerationTextField().setFocusable(false);
        mainViewModeOne.getVelocityTextField().setFocusable(false);
        mainViewModeOne.getTensionTextField().setFocusable(false);
        mainViewModeOne.getLeftBoxMassTextField().setFocusable(false);
        mainViewModeOne.getLeftBoxMuTextField().setFocusable(false);
        mainViewModeOne.getLeftBoxFrictionTextField().setFocusable(false);
        mainViewModeOne.getLeftSlopeAngleTextField().setFocusable(false);
        mainViewModeOne.getLeftSlopeAngleTextField().setFocusable(false);
        mainViewModeOne.getRightBoxMassTextField().setFocusable(false);
        mainViewModeOne.getInputTypeComboBox().setFocusable(false);
        mainViewModeOne.getStartButton().setFocusable(false);
    }

    /** Validates left mass input
     * @return whether or not the input is valid */
    private boolean checkLeftMass() {
        try {
            double leftMass = Double.parseDouble(mainViewModeOne.getLeftBoxMassTextField().getText());

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

    /** Validates left mu input
     * @return whether or not the input is valid */
    private boolean checkLeftMu() {
        try {
            double mu = Double.parseDouble(mainViewModeOne.getLeftBoxMuTextField().getText());

            if (mu < 0) {
                JOptionPane.showMessageDialog(null, "The mu must be greater than 0.");

                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The mu must be a number.");

            return false;
        }

        return true;
    }

    /** Validates right mass input
     * @return whether or not the input is valid */
    private boolean checkRightMass() {
        try {
            double rightMass = Double.parseDouble(mainViewModeOne.getRightBoxMassTextField().getText());

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

    /** Validates angle input
     * @return whether or not the input is valid */
    private boolean checkAngle() {
        if (mainViewModeOne.getLeftSlopeAngleTextField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "The angle must be a number.");

            return false;
        }

        return true;
    }

    /** Validates tension input
     * @return whether or not the input is valid */
    private boolean checkTension() {
        try {
            double tension = Double.parseDouble(mainViewModeOne.getTensionTextField().getText());

            if (tension < 0) {
                JOptionPane.showMessageDialog(null, "The tension must be greater than 0.");

                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The tension must be a number.");

            return false;
        }

        return true;
    }
}