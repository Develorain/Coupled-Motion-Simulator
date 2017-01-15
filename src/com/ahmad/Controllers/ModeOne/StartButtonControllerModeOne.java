package com.ahmad.Controllers.ModeOne;

import com.ahmad.Controllers.StartButtonController;
import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Views.ModeOne.MainViewModeOne;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class StartButtonControllerModeOne extends StartButtonController {
    private MainViewModeOne mainViewModeOne;
    private SystemModelModeOne systemModelModeOne;

    public StartButtonControllerModeOne(MainViewModeOne mainViewModeOne, SystemModelModeOne systemModelModeOne) {
        super(mainViewModeOne, systemModelModeOne);

        this.mainViewModeOne = mainViewModeOne;
        this.systemModelModeOne = systemModelModeOne;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (mainViewModeOne.inputTypeComboBox.getSelectedIndex() == 0) {
            if (!checkLeftMass() || !checkLeftMu() || !checkRightMass() || !checkAngle()) {
                return;
            }
        } else {
            if (!checkTension() || !checkLeftMu() || !checkRightMass() || !checkAngle()) {
                return;
            }
        }

        super.actionPerformed(actionEvent);

        mainViewModeOne.accelerationTextField.setEditable(false);
        mainViewModeOne.velocityTextField.setEditable(false);
        mainViewModeOne.tensionTextField.setEditable(false);
        mainViewModeOne.leftBoxMassTextField.setEditable(false);
        mainViewModeOne.leftBoxMuTextField.setEditable(false);
        mainViewModeOne.leftBoxFrictionTextField.setEditable(false);
        mainViewModeOne.leftSlopeAngleTextField.setEditable(false);
        mainViewModeOne.leftSlopeAngleTextField.setFocusable(false);
        mainViewModeOne.rightBoxMassTextField.setEditable(false);
        mainViewModeOne.inputTypeComboBox.setEnabled(false);
        mainViewModeOne.startButton.setEnabled(false);

        mainViewModeOne.accelerationTextField.setFocusable(false);
        mainViewModeOne.velocityTextField.setFocusable(false);
        mainViewModeOne.tensionTextField.setFocusable(false);
        mainViewModeOne.leftBoxMassTextField.setFocusable(false);
        mainViewModeOne.leftBoxMuTextField.setFocusable(false);
        mainViewModeOne.leftBoxFrictionTextField.setFocusable(false);
        mainViewModeOne.leftSlopeAngleTextField.setFocusable(false);
        mainViewModeOne.leftSlopeAngleTextField.setFocusable(false);
        mainViewModeOne.rightBoxMassTextField.setFocusable(false);
        mainViewModeOne.inputTypeComboBox.setFocusable(false);
        mainViewModeOne.startButton.setFocusable(false);
    }

    private boolean checkLeftMass() {
        try {
            double leftMass = Double.parseDouble(mainViewModeOne.leftBoxMassTextField.getText());

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

    private boolean checkLeftMu() {
        try {
            double mu = Double.parseDouble(mainViewModeOne.leftBoxMuTextField.getText());

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

    private boolean checkRightMass() {
        try {
            double rightMass = Double.parseDouble(mainViewModeOne.rightBoxMassTextField.getText());

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

    private boolean checkAngle() {
        if (mainViewModeOne.leftSlopeAngleTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "The angle must be a number.");

            return false;
        }

        return true;
    }

    private boolean checkTension() {
        try {
            double tension = Double.parseDouble(mainViewModeOne.tensionTextField.getText());

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