package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class TuleTutorial extends OpMode {

    @Override
    public void init() {
        drive_left = hardwareMap.dcMotor.get("Drive Left");
        drive_right = hardwareMap.dcMotor.get("Drive Right");
    }

    @Override
    public void loop() {
        drive_left.setPower(1.0f);
        drive_right.setPower(1.0f);
    }

    DcMotor drive_left;
    DcMotor drive_right;
}
