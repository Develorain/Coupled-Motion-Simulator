package com.ahmad.Controllers.ModeOne;

/** AngleTextFieldControllerModeOne
 * Handles input for the angle text field in mode one
 * @since January 17, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.ModeOne.SystemModelModeOne;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AngleTextFieldControllerModeOne implements KeyListener {
    private JTextField slopeAngleTextField;         // Declare the text field
    private SystemModelModeOne systemModelModeOne;  // Declare the system model

    /** Default Constructor
     * @param slopeAngleTextField a reference to the slope angle text field
     * @param systemModelModeOne  a reference to the system model */
    public AngleTextFieldControllerModeOne(JTextField slopeAngleTextField, SystemModelModeOne systemModelModeOne) {
        this.slopeAngleTextField = slopeAngleTextField;
        this.systemModelModeOne = systemModelModeOne;
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
                JOptionPane.showMessageDialog(null, "The angle must be between 0 and 90.");

                slopeAngleTextField.setText("45");
                systemModelModeOne.setSlopeAngle(45);
            } else {
                systemModelModeOne.setSlopeAngle(slopeAngle);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The angle must be a number.");

            slopeAngleTextField.setText("45");
            systemModelModeOne.setSlopeAngle(45);
        }
    }

    /** Listens for key typed event
     * @param keyEvent the event created upon key typed */
    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    /** Listens for key pressed event
     * @param keyEvent the event created upon key pressed */
    @Override
    public void keyPressed(KeyEvent keyEvent) {}
}
