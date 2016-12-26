package com.ahmad.Controllers;

import com.ahmad.Models.ModeOne.SystemModelModeOne;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AngleTextFieldController implements KeyListener {
    private JTextField slopeAngleTextField;
    private SystemModelModeOne systemModelModeOne;

    public AngleTextFieldController(JTextField slopeAngleTextField, SystemModelModeOne systemModelModeOne) {
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
