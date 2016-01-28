package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleController extends TuleControlFunctions {

    boolean reverseControl = false;
    double lid_timer = 0.0f;
    double scoop_timer = 0.0f;

    public TuleController() {

    }

    @Override
    public void start() {
		controlInit();
    }

    @Override
    public void loop() {

        controller = true;

        lid_timer = lid_timer - 0.05f;
        if (lid_timer < 0.0f) {
            lid_timer = 0.0f;
        }

        scoop_timer = scoop_timer - 0.05f;
        if (scoop_timer < 0.0f) {
            scoop_timer = 0.0f;
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
            setArmPower(0.8f);
        } else if (-gamepad2.right_stick_y < -0.2f) {
            setArmPower(-0.8f);
        } else {
            setArmPower(0.0f);
        }

        if (Math.abs(gamepad2.left_stick_y) > 0.2) {
            setScoop = false;
            setMotorPower(scoop, -gamepad2.left_stick_y);
        } else if ((gamepad2.left_stick_button && scoop_timer == 0.0f) || setScoop) {
            if (scoop_timer == 0.0f || setScoop) {
                if (!setScoop) {
                    scoop_timer = 1.0f;
                }
                if (scoop_position == 0 || motorPosition(scoop) > 10f) {
                    setScoopPosition(220f, 0.8f);
                } else {
                    setScoopPosition(0f, -0.8f);
                }
            }
        } else {
            setMotorPower(scoop, 0.0f);
        }

        if (gamepad2.dpad_up) {
            setMotorPower(pivot, 0.8f);
        } else if (gamepad2.dpad_down) {
            setMotorPower(pivot, -0.8f);
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
                if (servoPosition(lid) > 0.99f) {
                    setServo(lid, 0.0f);
                } else {
                    setServo(lid, 1.0f);
                }
            }
        }

        if (navX_value("pitch") > 30) {
            setBallastPositions("up", 3.0f);
        } else {
            setBallastPositions("down", 3.0f);
        }

        updateTelemetry();
    }
}