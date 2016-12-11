package com.ahmad.Models.ModeOne;

import com.ahmad.Models.Model;
import com.ahmad.Models.WireModel;
import com.ahmad.Models.SlopeModel;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public class SystemModel extends Model {
    private static final double GRAVITY = 9.8;

    private SlopedBoxModel boxA;
    private DanglingBoxModel boxB;

    public SlopeModel slopeA;

    private WireModel wireA;

    private double frictionSystem;
    private double acceleration;

    private double slopeAngle;

    public SystemModel() {
        slopeAngle = 45; // default value of 45

        boxA = new SlopedBoxModel(1, slopeAngle, 0);         // get these values from the view
        boxB = new DanglingBoxModel(1, slopeAngle, 0);

        slopeA = new SlopeModel(this);
    }

    public void update() {
        updateFriction();
        updateAcceleration();
        updateTension();
    }

    private void updateFriction() {
        frictionSystem = boxA.getMass() * GRAVITY * MathTools.cos(slopeAngle) * boxA.getMu();
        //frictionSystem = 10;
    }

    // this method is here because we need access to all boxes to calculate acceleration
    private void updateAcceleration() {
        acceleration = (boxB.getMass() * GRAVITY - boxA.getMass() * GRAVITY * MathTools.sin(slopeAngle) - frictionSystem)
                / (boxA.getMass() + boxB.getMass());

        Vector accelerationA = Vector.createFromPolar(acceleration, slopeAngle);
        Vector accelerationB = Vector.createFromPolar(acceleration, -90);

        boxA.setAcceleration(accelerationA);
        boxB.setAcceleration(accelerationB);
    }

    private void updateTension() {
        wireA = new WireModel(boxB);
        wireA.calculateTension(acceleration);

        System.out.println("Tension: " + wireA.tension);
    }

    public void setSlopeAngle(double slopeAngle) {
        this.slopeAngle = slopeAngle;
    }

    public double getSlopeAngle() {
        return slopeAngle;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public SlopedBoxModel getBoxA() {
        return boxA;
    }

    public DanglingBoxModel getBoxB() {
        return boxB;
    }
}
