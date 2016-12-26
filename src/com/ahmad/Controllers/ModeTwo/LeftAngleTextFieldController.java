package com.ahmad.Controllers.ModeTwo;

import com.ahmad.Models.ModeOne.SystemModelModeOne;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LeftAngleTextFieldController implements KeyListener {
    private JTextField slopeAngleTextField;
    private SystemModelModeOne systemModelModeOne;

    public LeftAngleTextFieldController(JTextField slopeAngleTextField, SystemModelModeOne systemModelModeOne) {
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

            systemModelModeOne.setSlopeAngle(slopeAngle);
        } catch (Exception e) {
            System.out.println("Error for angle");
        }
    }
}
