package com.ahmad.Controllers;

import com.ahmad.Models.ModeOne.SystemModel;
import com.ahmad.Views.SystemView;
import com.ahmad.Views.MainView;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AngleTextFieldController implements KeyListener {
    private MainView view;
    private JTextField slopeAngleTextField;
    private SystemModel boxSystem;
    private SystemView simulationView;

    public AngleTextFieldController(MainView view, JTextField slopeAngleTextField, SystemModel boxSystem, SystemView simulationView) {
        this.view = view;
        this.slopeAngleTextField = slopeAngleTextField;
        this.boxSystem = boxSystem;
        this.simulationView = simulationView;
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

            boxSystem.setSlopeAngle(slopeAngle);
        } catch (Exception e) {
            System.out.println("Error for angle");
        }
    }
}
