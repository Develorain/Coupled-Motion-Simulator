package com.ahmad.Controllers.ModeTwo;

/** RightAngleTextFieldController
 * Handles input for the right angle text field in mode two
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.ModeTwo.SystemModelModeTwo;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RightAngleTextFieldController implements KeyListener {
    private JTextField slopeAngleTextField;           // Declare the text field
    private SystemModelModeTwo systemModelModeTwo;    // Declare the system model

    /** Default Constructor
     * @param slopeAngleTextField a reference to the slope angle text field
     * @param systemModelModeTwo  a reference to the system model */
    public RightAngleTextFieldController(JTextField slopeAngleTextField, SystemModelModeTwo systemModelModeTwo) {
        this.slopeAngleTextField = slopeAngleTextField;
        this.systemModelModeTwo = systemModelModeTwo;
    }

    /** Listens for key released event
     * @param keyEvent the event created upon key release */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (slopeAngleTextField.getText().isEmpty()) {
            return;
        }

        try {
            double slopeAngle = Double.parseDouble(slopeAngleTextField.getText());

            if (slopeAngle < 0 || slopeAngle > 90) {
                JOptionPane.showMessageDialog(null, "The right angle must be between 0 and 90.");

                slopeAngleTextField.setText("45");
                systemModelModeTwo.setRightSlopeAngle(135);
            } else {
                systemModelModeTwo.setRightSlopeAngle(180 - slopeAngle);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The right angle must be a number.");

            slopeAngleTextField.setText("45");
            systemModelModeTwo.setRightSlopeAngle(135);
        }
    }

    /** Listens for key typed event
     * @param keyEvent the event created upon key typed */
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /** Listens for key pressed event
     * @param keyEvent the event created upon key pressed */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
    }
}
