package org.firstinspires.ftc.teamcode.mechanisms;

import static org.firstinspires.ftc.teamcode.robot.Config.STRAFE_COMPENSATION;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Drivetrain {

    private final DcMotorEx frontLeft;
    private final DcMotorEx frontRight;
    private final DcMotorEx backLeft;
    private final DcMotorEx backRight;
    private final IMU imu;

    public Drivetrain(DcMotorEx frontLeft, DcMotorEx frontRight, DcMotorEx backLeft, DcMotorEx backRight, IMU imu) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
        this.imu = imu;
    }

    public void robotCentricDrive(double strafe, double forward, double rotation) {

        strafe *= STRAFE_COMPENSATION;

        double denominator = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(rotation), 1);
        double frontLeftPower = (forward + strafe + rotation) / denominator;
        double backLeftPower = (forward - strafe + rotation) / denominator;
        double frontRightPower = (forward - strafe - rotation) / denominator;
        double backRightPower = (forward + strafe - rotation) / denominator;

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);
    }

    public void fieldCentricDrive(double strafe, double forward, double rotation) {

        double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double rotatedX = strafe * Math.cos(-heading) - forward * Math.sin(-heading);
        double rotatedY = strafe * Math.sin(-heading) + forward * Math.cos(-heading);

        robotCentricDrive(rotatedX, rotatedY, rotation);
    }
}