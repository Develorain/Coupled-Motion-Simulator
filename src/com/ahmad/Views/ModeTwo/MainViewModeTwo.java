package com.ahmad.Views.ModeTwo;

import com.ahmad.Controllers.ModeComboBoxController;
import com.ahmad.Controllers.ModeTwo.LeftAngleTextFieldController;
import com.ahmad.Controllers.ModeTwo.RightAngleTextFieldController;
import com.ahmad.Controllers.ResetButtonController;
import com.ahmad.Controllers.StartButtonController;
import com.ahmad.Models.ModeTwo.SystemModelModeTwo;
import com.ahmad.Views.ModeOne.SystemView;
import com.ahmad.Views.View;

import javax.swing.*;
import java.awt.*;

public class MainViewModeTwo implements View {
    private SystemModelModeTwo systemModelModeTwo;
    public SystemView systemView;

    public JPanel mainPanel = new JPanel(new GridBagLayout());

    private JTextField accelerationTextField;
    private JTextField velocityTextField;

    private JButton startButton;
    private JButton pauseButton;
    private JButton resetButton;
    private JComboBox modesComboBox;

    private JTextField leftBoxMassTextField;
    private JTextField leftBoxMuTextField;


    private JTextField middleBoxMassTextField;
    private JTextField middleBoxMuTextField;

    private JTextField rightBoxMassTextField;
    private JTextField rightBoxMuTextField;

    private JTextField leftSlopeAngleTextField;
    private JTextField rightSlopeAngleTextField;

    public MainViewModeTwo(SystemModelModeTwo systemModelModeTwo) {
        this.systemModelModeTwo = systemModelModeTwo;
        systemModelModeTwo.setView(this);

        layoutScreen();
        registerControllers();
    }

    @Override
    public void update() {
        systemView.systemPanel.repaint(); // Repaints the simulation area

        updateBoxSystemInfoTable();       // Updates the box system's information table
    }

    // Updates all the information tables
    private void updateBoxSystemInfoTable() {
        String accel = Double.toString(systemModelModeTwo.getAccelerationOfSystem());
        accelerationTextField.setText(accel);

        String vel = Double.toString(systemModelModeTwo.leftBox.getVelocityMagnitude());
        velocityTextField.setText(vel);
    }

    private void registerControllers() {
        StartButtonController sbl = new StartButtonController(systemModelModeTwo);
        startButton.addActionListener(sbl);


        LeftAngleTextFieldController latfc = new LeftAngleTextFieldController(leftSlopeAngleTextField, systemModelModeTwo);
        leftSlopeAngleTextField.addKeyListener(latfc);

        RightAngleTextFieldController ratfc = new RightAngleTextFieldController(leftSlopeAngleTextField, systemModelModeTwo);
        leftSlopeAngleTextField.addKeyListener(ratfc);


        ResetButtonController rbc = new ResetButtonController(accelerationTextField, velocityTextField,
                leftBoxMassTextField, leftBoxMuTextField, rightBoxMassTextField, leftSlopeAngleTextField);
        resetButton.addActionListener(rbc);


        ModeComboBoxController mcbc = new ModeComboBoxController(modesComboBox);
        modesComboBox.addActionListener(mcbc);
    }

    private void layoutScreen() {
        GridBagConstraints gc = new GridBagConstraints();

        // Creates and adds simulation view to the main JPanel
        //systemView = new SystemView(systemModelModeTwo);
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 7;
        gc.weighty = 100;
        //mainPanel.add(systemView.systemPanel, gc);

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
        modesComboBox = new JComboBox(modesList);
        gc.gridx = 6;
        gc.gridy = 1;
        mainPanel.add(modesComboBox, gc);

        startButton = new JButton("Start Simulation");
        gc.gridx = 6;
        gc.gridy = 2;
        mainPanel.add(startButton, gc);

        pauseButton = new JButton("Pause Simulation");
        gc.gridx = 6;
        gc.gridy = 3;
        mainPanel.add(pauseButton, gc);

        resetButton = new JButton("Reset Simulation");
        gc.gridx = 6;
        gc.gridy = 4;
        mainPanel.add(resetButton, gc);
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
        mainPanel.add(boxBTitleLabel, gc);

        JLabel boxBMassLabel = new JLabel("Mass ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 4;
        gc.gridy = 2;
        gc.gridwidth = 1;
        mainPanel.add(boxBMassLabel, gc);

        rightBoxMassTextField = new JTextField("1", 10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 5;
        gc.gridy = 2;
        mainPanel.add(rightBoxMassTextField, gc);
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
        mainPanel.add(boxATitleLabel, gc);

        JLabel boxAMassLabel = new JLabel("Mass ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 2;
        gc.gridwidth = 1;
        mainPanel.add(boxAMassLabel, gc);

        JLabel boxAMuLabel = new JLabel("Mu ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 3;
        mainPanel.add(boxAMuLabel, gc);

        leftBoxMassTextField = new JTextField(10);
        leftBoxMassTextField.setText("1");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 2;
        mainPanel.add(leftBoxMassTextField, gc);

        leftBoxMuTextField = new JTextField(10);
        leftBoxMuTextField.setText("0");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 3;
        mainPanel.add(leftBoxMuTextField, gc);
    }

    private void createSlopeATable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;

        JLabel slopeATitleLabel = new JLabel("Slope A");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 2;
        gc.gridy = 4;
        gc.gridwidth = 2;
        gc.gridheight = 1;
        mainPanel.add(slopeATitleLabel, gc);

        JLabel slopeAAngleLabel = new JLabel("Angle ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 5;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        mainPanel.add(slopeAAngleLabel, gc);

        leftSlopeAngleTextField = new JTextField(10);
        leftSlopeAngleTextField.setText("45");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 5;
        mainPanel.add(leftSlopeAngleTextField, gc);
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
        mainPanel.add(boxSystemLabel, gc);

        JLabel accelerationLabel = new JLabel("Acceleration ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        mainPanel.add(accelerationLabel, gc);

        JLabel velocityLabel = new JLabel("Velocity ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 3;
        mainPanel.add(velocityLabel, gc);

        accelerationTextField = new JTextField(10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 2;
        mainPanel.add(accelerationTextField, gc);

        velocityTextField = new JTextField(10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 3;
        mainPanel.add(velocityTextField, gc);
    }
}