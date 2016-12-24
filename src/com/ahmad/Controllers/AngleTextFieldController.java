package com.ahmad.Controllers;

import com.ahmad.Models.ModeOne.SystemModel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AngleTextFieldController implements KeyListener {
    private JTextField slopeAngleTextField;
    private SystemModel systemModel;

    public AngleTextFieldController(JTextField slopeAngleTextField, SystemModel systemModel) {
        this.slopeAngleTextField = slopeAngleTextField;
        this.systemModel = systemModel;
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

            systemModel.setSlopeAngle(slopeAngle);
        } catch (Exception e) {
            System.out.println("Error for angle");
        }
    }
}
