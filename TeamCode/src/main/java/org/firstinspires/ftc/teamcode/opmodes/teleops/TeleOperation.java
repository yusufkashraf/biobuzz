package org.firstinspires.ftc.teamcode.opmodes.teleops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp
public class TeleOperation extends LinearOpMode {

    @Override
    public void runOpMode() {

        Robot robot = new Robot(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {


            //free will! we can choose robot or field centric!

            //robot.drivetrain.robotCentricDrive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
            robot.drivetrain.fieldCentricDrive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);


            if (gamepad1.a) {
                robot.intake.setState(Intake.IntakeState.INTAKE);
            } else if (gamepad1.b) {
                robot.intake.setState(Intake.IntakeState.OUTTAKE);
            } else {
                robot.intake.setState(Intake.IntakeState.IDLE);
            }

            if (gamepad1.right_trigger_pressed) {
                robot.pollenShooter.setState(Shooter.ShooterState.RUNNING);
            } else if (gamepad1.right_bumper) {
                robot.pollenShooter.setState(Shooter.ShooterState.STANDBY);
            } else {
                robot.pollenShooter.setState(Shooter.ShooterState.IDLE);
            }

            if (gamepad1.left_trigger_pressed) {
                robot.nectarShooter.setState(Shooter.ShooterState.RUNNING);
            } else if (gamepad1.left_bumper) {
                robot.nectarShooter.setState(Shooter.ShooterState.STANDBY);
            } else {
                robot.nectarShooter.setState(Shooter.ShooterState.IDLE);
            }

            robot.intake.update();
            robot.pollenShooter.update();
            robot.nectarShooter.update();


            telemetry.addData("Intake", robot.intake.getState());
            telemetry.addData("Heading",
                    robot.hardware.imu.getRobotYawPitchRollAngles().getYaw(
                            org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES
                    )
            );
            telemetry.update();
        }
    }
}