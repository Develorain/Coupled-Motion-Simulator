package com.ahmad.Views.ModeOne;

import com.ahmad.Controllers.ModeComboBoxController;
import com.ahmad.Controllers.ModeOne.AngleTextFieldControllerModeOne;
import com.ahmad.Controllers.ResetButtonController;
import com.ahmad.Controllers.StartButtonController;
import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Tools.CustomPanel;
import com.ahmad.Tools.Globals;
import com.ahmad.Tools.GraphicsPainter;
import com.ahmad.Tools.Paintable;
import com.ahmad.Views.View;

import javax.swing.*;
import java.awt.*;

public class MainViewModeOne implements View, Paintable {
    public CustomPanel systemPanel;

    private SystemModelModeOne systemModelModeOne;

    public JPanel mainPanel = new JPanel(new GridBagLayout());

    private JTextField accelerationTextField;
    private JTextField velocityTextField;

    private JButton startButton;
    private JButton pauseButton;
    private JButton resetButton;
    private JComboBox modesComboBox;

    private JTextField boxAMassTextField;
    private JTextField boxAMuTextField;

    private JTextField boxBMassTextField;

    private JTextField slopeAAngleTextField;

    public MainViewModeOne(SystemModelModeOne systemModelModeOne) {
        this.systemModelModeOne = systemModelModeOne;
        systemModelModeOne.setView(this);

        systemPanel = new CustomPanel(this);
        systemPanel.setPreferredSize(new Dimension(Globals.SIMULATION_WIDTH_PIXELS, Globals.SIMULATION_HEIGHT_PIXELS));

        layoutScreen();
        registerControllers();
    }

    @Override
    public void update() {
        systemPanel.repaint(); // Repaints the simulation area

        updateBoxSystemInfoTable();       // Updates the box system's information table
    }

    // Updates all the information tables
    private void updateBoxSystemInfoTable() {
        String accel = Double.toString(systemModelModeOne.getAccelerationOfSystem());
        accelerationTextField.setText(accel);

        String vel = Double.toString(systemModelModeOne.getSlopedBox().getVelocityMagnitude());
        velocityTextField.setText(vel);
    }

    private void registerControllers() {
        StartButtonController sbl = new StartButtonController(systemModelModeOne);
        startButton.addActionListener(sbl);


        AngleTextFieldControllerModeOne atfc = new AngleTextFieldControllerModeOne(slopeAAngleTextField, systemModelModeOne);
        slopeAAngleTextField.addKeyListener(atfc);


        ResetButtonController rbc = new ResetButtonController(accelerationTextField, velocityTextField,
                boxAMassTextField, boxAMuTextField, boxBMassTextField, slopeAAngleTextField);
        resetButton.addActionListener(rbc);


        ModeComboBoxController mcbc = new ModeComboBoxController(modesComboBox);
        modesComboBox.addActionListener(mcbc);
    }

    private void layoutScreen() {
        GridBagConstraints gc = new GridBagConstraints();

        // Creates and adds simulation view to the main JPanel
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 7;
        gc.weighty = 100;
        mainPanel.add(systemPanel, gc);

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

        boxBMassTextField = new JTextField("1", 10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 5;
        gc.gridy = 2;
        mainPanel.add(boxBMassTextField, gc);
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

        boxAMassTextField = new JTextField(10);
        boxAMassTextField.setText("1");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 2;
        mainPanel.add(boxAMassTextField, gc);

        boxAMuTextField = new JTextField(10);
        boxAMuTextField.setText("0");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 3;
        mainPanel.add(boxAMuTextField, gc);
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

        slopeAAngleTextField = new JTextField(10);
        slopeAAngleTextField.setText("45");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 5;
        mainPanel.add(slopeAAngleTextField, gc);
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

    @Override
    public void paint(Graphics graphics) {
        graphics.drawLine(0, 0, Globals.SIMULATION_WIDTH_PIXELS / 2, Globals.SIMULATION_HEIGHT_PIXELS / 2);

        GraphicsPainter.drawSlopedBox(graphics, systemModelModeOne.getSlopedBox(), systemModelModeOne.getSlopeAngle(), true);
        GraphicsPainter.drawDanglingBox(graphics, systemModelModeOne.getDanglingBox());
        GraphicsPainter.drawSlope(graphics, systemModelModeOne.slope);
    }
}