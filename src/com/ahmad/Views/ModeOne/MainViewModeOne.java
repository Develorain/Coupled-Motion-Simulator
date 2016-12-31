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

    public SystemModelModeOne systemModelModeOne;

    public JPanel mainPanel = new JPanel(new GridBagLayout());

    public JTextField accelerationTextField;
    public JTextField velocityTextField;
    public JTextField frictionTextField;
    public JTextField tensionTextField;

    public JButton startButton;
    public JButton pauseButton;
    public JButton resetButton;
    public JComboBox modesComboBox;

    public JTextField leftBoxMassTextField;
    public JTextField leftBoxMuTextField;

    public JTextField rightBoxMassTextField;

    public JTextField leftSlopeAngleTextField;

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
        String accelerationValue = Double.toString(systemModelModeOne.getAccelerationOfSystem());
        accelerationTextField.setText(accelerationValue);

        String velocityValue = Double.toString(systemModelModeOne.getSlopedBox().getVelocityMagnitude());
        velocityTextField.setText(velocityValue);

        String frictionValue = Double.toString(systemModelModeOne.frictionOfSystem);
        frictionTextField.setText(frictionValue);

        String tensionValue = Double.toString(systemModelModeOne.wire.tension);
        tensionTextField.setText(tensionValue);
    }

    private void registerControllers() {
        StartButtonController sbl = new StartButtonController(this, systemModelModeOne);
        startButton.addActionListener(sbl);


        AngleTextFieldControllerModeOne atfc = new AngleTextFieldControllerModeOne(leftSlopeAngleTextField, systemModelModeOne);
        leftSlopeAngleTextField.addKeyListener(atfc);


        ResetButtonController rbc = new ResetButtonController(accelerationTextField, velocityTextField,
                leftBoxMassTextField, leftBoxMuTextField, rightBoxMassTextField, leftSlopeAngleTextField);
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

        String[] modesList = new String[]{"Mode 1", "Mode 2"};
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

        JLabel boxBTitleLabel = new JLabel("Right Box");
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

        JLabel boxATitleLabel = new JLabel("Left Box");
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

        JLabel slopeATitleLabel = new JLabel("Left Slope");
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

        JLabel frictionLabel = new JLabel("Friction ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 4;
        mainPanel.add(frictionLabel, gc);

        JLabel tensionLabel = new JLabel("Tension ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 5;
        mainPanel.add(tensionLabel, gc);

        accelerationTextField = new JTextField(10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 2;
        mainPanel.add(accelerationTextField, gc);

        velocityTextField = new JTextField(10);
        velocityTextField.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 3;
        mainPanel.add(velocityTextField, gc);

        frictionTextField = new JTextField(10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 4;
        mainPanel.add(frictionTextField, gc);

        tensionTextField = new JTextField(10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 5;
        mainPanel.add(tensionTextField, gc);
    }

    @Override
    public void paint(Graphics graphics) {
        GraphicsPainter.drawSlopedBox(graphics, systemModelModeOne.getSlopedBox(), systemModelModeOne.getSlopeAngle());
        GraphicsPainter.drawDanglingBox(graphics, systemModelModeOne.getDanglingBox());
        GraphicsPainter.drawSlope(graphics, systemModelModeOne.slope);
        GraphicsPainter.drawWire(graphics, systemModelModeOne.wire);

        graphics.drawOval(systemModelModeOne.getDanglingBox().getX(), systemModelModeOne.getDanglingBox().getY() - 50, 50, 50);
    }
}