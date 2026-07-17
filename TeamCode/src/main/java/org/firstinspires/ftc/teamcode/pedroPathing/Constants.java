package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.SwerveConstants;
import com.pedropathing.ftc.localization.constants.TwoWheelConstants;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.DiffyEncodersAsAnalog;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants();

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static SwerveConstants swerveConstants = new SwerveConstants()
            .maxPower(1);

    private static DiffyPod left(HardwareMap hardwareMap) {
        DiffyPod pod = new DiffyPod(
                hardwareMap,
                new DiffyEncodersAsAnalog(
                        hardwareMap.get(DcMotorEx.class, "motorLF"),
                        hardwareMap.get(DcMotorEx.class, "motorLB"),
                        78.0/535.0
                ),
                "motorLF",
                "motorLB",
                DcMotorSimple.Direction.REVERSE,
                DcMotorSimple.Direction.FORWARD,
                new PIDFCoefficients(0.2,0, 0, 0),
                0,
                new Pose(-6, 0, 0),
                0,
                3.3,
                false
        );

        return pod;
    }

    public static DiffyPod right(HardwareMap hardwareMap) {
        DiffyPod pod = new DiffyPod(
                hardwareMap,
                new DiffyEncodersAsAnalog(
                        hardwareMap.get(DcMotorEx.class, "motorRF"),
                        hardwareMap.get(DcMotorEx.class, "motorRB"),
                        78.0/535.0
                ),
                "motorRF",
                "motorRB",
                DcMotorSimple.Direction.FORWARD,
                DcMotorSimple.Direction.REVERSE,
                new PIDFCoefficients(0.2,0, 0, 0),
                0,
                new Pose(6, 0, 0),
                0,
                3.3,
                true
        );

        return pod;
    }

    public static TwoWheelConstants localizerConstants = new TwoWheelConstants()
            .forwardEncoder_HardwareMapName("leftSlideFlip")
            .strafeEncoder_HardwareMapName("rightSlideFlip")
            .IMU_HardwareMapName("imu")
            .IMU_Orientation(
                    new RevHubOrientationOnRobot(
                            RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                            RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
                    )
            );
    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .twoWheelLocalizer(localizerConstants)
                .swerveDrivetrain(swerveConstants,
                        left(hardwareMap),
                        right(hardwareMap))
                .build();
    }
}
