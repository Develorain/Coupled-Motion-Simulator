package com.ahmad.Controllers.ModeTwo;

import com.ahmad.Models.ModeTwo.SystemModelModeTwo;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RightAngleTextFieldController implements KeyListener {
    private JTextField slopeAngleTextField;
    private SystemModelModeTwo systemModelModeTwo;

    public RightAngleTextFieldController(JTextField slopeAngleTextField, SystemModelModeTwo systemModelModeTwo) {
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
        try {
            double slopeAngle = Double.parseDouble(slopeAngleTextField.getText());

            systemModelModeTwo.setRightSlopeAngle(180 - slopeAngle);
        } catch (Exception e) {
            System.out.println("Error for angle");
        }
    }
}
