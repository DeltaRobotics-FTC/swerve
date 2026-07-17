package org.firstinspires.ftc.teamcode;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@TeleOp(name = "teleop")
public class basicTeleop extends OpMode {
    private Follower follower;
    public static Pose startingPose;

    DcMotorEx rf;
    DcMotorEx rb;
    DcMotorEx lf;
    DcMotorEx lb;


    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
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
        follower.update();

        follower.setTeleOpDrive(
                -gamepad1.left_stick_y,
                //-gamepad1.left_stick_x,
                0,
                -gamepad1.right_stick_x,
                true
        );


        telemetry.addData("rf", rf.getCurrentPosition());
        telemetry.addData("rb", rb.getCurrentPosition());
        telemetry.addData("lf", lf.getCurrentPosition());
        telemetry.addData("lb", lb.getCurrentPosition());

        telemetry.addData("starting pose", startingPose);

    }
}