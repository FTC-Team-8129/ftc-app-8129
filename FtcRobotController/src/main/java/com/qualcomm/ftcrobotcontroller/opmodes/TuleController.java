package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleController extends TuleControlFunctions {

    boolean reverseControl = false;
    double lid_timer = 0.0f;

    public TuleController() {

    }

    @Override
    public void start() {
		controlInit();
    }

    @Override
    public void loop() {

        lid_timer = lid_timer - 0.05f;
        if (lid_timer < 0.0f) {
            lid_timer = 0.0f;
        }

        if (gamepad1.a) {
            reverseControl = true;
        } else if (gamepad1.b) {
            reverseControl = false;
        }

        if (reverseControl) {
            setDrivePower(gamepad1.right_stick_y, gamepad1.left_stick_y);
        } else {
            setDrivePower(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
        }

        if (-gamepad2.right_stick_y > 0.2f) {
            setArmPower(1.0f);
        } else if (-gamepad2.right_stick_y < -0.2f) {
            setArmPower(-1.0f);
        } else {
            setArmPower(0.0f);
        }

        if (Math.abs(gamepad2.left_stick_y) > 0.2) {
            setMotorPower(pivot, -gamepad2.left_stick_y);
        } else {
            setMotorPower(pivot, 0.0f);
        }

        if (Math.abs(gamepad2.right_trigger) > 0.05) {
            setMotorPower(dump, -gamepad2.right_trigger);
        } else if (Math.abs(gamepad2.left_trigger) > 0.05) {
            setMotorPower(dump, gamepad2.left_trigger);
        } else {
            setMotorPower(dump, 0.0f);
        }

        if (gamepad2.right_bumper) {
            setMotorPower(slide, 0.5f);
        } else if (gamepad2.left_bumper) {
            setMotorPower(slide, -0.5f);
        } else {
            setMotorPower(slide, 0.0f);
        }

        if (gamepad2.right_stick_button) {
            if (lid_timer == 0.0f) {
                lid_timer = 1.0f;
                if (servoLid_Position() > 0.99f) {
                    setLidPosition(1.0f);
                } else {
                    setLidPosition(0.0f);
                }
            }
        }

        updateTelemetry();
    }
}