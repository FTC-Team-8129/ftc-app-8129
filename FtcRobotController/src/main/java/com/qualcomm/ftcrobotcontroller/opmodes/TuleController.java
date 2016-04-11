package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleController extends TuleControlFunctions {

    public TuleController() {

    }

    @Override
    public void start() {
		controlInit();
    }

    @Override
    public void loop() {

//        controller = true;
//
//        lid_timer = lid_timer - 0.05f;
//        if (lid_timer < 0.0f) {
//            lid_timer = 0.0f;
//        }
//
//        scoop_timer = scoop_timer - 0.05f;
//        if (scoop_timer < 0.0f) {
//            scoop_timer = 0.0f;
//        }

        if (gamepad1.a) {
            reverseControl = true;
        } else if (gamepad1.b) {
            reverseControl = false;
        }

        runWithoutEncoder(drive_left);
        runWithoutEncoder(drive_right);

        if (reverseControl) {
            if (Math.abs(gamepad1.left_stick_y) > 0.2f){
                setMotorPower(drive_left, gamepad1.right_stick_y);
            } else {
                setMotorPower(drive_left, 0.0f);
            }
            if (Math.abs(gamepad1.right_stick_y) > 0.2f) {
                setMotorPower(drive_right, gamepad1.left_stick_y);
            } else {
                setMotorPower(drive_right, 0.0f);
            }
        } else {
            if (Math.abs(gamepad1.right_stick_y) > 0.2f) {
                setMotorPower(drive_right, -gamepad1.right_stick_y);
            } else {
                setMotorPower(drive_right, 0.0f);
            }
            if (Math.abs(gamepad1.left_stick_y) > 0.2f) {
                setMotorPower(drive_left, -gamepad1.left_stick_y);
            } else {
                setMotorPower(drive_left, 0.0f);
            }
        }

        runWithoutEncoder(arm);

        if (Math.abs(gamepad1.right_trigger) > 0.1f) {
            setMotorPower(arm, 1.0f);
        } else if(Math.abs(gamepad1.left_trigger) > 0.1f) {
            setMotorPower(arm, -1.0f);
        } else {
            setMotorPower(arm, 0.0f);
        }

//        runWithoutEncoder(scoop);
//
//        if (Math.abs(gamepad2.left_stick_y) > 0.1) {
//            setScoop = false;
//            runWithEncoder(scoop);
//            setMotorPower(scoop, -gamepad2.left_stick_y);
//            if (motorPosition(scoop) > 90 * SCOOP_CPD) {
//                scoop_position = 1;
//            } else if (motorPosition(scoop) < 90 * SCOOP_CPD) {
//                scoop_position = 0;
//            }
//        } else if ((gamepad2.left_stick_button && scoop_timer == 0.0f) || setScoop) {
//            if (scoop_timer == 0.0f || setScoop) {
//                if (!setScoop) {
//                    scoop_timer = 0.5f;
//                }
//                if (scoop_position == 0) {
//                    setScoopPosition(180f, 0.5f);
//                } else {
//                    setScoopPosition(20f, -0.5f);
//                }
//            }
//        } else {
//            setMotorPower(scoop, 0.0f);
//        }

        runWithoutEncoder(pivot);

        if (gamepad1.dpad_up) {
            setMotorPower(pivot, -1.0f);
        } else if (gamepad1.dpad_down) {
            setMotorPower(pivot, 1.0f);
        } else {
            setMotorPower(pivot, 0.0f);
        }

//        runWithoutEncoder(dump);
//
//        if (gamepad2.x) {
//            setMotorPower(dump, 0.5f);
//        } else if (gamepad2.b) {
//            setMotorPower(dump, -0.5f);
//        } else {
//            setMotorPower(dump, 0.0f);
//        }

        runWithoutEncoder(slide);

        if (gamepad1.right_bumper) {
            setMotorPower(slide, -0.5f);
        } else if (gamepad1.left_bumper) {
            setMotorPower(slide, 0.5f);
        } else {
            setMotorPower(slide, 0.0f);
        }

        updateTelemetry();
    }
}