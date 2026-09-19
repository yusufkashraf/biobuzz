package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.robot.PIDFController;

public class Shooter {
    public enum ShooterState {
        IDLE,
        STANDBY,
        RUNNING
    }

    private DcMotorEx motor;
    private PIDFController controller;
    private ShooterState currentState = ShooterState.IDLE;
    private double coastVel, runVel;

    public Shooter(DcMotorEx motor, double kP, double kI, double kD, double kF,
                   double coastVel, double runVel) {
        this.motor = motor;
        this.controller = new PIDFController(kP, kI, kD, kF);
        this.coastVel = coastVel;
        this.runVel = runVel;
    }

    public void setState(ShooterState state) {
        if (state != currentState) {
            currentState = state;
            controller.reset();
        }
    }

    public ShooterState getState() {
        return currentState;
    }

    public void update() {
        double target = 0;

        switch (currentState) {
            case IDLE:
                target = 0;
                break;
            case STANDBY:
                target = coastVel;
                break;
            case RUNNING:
                target = runVel;
                break;
        }
        controller.setTarget(target);
        double motorPower = controller.calculate(motor.getVelocity());
        motor.setPower(motorPower);
    }

    public void stop() {
        setState(ShooterState.IDLE);
        motor.setPower(0);
        controller.reset();
    }
}