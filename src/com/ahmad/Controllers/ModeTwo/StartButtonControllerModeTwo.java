package com.ahmad.Controllers.ModeTwo;

import com.ahmad.Controllers.StartButtonController;
import com.ahmad.Models.ModeTwo.SystemModelModeTwo;
import com.ahmad.Views.ModeTwo.MainViewModeTwo;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class StartButtonControllerModeTwo extends StartButtonController {
    private MainViewModeTwo mainViewModeTwo;
    private SystemModelModeTwo systemModelModeTwo;

    public StartButtonControllerModeTwo(MainViewModeTwo mainViewModeTwo, SystemModelModeTwo systemModelModeTwo) {
        super(mainViewModeTwo, systemModelModeTwo);

        this.mainViewModeTwo = mainViewModeTwo;
        this.systemModelModeTwo = systemModelModeTwo;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (!checkLeftMass() || !checkMiddleMass() || !checkRightMass() || !checkLeftMu() || !checkMiddleMu() || !checkRightMu() || !checkLeftAngle() || !checkRightAngle()) {
            return;
        }

        super.actionPerformed(actionEvent);

        mainViewModeTwo.accelerationTextField.setEditable(false);
        mainViewModeTwo.velocityTextField.setEditable(false);
        mainViewModeTwo.leftBoxMassTextField.setEditable(false);
        mainViewModeTwo.leftBoxMuTextField.setEditable(false);
        mainViewModeTwo.leftBoxFrictionTextField.setEditable(false);
        mainViewModeTwo.leftWireTensionTextField.setEditable(false);
        mainViewModeTwo.leftSlopeAngleTextField.setEditable(false);
        mainViewModeTwo.middleBoxMassTextField.setEditable(false);
        mainViewModeTwo.middleBoxMuTextField.setEditable(false);
        mainViewModeTwo.middleBoxFrictionTextField.setEditable(false);
        mainViewModeTwo.rightBoxMassTextField.setEditable(false);
        mainViewModeTwo.rightBoxMuTextField.setEditable(false);
        mainViewModeTwo.rightBoxFrictionTextField.setEditable(false);
        mainViewModeTwo.rightWireTensionTextField.setEditable(false);
        mainViewModeTwo.rightSlopeAngleTextField.setEditable(false);
        mainViewModeTwo.startButton.setEnabled(false);

        mainViewModeTwo.accelerationTextField.setFocusable(false);
        mainViewModeTwo.velocityTextField.setFocusable(false);
        mainViewModeTwo.leftBoxMassTextField.setFocusable(false);
        mainViewModeTwo.leftBoxMuTextField.setFocusable(false);
        mainViewModeTwo.leftBoxFrictionTextField.setFocusable(false);
        mainViewModeTwo.leftWireTensionTextField.setFocusable(false);
        mainViewModeTwo.leftSlopeAngleTextField.setFocusable(false);
        mainViewModeTwo.middleBoxMassTextField.setFocusable(false);
        mainViewModeTwo.middleBoxMuTextField.setFocusable(false);
        mainViewModeTwo.middleBoxFrictionTextField.setFocusable(false);
        mainViewModeTwo.rightBoxMassTextField.setFocusable(false);
        mainViewModeTwo.rightBoxMuTextField.setFocusable(false);
        mainViewModeTwo.rightBoxFrictionTextField.setFocusable(false);
        mainViewModeTwo.rightWireTensionTextField.setFocusable(false);
        mainViewModeTwo.rightSlopeAngleTextField.setFocusable(false);
        mainViewModeTwo.startButton.setFocusable(false);
    }

    private boolean checkLeftMass() {
        try {
            double leftMass = Double.parseDouble(mainViewModeTwo.leftBoxMassTextField.getText());

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

    private boolean checkMiddleMass() {
        try {
            double middleMass = Double.parseDouble(mainViewModeTwo.middleBoxMassTextField.getText());

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

    private boolean checkRightMass() {
        try {
            double rightMass = Double.parseDouble(mainViewModeTwo.rightBoxMassTextField.getText());

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

    private boolean checkLeftMu() {
        try {
            double mu = Double.parseDouble(mainViewModeTwo.leftBoxMuTextField.getText());

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

    private boolean checkMiddleMu() {
        try {
            double mu = Double.parseDouble(mainViewModeTwo.middleBoxMuTextField.getText());

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

    private boolean checkRightMu() {
        try {
            double mu = Double.parseDouble(mainViewModeTwo.rightBoxMuTextField.getText());

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

    private boolean checkLeftAngle() {
        if (mainViewModeTwo.leftSlopeAngleTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "The left angle must be a number.");

            return false;
        }

        return true;
    }

    private boolean checkRightAngle() {
        if (mainViewModeTwo.rightSlopeAngleTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "The right angle must be a number.");

            return false;
        }

        return true;
    }
}