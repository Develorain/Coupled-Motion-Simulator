package com.ahmad.Views.ModeTwo;

/** MainViewModeTwo
 * The main Graphics User Interface for mode two
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Controllers.ModeComboBoxController;
import com.ahmad.Controllers.ModeTwo.LeftAngleTextFieldController;
import com.ahmad.Controllers.ModeTwo.RightAngleTextFieldController;
import com.ahmad.Controllers.ModeTwo.StartButtonControllerModeTwo;
import com.ahmad.Controllers.ResetButtonController;
import com.ahmad.Models.ModeTwo.SystemModelModeTwo;
import com.ahmad.Startup;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.CustomPanel;
import com.ahmad.Tools.GraphicsPainter;
import com.ahmad.Tools.Paintable;
import com.ahmad.Views.View;

import javax.swing.*;
import java.awt.*;

public class MainViewModeTwo implements View, Paintable {
    private CustomPanel systemPanel; // Declare the system panel

    private SystemModelModeTwo systemModelModeTwo; // Declare the system model

    private JPanel mainPanel = new JPanel(new GridBagLayout()); // Declare the main panel
    private Startup startup; // Declare the startup

    private JTextField accelerationTextField; // Declare the acceleration text field
    private JTextField velocityTextField; // Declare the velocity text field

    private JTextField leftBoxMassTextField; // Declare the left box mass text field
    private JTextField leftBoxMuTextField; // Declare the left box mu text field
    private JTextField leftBoxFrictionTextField; // Declare the left box friction text field
    private JTextField leftWireTensionTextField; // Declare the left wire tension text field
    private JTextField leftSlopeAngleTextField; // Declare the left slope angle text field

    private JTextField middleBoxMassTextField; // Declare the middle box mass text field
    private JTextField middleBoxMuTextField; // Declare the middle box mu text field
    private JTextField middleBoxFrictionTextField; // Declare the middle box friction text field

    private JTextField rightBoxMassTextField; // Declare the right box mass text field
    private JTextField rightBoxMuTextField; // Declare the right box mu text field
    private JTextField rightBoxFrictionTextField; // Declare the right box friction text field
    private JTextField rightWireTensionTextField; // Declare the right box tension text field
    private JTextField rightSlopeAngleTextField; // Declare the right slope angle text field

    private JComboBox modesComboBox; // Declare the modes combobox
    private JButton startButton; // Declare the start button
    private JButton resetButton; // Declare the reset button

    /** Default Constructor
     * @param startup            a reference to the startup
     * @param systemModelModeTwo a reference to the system model */
    public MainViewModeTwo(Startup startup, SystemModelModeTwo systemModelModeTwo) {
        this.startup = startup;
        this.systemModelModeTwo = systemModelModeTwo;
        systemModelModeTwo.setView(this);

        systemPanel = new CustomPanel(this);
        systemPanel.setPreferredSize(new Dimension(Constants.SIMULATION_WIDTH_PIXELS, Constants.SIMULATION_HEIGHT_PIXELS));

        layoutScreen();
        registerControllers();
    }

    /** Paint method to draw custom graphics
     * @param graphics a reference to graphics instance */
    @Override
    public void paint(Graphics graphics) {
        GraphicsPainter.drawSlope(graphics, systemModelModeTwo.getLeftSlope());
        GraphicsPainter.drawSlope(graphics, systemModelModeTwo.getMiddleSlope());
        GraphicsPainter.drawSlope(graphics, systemModelModeTwo.getRightSlope());

        GraphicsPainter.drawPulley(graphics, systemModelModeTwo.getLeftPulley());
        GraphicsPainter.drawPulley(graphics, systemModelModeTwo.getRightPulley());

        GraphicsPainter.drawSlopedBox(graphics, systemModelModeTwo.getLeftBox(), systemModelModeTwo.getLeftSlopeAngle(), false);
        GraphicsPainter.drawSlopedBox(graphics, systemModelModeTwo.getMiddleBox(), systemModelModeTwo.getMiddleSlopeAngle(), false);
        GraphicsPainter.drawSlopedBox(graphics, systemModelModeTwo.getRightBox(), systemModelModeTwo.getRightSlopeAngle(), true);

        GraphicsPainter.drawWire(graphics, systemModelModeTwo.getLeftWire());
        GraphicsPainter.drawWire(graphics, systemModelModeTwo.getRightWire());
    }

    /** Gets data from the model and updates the view */
    @Override
    public void update() {
        systemPanel.repaint();        // Repaints the simulation area

        updateBoxSystemInfoTable();   // Updates the box system's information table
    }

    /** Repaints the view */
    @Override
    public void repaint() {
        systemPanel.repaint();
    }

    /** Updates all the information tables on the screen */
    private void updateBoxSystemInfoTable() {
        String accelerationValue = Double.toString(systemModelModeTwo.getAccelerationOfSystem());
        accelerationTextField.setText(accelerationValue);

        String velocityValue = Double.toString(systemModelModeTwo.getLeftBox().getVelocity().getR());
        velocityTextField.setText(velocityValue);

        String leftBoxFrictionValue = Double.toString(systemModelModeTwo.getLeftBox().getFriction());
        leftBoxFrictionTextField.setText(leftBoxFrictionValue);

        String middleBoxFrictionValue = Double.toString(systemModelModeTwo.getMiddleBox().getFriction());
        middleBoxFrictionTextField.setText(middleBoxFrictionValue);

        String rightBoxFrictionValue = Double.toString(systemModelModeTwo.getRightBox().getFriction());
        rightBoxFrictionTextField.setText(rightBoxFrictionValue);

        String leftWireTensionValue = Double.toString(systemModelModeTwo.getLeftWire().getTension());
        leftWireTensionTextField.setText(leftWireTensionValue);

        String rightWireTensionValue = Double.toString(systemModelModeTwo.getRightWire().getTension());
        rightWireTensionTextField.setText(rightWireTensionValue);
    }

    /** Registers all the view's controllers */
    private void registerControllers() {
        StartButtonControllerModeTwo sbc = new StartButtonControllerModeTwo(this, systemModelModeTwo);
        startButton.addActionListener(sbc);


        LeftAngleTextFieldController latfc = new LeftAngleTextFieldController(leftSlopeAngleTextField, systemModelModeTwo);
        leftSlopeAngleTextField.addKeyListener(latfc);

        RightAngleTextFieldController ratfc = new RightAngleTextFieldController(rightSlopeAngleTextField, systemModelModeTwo);
        rightSlopeAngleTextField.addKeyListener(ratfc);


        ResetButtonController rbc = new ResetButtonController(startup, modesComboBox);
        resetButton.addActionListener(rbc);


        ModeComboBoxController mcbc = new ModeComboBoxController(startup, modesComboBox);
        modesComboBox.addActionListener(mcbc);
    }

    /** Lays out the components of the screen */
    private void layoutScreen() {
        GridBagConstraints gc = new GridBagConstraints();

        // Creates and adds simulation view to the main JPanel
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 9;
        gc.weighty = 100;
        mainPanel.add(systemPanel, gc);

        createBoxSystemTable(gc);

        createLeftBoxTable(gc);
        createLeftWireTable(gc);
        createLeftSlopeTable(gc);

        createMiddleBoxTable(gc);

        createRightBoxTable(gc);
        createRightWireTable(gc);
        createRightSlopeTable(gc);

        createButtonTable(gc);
    }

    /** Creates the right slope table */
    private void createRightSlopeTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;

        JLabel rightSlopeTitleLabel = new JLabel("Right Slope");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 6;
        gc.gridy = 7;
        gc.gridwidth = 2;
        gc.gridheight = 1;
        mainPanel.add(rightSlopeTitleLabel, gc);

        JLabel rightSlopeAngleLabel = new JLabel("Angle ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 6;
        gc.gridy = 8;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        mainPanel.add(rightSlopeAngleLabel, gc);

        rightSlopeAngleTextField = new JTextField(10);
        rightSlopeAngleTextField.setText("45");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 7;
        gc.gridy = 8;
        mainPanel.add(rightSlopeAngleTextField, gc);
    }

    /** Creates the right box table */
    private void createRightBoxTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;

        JLabel rightBoxTitleLabel = new JLabel("Right Box");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 6;
        gc.gridy = 1;
        gc.gridwidth = 2;
        mainPanel.add(rightBoxTitleLabel, gc);

        JLabel rightBoxMassLabel = new JLabel("Mass ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 6;
        gc.gridy = 2;
        gc.gridwidth = 1;
        mainPanel.add(rightBoxMassLabel, gc);

        JLabel rightBoxMuLabel = new JLabel("Mu ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 6;
        gc.gridy = 3;
        gc.gridwidth = 1;
        mainPanel.add(rightBoxMuLabel, gc);

        JLabel rightBoxFrictionLabel = new JLabel("Friction ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 6;
        gc.gridy = 4;
        gc.gridwidth = 1;
        mainPanel.add(rightBoxFrictionLabel, gc);

        rightBoxMassTextField = new JTextField("2", 10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 7;
        gc.gridy = 2;
        mainPanel.add(rightBoxMassTextField, gc);

        rightBoxMuTextField = new JTextField(10);
        rightBoxMuTextField.setText("0");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 7;
        gc.gridy = 3;
        mainPanel.add(rightBoxMuTextField, gc);

        rightBoxFrictionTextField = new JTextField(10);
        rightBoxFrictionTextField.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 7;
        gc.gridy = 4;
        mainPanel.add(rightBoxFrictionTextField, gc);
    }

    /** Creates the button table */
    private void createButtonTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weighty = 1;

        String[] modesList = new String[]{"Two Box System", "Three Box System"};
        modesComboBox = new JComboBox<>(modesList);
        modesComboBox.setSelectedIndex(1);
        gc.gridx = 8;
        gc.gridy = 1;
        mainPanel.add(modesComboBox, gc);

        startButton = new JButton("Start Simulation");
        gc.gridx = 8;
        gc.gridy = 2;
        mainPanel.add(startButton, gc);

        resetButton = new JButton("Reset Simulation");
        gc.gridx = 8;
        gc.gridy = 3;
        mainPanel.add(resetButton, gc);
    }

    /** Creates the middle box table */
    private void createMiddleBoxTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;

        JLabel middleBoxTitleLabel = new JLabel("Middle Box");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 4;
        gc.gridy = 1;
        gc.gridwidth = 2;
        mainPanel.add(middleBoxTitleLabel, gc);

        JLabel middleBoxMassLabel = new JLabel("Mass ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 4;
        gc.gridy = 2;
        gc.gridwidth = 1;
        mainPanel.add(middleBoxMassLabel, gc);

        JLabel middleBoxMuLabel = new JLabel("Mu ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 4;
        gc.gridy = 3;
        gc.gridwidth = 1;
        mainPanel.add(middleBoxMuLabel, gc);

        JLabel middleBoxFrictionLabel = new JLabel("Friction ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 4;
        gc.gridy = 4;
        gc.gridwidth = 1;
        mainPanel.add(middleBoxFrictionLabel, gc);

        middleBoxMassTextField = new JTextField("1", 10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 5;
        gc.gridy = 2;
        mainPanel.add(middleBoxMassTextField, gc);

        middleBoxMuTextField = new JTextField(10);
        middleBoxMuTextField.setText("0");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 5;
        gc.gridy = 3;
        mainPanel.add(middleBoxMuTextField, gc);

        middleBoxFrictionTextField = new JTextField(10);
        middleBoxFrictionTextField.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 5;
        gc.gridy = 4;
        mainPanel.add(middleBoxFrictionTextField, gc);
    }

    /** Creates the left box table */
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

    /** Creates the left wire table */
    private void createLeftWireTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;

        JLabel leftWireTitleLabel = new JLabel("Left Wire");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 2;
        gc.gridy = 5;
        gc.gridwidth = 2;
        mainPanel.add(leftWireTitleLabel, gc);

        JLabel leftWireTensionLabel = new JLabel("Tension ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 6;
        gc.gridwidth = 1;
        mainPanel.add(leftWireTensionLabel, gc);

        leftWireTensionTextField = new JTextField(10);
        leftWireTensionTextField.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 6;
        mainPanel.add(leftWireTensionTextField, gc);
    }

    /** Creates the right wire table */
    private void createRightWireTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;

        JLabel rightWireTitleLabel = new JLabel("Right Wire");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 6;
        gc.gridy = 5;
        gc.gridwidth = 2;
        mainPanel.add(rightWireTitleLabel, gc);

        JLabel rightWireTensionLabel = new JLabel("Tension ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 6;
        gc.gridy = 6;
        gc.gridwidth = 1;
        mainPanel.add(rightWireTensionLabel, gc);

        rightWireTensionTextField = new JTextField(10);
        rightWireTensionTextField.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 7;
        gc.gridy = 6;
        mainPanel.add(rightWireTensionTextField, gc);
    }

    /** Creates the left slope table */
    private void createLeftSlopeTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;

        JLabel leftSlopeTitleLabel = new JLabel("Left Slope");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 2;
        gc.gridy = 7;
        gc.gridwidth = 2;
        gc.gridheight = 1;
        mainPanel.add(leftSlopeTitleLabel, gc);

        JLabel leftSlopeAngleLabel = new JLabel("Angle ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 8;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        mainPanel.add(leftSlopeAngleLabel, gc);

        leftSlopeAngleTextField = new JTextField(10);
        leftSlopeAngleTextField.setText("45");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 8;
        mainPanel.add(leftSlopeAngleTextField, gc);
    }

    /** Creates the box system table */
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
    }

    /** Returns the main panel
     * @return the main panel */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /** Returns the acceleration text field
     * @return the acceleration text field */
    public JTextField getAccelerationTextField() {
        return accelerationTextField;
    }

    /** Returns the velocity text field
     * @return the velocity text field */
    public JTextField getVelocityTextField() {
        return velocityTextField;
    }

    /** Returns the left box mass text field
     * @return the left box mass text field */
    public JTextField getLeftBoxMassTextField() {
        return leftBoxMassTextField;
    }

    /** Returns the left box mu text field
     * @return the left box mu text field */
    public JTextField getLeftBoxMuTextField() {
        return leftBoxMuTextField;
    }

    /** Returns the left box friction text field
     * @return the left box friction text field */
    public JTextField getLeftBoxFrictionTextField() {
        return leftBoxFrictionTextField;
    }

    /** Returns the left tension text field
     * @return the left tension text field */
    public JTextField getLeftWireTensionTextField() {
        return leftWireTensionTextField;
    }

    /** Returns the left slope angle text field
     * @return the left slope angle text field */
    public JTextField getLeftSlopeAngleTextField() {
        return leftSlopeAngleTextField;
    }

    /** Returns the middle box mass text field
     * @return the middle box mass text field */
    public JTextField getMiddleBoxMassTextField() {
        return middleBoxMassTextField;
    }

    /** Returns the middle box mu text field
     * @return the middle box mu text field */
    public JTextField getMiddleBoxMuTextField() {
        return middleBoxMuTextField;
    }

    /** Returns the middle box friction text field
     * @return the middle box friction text field */
    public JTextField getMiddleBoxFrictionTextField() {
        return middleBoxFrictionTextField;
    }

    /** Returns the right box mass text field
     * @return the right box mass text field */
    public JTextField getRightBoxMassTextField() {
        return rightBoxMassTextField;
    }

    /** Returns the right box mu text field
     * @return the right box mu text field */
    public JTextField getRightBoxMuTextField() {
        return rightBoxMuTextField;
    }

    /** Returns the right box friction text field
     * @return the right box friction text field */
    public JTextField getRightBoxFrictionTextField() {
        return rightBoxFrictionTextField;
    }

    /** Returns the right tension text field
     * @return the right tension text field */
    public JTextField getRightWireTensionTextField() {
        return rightWireTensionTextField;
    }

    /** Returns the right slope angle text field
     * @return the right slope angle text field */
    public JTextField getRightSlopeAngleTextField() {
        return rightSlopeAngleTextField;
    }

    /** Returns the start button
     * @return the start button */
    public JButton getStartButton() {
        return startButton;
    }
}