package com.ahmad.Controllers.ModeOne;

import com.ahmad.Models.ModeOne.SystemModelModeOne;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AngleTextFieldControllerModeOne implements KeyListener {
    private JTextField slopeAngleTextField;
    private SystemModelModeOne systemModelModeOne;

    public AngleTextFieldControllerModeOne(JTextField slopeAngleTextField, SystemModelModeOne systemModelModeOne) {
        this.slopeAngleTextField = slopeAngleTextField;
        this.systemModelModeOne = systemModelModeOne;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (slopeAngleTextField.getText().isEmpty()) {
            return;
        }

        try {
            double slopeAngle = Double.parseDouble(slopeAngleTextField.getText());

            if (slopeAngle < 0 || slopeAngle > 90) {
                JOptionPane.showMessageDialog(null, "The angle must be between 0 and 90.");

                slopeAngleTextField.setText("45");
            } else {
                systemModelModeOne.setSlopeAngle(slopeAngle);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The angle must be a number.");

            slopeAngleTextField.setText("45");
        }
    }
}
