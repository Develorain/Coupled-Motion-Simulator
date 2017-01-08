package com.ahmad.Views.ModeOne;

import com.ahmad.Controllers.InputTypeComboBoxController;
import com.ahmad.Controllers.ModeComboBoxController;
import com.ahmad.Controllers.ModeOne.AngleTextFieldControllerModeOne;
import com.ahmad.Controllers.ResetButtonController;
import com.ahmad.Controllers.StartButtonController;
import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.CustomPanel;
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
    public JTextField leftBoxFrictionTextField;
    public JTextField tensionTextField;

    public JTextField leftBoxMassTextField;
    public JTextField leftBoxMuTextField;

    public JTextField rightBoxMassTextField;

    public JTextField leftSlopeAngleTextField;

    public JComboBox inputTypeComboBox;
    public JComboBox modesComboBox;
    public JButton startButton;
    public JButton pauseButton;
    public JButton resetButton;

    public MainViewModeOne(SystemModelModeOne systemModelModeOne) {
        this.systemModelModeOne = systemModelModeOne;
        systemModelModeOne.setView(this);

        systemPanel = new CustomPanel(this);
        systemPanel.setPreferredSize(new Dimension(Constants.SIMULATION_WIDTH_PIXELS, Constants.SIMULATION_HEIGHT_PIXELS));

        layoutScreen();
        registerControllers();
    }

    @Override
    public void update() {
        systemPanel.repaint(); // Repaints the simulation area

        updateBoxSystemInfoTable();       // Updates the box system's information table
    }

    @Override
    public void repaint() {
        systemPanel.repaint();
    }

    // Updates all the information table
    private void updateBoxSystemInfoTable() {
        String accelerationValue = Double.toString(systemModelModeOne.getAccelerationOfSystem());
        accelerationTextField.setText(accelerationValue);

        String velocityValue = Double.toString(systemModelModeOne.getSlopedBox().velocity.getX() > 0
                ? systemModelModeOne.getSlopedBox().velocity.getR()
                : -systemModelModeOne.getSlopedBox().velocity.getR());
        velocityTextField.setText(velocityValue);

        String tensionValue = Double.toString(systemModelModeOne.wire.tension);
        tensionTextField.setText(tensionValue);

        String leftBoxMassValue = Double.toString(systemModelModeOne.slopedBox.getMass());
        leftBoxMassTextField.setText(leftBoxMassValue);

        String leftBoxMuValue = Double.toString(systemModelModeOne.slopedBox.getMu());
        leftBoxMuTextField.setText(leftBoxMuValue);

        String leftBoxFrictionValue = Double.toString(systemModelModeOne.slopedBox.friction);
        leftBoxFrictionTextField.setText(leftBoxFrictionValue);

        String leftSlopeAngleValue = Double.toString(systemModelModeOne.slopeAngle);
        leftSlopeAngleTextField.setText(leftSlopeAngleValue);

        String rightBoxMassValue = Double.toString(systemModelModeOne.danglingBox.getMass());
        rightBoxMassTextField.setText(rightBoxMassValue);
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

        InputTypeComboBoxController itcbc = new InputTypeComboBoxController(this);
        inputTypeComboBox.addActionListener(itcbc);
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

        createLeftBoxTable(gc);
        createLeftSlopeTable(gc);

        createRightBoxTable(gc);

        createButtonTable(gc);
    }

    private void createButtonTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weighty = 1;

        String[] inputTypeList = new String[]{
                "Solve for Friction, Acceleration, and Tension",
                "Solve for Acceleration and Left Mass",
                "Solve for Friction, Left Mu, and Right Mass"
        };
        inputTypeComboBox = new JComboBox<>(inputTypeList);
        gc.gridx = 6;
        gc.gridy = 1;
        mainPanel.add(inputTypeComboBox, gc);

        String[] modesList = new String[]{"Two Box System", "Three Box System"};
        modesComboBox = new JComboBox<>(modesList);
        gc.gridx = 6;
        gc.gridy = 2;
        mainPanel.add(modesComboBox, gc);

        startButton = new JButton("Start Simulation");
        gc.gridx = 6;
        gc.gridy = 3;
        mainPanel.add(startButton, gc);

        pauseButton = new JButton("Pause Simulation");
        gc.gridx = 6;
        gc.gridy = 4;
        mainPanel.add(pauseButton, gc);

        resetButton = new JButton("Reset Simulation");
        gc.gridx = 6;
        gc.gridy = 5;
        mainPanel.add(resetButton, gc);
    }

    private void createRightBoxTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;

        JLabel rightBoxTitleLabel = new JLabel("Right Box");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 4;
        gc.gridy = 1;
        gc.gridwidth = 2;
        mainPanel.add(rightBoxTitleLabel, gc);

        JLabel rightBoxMassLabel = new JLabel("Mass ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 4;
        gc.gridy = 2;
        gc.gridwidth = 1;
        mainPanel.add(rightBoxMassLabel, gc);

        rightBoxMassTextField = new JTextField("1", 10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 5;
        gc.gridy = 2;
        mainPanel.add(rightBoxMassTextField, gc);
    }

    private void createLeftBoxTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;

        JLabel leftBoxTitleLabel = new JLabel("Left Box");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 2;
        gc.gridy = 1;
        gc.gridwidth = 2;
        mainPanel.add(leftBoxTitleLabel, gc);

        JLabel leftBoxMassLabel = new JLabel("Mass ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 2;
        gc.gridwidth = 1;
        mainPanel.add(leftBoxMassLabel, gc);

        JLabel leftBoxMuLabel = new JLabel("Mu ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 3;
        mainPanel.add(leftBoxMuLabel, gc);

        JLabel leftBoxFrictionLabel = new JLabel("Friction ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 4;
        mainPanel.add(leftBoxFrictionLabel, gc);

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

        leftBoxFrictionTextField = new JTextField(10);
        leftBoxFrictionTextField.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 4;
        mainPanel.add(leftBoxFrictionTextField, gc);
    }

    private void createLeftSlopeTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;

        JLabel leftSlopeTitleLabel = new JLabel("Left Slope");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 2;
        gc.gridy = 5;
        gc.gridwidth = 2;
        gc.gridheight = 1;
        mainPanel.add(leftSlopeTitleLabel, gc);

        JLabel leftSlopeAngleLabel = new JLabel("Angle ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 6;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        mainPanel.add(leftSlopeAngleLabel, gc);

        leftSlopeAngleTextField = new JTextField(10);
        leftSlopeAngleTextField.setText("45");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 6;
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

        JLabel tensionLabel = new JLabel("Tension ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 4;
        mainPanel.add(tensionLabel, gc);

        accelerationTextField = new JTextField(10);
        accelerationTextField.setEditable(false);
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

        tensionTextField = new JTextField(10);
        tensionTextField.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 4;
        mainPanel.add(tensionTextField, gc);
    }

    @Override
    public void paint(Graphics graphics) {
        GraphicsPainter.drawSlope(graphics, systemModelModeOne.slope);
        GraphicsPainter.drawPulley(graphics, systemModelModeOne.pulley);

        GraphicsPainter.drawSlopedBox(graphics, systemModelModeOne.getSlopedBox(), systemModelModeOne.getSlopeAngle(), false);
        GraphicsPainter.drawDanglingBox(graphics, systemModelModeOne.getDanglingBox());
        GraphicsPainter.drawWire(graphics, systemModelModeOne.wire);
    }
}