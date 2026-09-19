package org.firstinspires.ftc.teamcode.robot;

public class PIDFController {
    private double kP, kI, kD, kF;

    private double integral = 0;
    private double previousError = 0;
    private long previousTimeNs = 0;

    private double targetValue = 0;

    public PIDFController(double kP, double kI, double kD, double kF) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;

        previousTimeNs = System.nanoTime();
    }

    public void setTarget(double target) {
        targetValue = target;
    }

    public double calculate(double current) {
        long currentTimeNs = System.nanoTime();
        double dtSeconds = (currentTimeNs - previousTimeNs) / 1_000_000_000.0;

        if (dtSeconds <= 0) {
            dtSeconds = 0.001;
        }

        previousTimeNs = currentTimeNs;

        double error = targetValue - current;

        double pTerm = kP * error;

        integral += error * dtSeconds;
        double iTerm = kI * integral;

        double derivative = (error - previousError) / dtSeconds;
        double dTerm = kD * derivative;

        double fTerm = kF * targetValue;

        double output = pTerm + iTerm + dTerm + fTerm;

        output = Math.max(-1.0, Math.min(1.0, output));

        previousError = error;

        return output;
    }

    public void reset() {
        integral = 0;
        previousError = 0;
        previousTimeNs = System.nanoTime();
    }
}