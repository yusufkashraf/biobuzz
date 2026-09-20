package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;

public class Robot {

    /**
     * represents the whole robot by initializing hardware and "compiling" all the mechanisms together
     */
    public final Hardware hardware;

    public final Drivetrain drivetrain;
    public final Intake intake;
    public final Shooter pollenShooter;
    public final Shooter nectarShooter;

    public final Limelight3A limelight;

    public Robot(HardwareMap hardwareMap) {

        hardware = new Hardware();
        hardware.init(hardwareMap);

        drivetrain = new Drivetrain(
                hardware.frontLeft,
                hardware.frontRight,
                hardware.backLeft,
                hardware.backRight,
                hardware.imu
        );

        pollenShooter = new Shooter(
                hardware.pollenShooter,
                Config.POLLEN_KP, Config.POLLEN_KI, Config.POLLEN_KD, Config.POLLEN_KF,
                Config.POLLEN_STANDBY_VEL, Config.POLLEN_RUN_VEL
        );

        nectarShooter = new Shooter(
                hardware.nectarShooter,
                Config.NECTAR_KP, Config.NECTAR_KI, Config.NECTAR_KD, Config.NECTAR_KF,
                Config.NECTAR_STANDBY_VEL, Config.NECTAR_RUN_VEL
        );

        intake = new Intake(hardware.intake);
        limelight = hardware.limelight;
    }
}