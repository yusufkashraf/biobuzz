package org.firstinspires.ftc.teamcode.robot;

public class Config {

    /**
     * contains all (most) constants used
     */

    public static double INTAKE_POWER = 1.0;
    public static double OUTTAKE_POWER = -1.0;

    public static double STRAFE_COMPENSATION = 1.1;

    // Pollen shooter
    public static double POLLEN_KP = 0.01;
    public static double POLLEN_KI = 0.001;
    public static double POLLEN_KD = 0.0;
    public static double POLLEN_KF = 0.1;
    public static double POLLEN_STANDBY_VEL = 1000;  //ticks per sec
    public static double POLLEN_RUN_VEL = 2000;

    // Nectar shooter
    public static double NECTAR_KP = 0.01;
    public static double NECTAR_KI = 0.001;
    public static double NECTAR_KD = 0.0;
    public static double NECTAR_KF = 0.1;
    public static double NECTAR_STANDBY_VEL = 1000;
    public static double NECTAR_RUN_VEL = 2000;

}