package com.ahmad.Controllers;

import com.ahmad.Models.ModeOne.SystemModelOne;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AngleTextFieldController implements KeyListener {
    private JTextField slopeAngleTextField;
    private SystemModelOne systemModelOne;

    public AngleTextFieldController(JTextField slopeAngleTextField, SystemModelOne systemModelOne) {
        this.slopeAngleTextField = slopeAngleTextField;
        this.systemModelOne = systemModelOne;
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

            systemModelOne.setSlopeAngle(slopeAngle);
        } catch (Exception e) {
            System.out.println("Error for angle");
        }
    }
}
