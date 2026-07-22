package org.firstinspires.ftc.teamcode;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.pedroPathing.DiffyPod;

@TeleOp(name = "teleop")
public class basicTeleop extends OpMode {
    private Follower follower;
    public static Pose startingPose;

    DiffyPod right;
    DiffyPod left;

    DcMotorEx rf;
    DcMotorEx rb;
    DcMotorEx lf;
    DcMotorEx lb;


    @Override
    public void init() {
        right = Constants.right(hardwareMap);
        left = Constants.left(hardwareMap);

        follower = Constants.createFollower(hardwareMap, right, left);
        follower.setStartingPose(startingPose == null ? new Pose() : startingPose);
        follower.update();

        rf = hardwareMap.get(DcMotorEx.class, "motorRF");
        rb = hardwareMap.get(DcMotorEx.class, "motorRB");
        lf = hardwareMap.get(DcMotorEx.class, "motorLF");
        lb = hardwareMap.get(DcMotorEx.class, "motorLB");
    }

    @Override
    public void start() {
        follower.startTeleopDrive();
    }

    @Override
    public void loop() {
//        follower.update();
//
//        follower.setTeleOpDrive(
//                -gamepad1.left_stick_y,
//                //-gamepad1.left_stick_x,
//                0,
//                -gamepad1.right_stick_x,
//                true
//        );

        rf.setPower(1);
        rb.setPower(-1);
        lf.setPower(1);
        lb.setPower(1);

        telemetry.addData("rf", rf.getCurrentPosition());
        telemetry.addData("rb", rb.getCurrentPosition());
        telemetry.addData("lf", lf.getCurrentPosition());
        telemetry.addData("lb", lb.getCurrentPosition());

        telemetry.addData("starting pose", startingPose);

        telemetry.addData("\n----START SWERVE----", "");

        telemetry.addData("\n--RIGHT--", "");

        telemetry.addData("actualRad", right.actualRad);
        telemetry.addData("desiredRad", right.desiredRad);
        telemetry.addData("magnitude", right.mag);
        telemetry.addData("direction", right.dir);
        telemetry.addData("errorRad", right.errorRad);
        telemetry.addData("turnPower", right.turnPower);
        telemetry.addData("drivePower", right.drivePower);

        telemetry.addData("\n--LEFT--", "");

        telemetry.addData("actualRad", left.actualRad);
        telemetry.addData("desiredRad", left.desiredRad);
        telemetry.addData("magnitude", left.mag);
        telemetry.addData("direction", left.dir);
        telemetry.addData("errorRad", left.errorRad);
        telemetry.addData("turnPower", left.turnPower);
        telemetry.addData("drivePower", left.drivePower);

        telemetry.addData("rightVoltage", right.getVoltage());
        telemetry.addData("leftVoltage", left.getVoltage());

        telemetry.addData("\n----END SWERVE----", "");

    }
}