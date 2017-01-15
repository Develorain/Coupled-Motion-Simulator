package com.ahmad.Controllers.ModeTwo;

import com.ahmad.Models.ModeTwo.SystemModelModeTwo;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LeftAngleTextFieldController implements KeyListener {
    private JTextField slopeAngleTextField;
    private SystemModelModeTwo systemModelModeTwo;

    public LeftAngleTextFieldController(JTextField slopeAngleTextField, SystemModelModeTwo systemModelModeTwo) {
        this.slopeAngleTextField = slopeAngleTextField;
        this.systemModelModeTwo = systemModelModeTwo;
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
                JOptionPane.showMessageDialog(null, "The left angle must be between 0 and 90.");

                slopeAngleTextField.setText("45");
            } else {
                systemModelModeTwo.setLeftSlopeAngle(slopeAngle);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The right angle must be a number.");

            slopeAngleTextField.setText("45");
        }
    }
}
