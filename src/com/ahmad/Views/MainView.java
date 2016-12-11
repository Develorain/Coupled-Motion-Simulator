package com.ahmad.Views;

import com.ahmad.Controllers.AngleTextFieldController;
import com.ahmad.Controllers.ResetButtonController;
import com.ahmad.Controllers.StartButtonController;
import com.ahmad.Models.ModeOne.SystemModel;

import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel {
    // todo: doesn't need to extend jpanel, should just have an instance of jpanel, needs to extend view

    private SystemModel boxSystem;
    public SystemView simulationView;

    private JTextField accelerationTextField;
    private JTextField velocityTextField;

    private JButton startButton;
    private JButton pauseButton;
    private JButton resetButton;

    private JTextField boxAMassTextField;
    private JTextField boxAMuTextField;

    private JTextField boxBMassTextField;

    private JTextField slopeAAngleTextField;

    public MainView(SystemModel boxSystem) {
        super(new GridBagLayout());

        this.boxSystem = boxSystem;

        layoutScreen();
        registerControllers();
    }

    // Updates all the information tables
    public void updateBoxSystemInfoTable() {
        String accel = Double.toString(simulationView.getBoxSystem().getAcceleration());
        accelerationTextField.setText(accel);

        String vel = Double.toString(simulationView.getBoxSystem().getBoxA().getVelocityMagnitude());
        velocityTextField.setText(vel);
    }

    private void registerControllers() {
        StartButtonController sbl = new StartButtonController(this, simulationView.getBoxSystem());
        startButton.addActionListener(sbl);


        AngleTextFieldController atfc = new AngleTextFieldController(this, slopeAAngleTextField, boxSystem, simulationView);
        slopeAAngleTextField.addKeyListener(atfc);


        ResetButtonController rbc = new ResetButtonController(accelerationTextField, velocityTextField,
                boxAMassTextField, boxAMuTextField, boxBMassTextField, slopeAAngleTextField);
        resetButton.addActionListener(rbc);
    }

    private void layoutScreen() {
        GridBagConstraints gc = new GridBagConstraints();

        // Creates and adds simulation view to the main JPanel
        simulationView = new SystemView(boxSystem);
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 7;
        gc.weighty = 100;
        add(simulationView, gc);

        createBoxSystemTable(gc);
        createBlockATable(gc);
        createSlopeATable(gc);
        createBlockBTable(gc);
        createButtonTable(gc);
    }

    private void createButtonTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weighty = 1;

        String[] modesList = new String[]{"Mode 1", "Mode 2", "Mode 3"};
        JComboBox modes = new JComboBox(modesList);
        gc.gridx = 6;
        gc.gridy = 1;
        add(modes, gc);

        startButton = new JButton("Start Simulation");
        gc.gridx = 6;
        gc.gridy = 2;
        add(startButton, gc);

        pauseButton = new JButton("Pause Simulation");
        gc.gridx = 6;
        gc.gridy = 3;
        add(pauseButton, gc);

        resetButton = new JButton("Reset Simulation");
        gc.gridx = 6;
        gc.gridy = 4;
        add(resetButton, gc);
    }

    private void createBlockBTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;

        JLabel boxBTitleLabel = new JLabel("Block B");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 4;
        gc.gridy = 1;
        gc.gridwidth = 2;
        add(boxBTitleLabel, gc);

        JLabel boxBMassLabel = new JLabel("Mass ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 4;
        gc.gridy = 2;
        gc.gridwidth = 1;
        add(boxBMassLabel, gc);

        boxBMassTextField = new JTextField("1", 10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 5;
        gc.gridy = 2;
        add(boxBMassTextField, gc);
    }

    private void createBlockATable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;

        JLabel boxATitleLabel = new JLabel("Block A");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 2;
        gc.gridy = 1;
        gc.gridwidth = 2;
        add(boxATitleLabel, gc);

        JLabel boxAMassLabel = new JLabel("Mass ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 2;
        gc.gridwidth = 1;
        add(boxAMassLabel, gc);

        JLabel boxAMuLabel = new JLabel("Mu ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 3;
        add(boxAMuLabel, gc);

        boxAMassTextField = new JTextField(10);
        boxAMassTextField.setText("1");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 2;
        add(boxAMassTextField, gc);

        boxAMuTextField = new JTextField(10);
        boxAMuTextField.setText("0");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 3;
        add(boxAMuTextField, gc);
    }

    private void createSlopeATable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;

        JLabel slopeATitleLabel = new JLabel("Slope A");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 2;
        gc.gridy = 4;
        gc.gridwidth = 2;
        gc.gridheight = 1;
        add(slopeATitleLabel, gc);

        JLabel slopeAAngleLabel = new JLabel("Angle ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 5;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        add(slopeAAngleLabel, gc);

        slopeAAngleTextField = new JTextField(10);
        slopeAAngleTextField.setText("45");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 5;
        add(slopeAAngleTextField, gc);
    }

    private void createBoxSystemTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 1;
        gc.weighty = 1;

        JLabel boxSystemLabel = new JLabel("Box System");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 2;
        gc.gridheight = 1;
        add(boxSystemLabel, gc);

        JLabel accelerationLabel = new JLabel("Acceleration ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        add(accelerationLabel, gc);

        JLabel velocityLabel = new JLabel("Velocity ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 3;
        add(velocityLabel, gc);

        accelerationTextField = new JTextField(10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 2;
        add(accelerationTextField, gc);

        velocityTextField = new JTextField(10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 3;
        add(velocityTextField, gc);
    }
}