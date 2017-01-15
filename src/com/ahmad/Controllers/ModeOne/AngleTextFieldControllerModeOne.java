package com.ahmad.Controllers.ModeOne;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Tools.Prompt;

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
        try {
            double slopeAngle = Double.parseDouble(slopeAngleTextField.getText());

            if (slopeAngle < 0 || slopeAngle > 90) {
                systemModelModeOne.setSlopeAngle(0);

                Prompt.displayErrorMessage("The angle input is incorrect.");
            } else {
                systemModelModeOne.setSlopeAngle(slopeAngle);
            }
        } catch (Exception e) {
            System.out.println("Error for angle");
        }
    }
}
