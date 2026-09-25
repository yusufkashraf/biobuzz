package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static Follower create(HardwareMap h) {
        // return new Follower(Drivetrain, Localizer, Foresight);
        return null;
    }
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("frontLeft");
        c.frontRightName.set("frontRight");
        c.backLeftName.set("backLeft");
        c.backRightName.set("backRight");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);

        c.manualBrakeMode.set(true); //unsure what this will do
    });
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(-0.2212331426425243);
        c.yPodOffset.set(0.4783905209518793);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.11758908919831998);
                Controller secondaryTranslationalForward = Controller.proportional(0.043446026420448106);
                Controller primaryTranslationalLateral = Controller.proportional(0.16665172122925248);
                Controller secondaryTranslationalLateral = Controller.proportional(0.06157335797820519);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.015501697742681899));
                c.brake.set(Controller.proportionalFeedforward(0.013176443081279613));

                c.headingFeedback.set(Controller.proportional(2.2203405255296307));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.0363944288768374, 0.006030618625715638));

                c.linearBrakeCoefficients.set(Matrix.diag(0.03889481037312439, 0.06156930054214393));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0017693528841583855, 8.464897897690494E-4));

                c.maxAchievableForwardVelocity.set(65.28517237866717);
                c.maxAchievableStrafeVelocity.set(56.90396019033053);
                c.naturalForwardDeceleration.set(40.17776809951195);
                c.naturalStrafeDeceleration.set(61.81293730726842);
            }
    );
}