package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class TuleSuperController extends OpMode {

    public TuleSuperController() {

    }

    @Override
    public void init() {

        try {
            drive_left = hardwareMap.dcMotor.get("drive left");
        } catch (Exception exception) {
            warning_addMessage("Left Drive Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            drive_left = null;
        }

        try {
            drive_right = hardwareMap.dcMotor.get("drive right");
            drive_right.setDirection(DcMotor.Direction.REVERSE);
        } catch (Exception exception) {
            warning_addMessage("Right Drive Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            drive_right = null;
        }

        try {
            arm_left = hardwareMap.dcMotor.get("arm left");
            arm_left.setDirection(DcMotor.Direction.REVERSE);
        } catch (Exception exception) {
            warning_addMessage("Left Arm Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            arm_left = null;
        }

        try {
            arm_right = hardwareMap.dcMotor.get("arm right");
        } catch (Exception exception) {
            warning_addMessage("Right Arm Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            arm_right = null;
        }

        try {
            dump = hardwareMap.dcMotor.get("dump");
            dump.setDirection(DcMotor.Direction.REVERSE);
        } catch (Exception exception) {
            warning_addMessage("Dump Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            dump = null;
        }

        try {
            pivot = hardwareMap.dcMotor.get("pivot");
        } catch (Exception exception) {
            warning_addMessage("Pivot Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            pivot = null;
        }

        try {
            scoop = hardwareMap.dcMotor.get("scoop");
            scoop.setDirection(DcMotor.Direction.REVERSE);
        } catch (Exception exception) {
            warning_addMessage("Scoop Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            scoop = null;
        }

        try {
            slide = hardwareMap.dcMotor.get("slide");
        } catch (Exception exception) {
            warning_addMessage("Slide Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            slide = null;
        }
    }

    @Override
    public void start() {
        resetStartTime();
        motor_killAll();
        motor_resetEncoder(drive_left);
        motor_resetEncoder(drive_right);
        motor_resetEncoder(arm_left);
        motor_resetEncoder(arm_right);
        motor_resetEncoder(scoop);
    }

    @Override
    public void loop() {

        motor_runWithEncoder(drive_left);
        motor_runWithEncoder(drive_right);
        motor_runWithEncoder(arm_left);
        motor_runWithEncoder(arm_right);
        motor_runWithEncoder(scoop);
        motor_runWithoutEncoder(dump);
        motor_runWithoutEncoder(pivot);
        motor_runWithoutEncoder(slide);

        lid_timer = lid_timer - 0.05f;
        if (lid_timer < 0.0f) {
            lid_timer = 0.0f;
        }

        scoop_timer = scoop_timer - 0.05f;
        if (scoop_timer < 0.0f) {
            scoop_timer = 0.0f;
        }

        if (gamepad1.a) {
            controller_reverse = true;
        } else if (gamepad1.b) {
            controller_reverse = false;
        }

        if (controller_reverse) {
            drive_setPower(gamepad1.right_stick_y, gamepad1.left_stick_y);
        } else {
            drive_setPower(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
        }

        if (-gamepad2.right_stick_y > 0.2f) {
            arm_setPower(0.8f);
        } else if (-gamepad2.right_stick_y < -0.2f) {
            arm_setPower(-0.8f);
        } else {
            arm_setPower(0.0f);
        }

        if (Math.abs(gamepad2.left_stick_y) > 0.1) {
            scoop_set = false;
            motor_setPower(scoop, -gamepad2.left_stick_y);
            if (motor_position(scoop) > 90 * SCOOP_CPD) {
                scoop_position = 1;
            } else if (motor_position(scoop) < 90 * SCOOP_CPD) {
                scoop_position = 0;
            }
        } else if ((gamepad2.left_stick_button && scoop_timer == 0.0f) || scoop_set) {
            if (scoop_timer == 0.0f || scoop_set) {
                if (!scoop_set) {
                    scoop_timer = 0.5f;
                }
                if (scoop_position == 0) {
                    scoop_setPosition(180f, 0.5f);
                } else {
                    scoop_setPosition(20f, -0.5f);
                }
            }
        } else {
            motor_setPower(scoop, 0.0f);
        }

        if (gamepad2.dpad_up) {
            motor_setPower(pivot, 0.8f);
        } else if (gamepad2.dpad_down) {
            motor_setPower(pivot, -0.8f);
        } else {
            motor_setPower(pivot, 0.0f);
        }

        if (gamepad2.x) {
            motor_setPower(dump, 0.5f);
        } else if (gamepad2.b) {
            motor_setPower(dump, -0.5f);
        } else {
            motor_setPower(dump, 0.0f);
        }

        if (gamepad2.right_bumper) {
            motor_setPower(slide, -0.5f);
        } else if (gamepad2.left_bumper) {
            motor_setPower(slide, 0.5f);
        } else {
            motor_setPower(slide, 0.0f);
        }

        telemetry_update();
    }

    DcMotor drive_left;
    DcMotor drive_right;
    DcMotor arm_left;
    DcMotor arm_right;
    DcMotor dump;
    DcMotor pivot;
    DcMotor scoop;
    DcMotor slide;

    String warning_message = "Can't Map";

    final double MOTOR_ENCODER_PULSES = 28;
    final double SCOOP_MOTOR = 40;
    final double SCOOP_GEAR_RATIO = 2;
    final double SCOOP_CPR = (MOTOR_ENCODER_PULSES * SCOOP_MOTOR * SCOOP_GEAR_RATIO);
    final double SCOOP_CPD = SCOOP_CPR / 360;

    boolean warning = false;
    boolean controller_reverse = false;
    boolean scoop_set = false;

    int motor_position(DcMotor motor) {
        int position = 0;
        if (motor != null) {
            position = motor.getCurrentPosition();
        }
        return position;
    }
    int scoop_position = 0;
    int state = 0;

    double motor_power(DcMotor motor) {
        double power = 0.0f;
        if (motor != null) {
            power = motor.getPower();
        }
        return power;
    }
    double lid_timer = 0.0f;
    double scoop_timer = 0.0f;
    double drive_time_last = 0.0f;
    double drive_time_current = 0.0f;
    double drive_dt = 0.0f;
    double drive_left_count_last = 0.0f;
    double drive_left_count_current = 0.0f;
    double drive_left_dx_p = 0.0f;
    double drive_left_dx_n = 0.0f;
    double drive_left_v = 0.0f;
    double drive_left_v_p = 0.0f;
    double drive_left_v_n = 0.0f;
    double drive_left_power_p = 0.0f;
    double drive_left_power_n = 0.0f;
    double drive_left_scale = 100.0f;
    double drive_right_count_last = 0.0f;
    double drive_right_count_current = 0.0f;
    double drive_right_dx_p = 0.0f;
    double drive_right_dx_n = 0.0f;
    double drive_right_v = 0.0f;
    double drive_right_v_p = 0.0f;
    double drive_right_v_n = 0.0f;
    double drive_right_power_p = 0.0f;
    double drive_right_power_n = 0.0f;
    double drive_right_scale = 100.0f;
    double arm_time_last = 0.0f;
    double arm_time_current = 0.0f;
    double arm_error_last = 0.0f;
    double arm_error_current = 0.0f;
    double arm_error_max = 0.0f;
    double arm_KP = 25.0f;
    double arm_KI = 1.0f;
    double arm_KD = 50.0f;
    double arm_P = 0.0f;
    double arm_I = 0.0f;
    double arm_D = 0.0f;
    double arm_PID = 0.0f;
    double arm_left_power = 0.0f;
    double arm_left_count_last = 0.0f;
    double arm_left_count_current = 0.0f;
    double arm_left_v = 0.0f;
    double arm_left_v0 = 0.0f;
    double arm_left_v1 = 0.0f;
    double arm_left_v2 = 0.0f;
    double arm_left_v3 = 0.0f;
    double arm_left_v4 = 0.0f;
    double arm_left_scale = 100.0f;
    double arm_left_scale_p = 100.0f;
    double arm_left_scale_n = 100.0f;
    double arm_right_power = 0.0f;
    double arm_right_count_last = 0.0f;
    double arm_right_count_current = 0.0f;
    double arm_right_v = 0.0f;
    double arm_right_v0 = 0.0f;
    double arm_right_v1 = 0.0f;
    double arm_right_v2 = 0.0f;
    double arm_right_v3 = 0.0f;
    double arm_right_v4 = 0.0f;
    double arm_right_scale = 100.0f;
    double arm_right_scale_p = 100.0f;
    double arm_right_scale_n = 100.0f;

    void warning_addMessage(String error) {
        if (warning) {
            warning_message += ", ";
        }
        warning = true;
        warning_message += error;
    }
    void motor_setPower(DcMotor motor, double power) {
        if (motor != null) {
            motor.setPower(power);
        }
    }
    void motor_resetEncoder(DcMotor motor) {
        if (motor != null) {
            motor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }
    void motor_runWithEncoder(DcMotor motor) {
        if (motor != null) {
            motor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    }
    void motor_runWithoutEncoder(DcMotor motor) {
        if (motor != null) {
            motor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    }
    void motor_killAll() {
        motor_setPower(drive_left, 0.0f);
        motor_setPower(drive_right, 0.0f);
        motor_setPower(arm_left, 0.0f);
        motor_setPower(arm_right, 0.0f);
        motor_setPower(dump, 0.0f);
        motor_setPower(pivot, 0.0f);
        motor_setPower(slide, 0.0f);
        motor_setPower(scoop, 0.0f);
    }
    void drive_setPower(double leftPower, double rightPower) {

        if (Math.abs(leftPower) < 0.2f) {
            leftPower = 0.0f;
        }
        if (Math.abs(rightPower) < 0.2f) {
            rightPower = 0.0f;
        }

        motor_runWithEncoder(drive_right);
        motor_runWithEncoder(drive_left);

        drive_time_last = drive_time_current;
        drive_time_current = getRuntime();
        drive_right_count_last = drive_right_count_current;
        drive_right_count_current = motor_position(drive_right);
        drive_left_count_last = drive_right_count_current;
        drive_right_count_current = motor_position(drive_left);
        drive_dt = drive_time_current - drive_time_last;

        if (leftPower > 0) {
            drive_left_dx_p = drive_left_count_current - drive_left_count_last;
            drive_left_v_p = 0.9f * drive_left_v_p + 0.1f * drive_left_dx_p / drive_dt;
            drive_left_power_p = 0.9f * drive_left_power_p + 0.1f * leftPower;
            drive_left_v = Math.abs(drive_left_v_p);
        } else if (leftPower < 0) {
            drive_left_dx_n = drive_left_count_current - drive_left_count_last;
            drive_left_v_n = 0.9f * drive_left_v_n + 0.1f * drive_left_dx_n / drive_dt;
            drive_left_power_n = 0.9f * drive_left_power_n + 0.1f * leftPower;
            drive_left_v = Math.abs(drive_left_v_n);
        }

        if (rightPower > 0) {
            drive_right_dx_p = drive_right_count_current - drive_right_count_last;
            drive_right_v_p = 0.9f * drive_right_v_p + 0.1f * drive_right_dx_p / drive_dt;
            drive_right_power_p = 0.9f * drive_right_power_p + 0.1f * rightPower;
            drive_right_v = Math.abs(drive_right_v_p);
        } else if (rightPower < 0) {
            drive_right_dx_n = drive_right_count_current - drive_right_count_last;
            drive_right_v_n = 0.9f * drive_right_v_n + 0.1f * drive_right_dx_n / drive_dt;
            drive_right_power_n = 0.9f * drive_right_power_n + 0.1f * rightPower;
            drive_right_v = Math.abs(drive_right_v_n);
        }

        motor_setPower(drive_left, leftPower);
        motor_setPower(drive_right, rightPower);
    }
    void arm_setPower(double power) {

        motor_runWithEncoder(arm_right);
        motor_runWithEncoder(arm_left);

        arm_time_last = arm_time_current;
        arm_time_current = getRuntime();
        arm_right_count_last = arm_right_count_current;
        arm_right_count_current = motor_position(arm_right);
        arm_left_count_last = arm_left_count_current;
        arm_left_count_current = motor_position(arm_left);

        arm_error_last = arm_error_current;
        if (power >= 0) {
            arm_error_current = arm_left_count_current - arm_right_count_current;
        } else {
            arm_error_current = -(arm_left_count_current - arm_right_count_current);
        }

        arm_P = arm_KP * arm_error_current;
        arm_I = arm_KI * (arm_I + (((arm_error_current + arm_error_last) / 2)
                * (arm_time_current - arm_time_last)));
        arm_D = arm_KD * (arm_error_current - arm_error_last)
                / (arm_time_current - arm_time_last);
        arm_PID = arm_P + arm_I + arm_D;

        arm_left_v4 = arm_left_v3;
        arm_left_v3 = arm_left_v2;
        arm_left_v2 = arm_left_v1;
        arm_left_v1 = arm_left_v0;
        arm_left_v0 = Math.abs((arm_left_count_current - arm_left_count_last)
                / (arm_time_current - arm_time_last));
        arm_left_v = (arm_left_v0 + arm_left_v1 + arm_left_v2
                + arm_left_v3 + arm_left_v4) / 5;

        arm_right_v4 = arm_right_v3;
        arm_right_v3 = arm_right_v2;
        arm_right_v2 = arm_right_v1;
        arm_right_v1 = arm_right_v0;
        arm_right_v0 = Math.abs((arm_right_count_current - arm_right_count_last)
                / (arm_time_current - arm_time_last));
        arm_right_v = (arm_right_v0 + arm_right_v1 + arm_right_v2
                + arm_right_v3 + arm_right_v4) / 5;

        if (Math.abs(arm_error_current) > arm_error_max) {
            arm_error_max = Math.abs(arm_error_current);
        }

        if (power > 0 && arm_PID > 0 && arm_left_v > arm_right_v) {
            arm_right_scale_p = arm_right_scale_p + Math.abs(arm_PID);
        } else if (power < 0 && arm_PID > 0 && arm_left_v > arm_right_v) {
            arm_right_scale_n = arm_right_scale_n + Math.abs(arm_PID);
        }

        if (power > 0 && arm_PID < 0 && arm_right_v > arm_left_v) {
            arm_left_scale_p = arm_left_scale_p + Math.abs(arm_PID);
        } else if (power < 0 && arm_PID < 0 && arm_right_v > arm_left_v) {
            arm_left_scale_n = arm_left_scale_n + Math.abs(arm_PID);
        }

        if (power > 0) {
            arm_left_scale = arm_left_scale_p;
            arm_right_scale = arm_right_scale_p;
        } else if (power < 0) {
            arm_left_scale = arm_left_scale_n;
            arm_right_scale = arm_right_scale_n;
        }

        if (arm_left_scale > arm_right_scale) {
            arm_right_power = power * arm_right_scale/arm_left_scale;
            arm_left_power = power;
        } else if (arm_right_scale > arm_left_scale) {
            arm_left_power = power * arm_left_scale/arm_right_scale;
            arm_right_power = power;
        } else {
            arm_right_power = power;
            arm_left_power = power;
        }

        if (arm_left_power >= 1.0f) {
            arm_left_power = 1.0f;
        } else if (arm_left_power <= -1.0f) {
            arm_left_power = -1.0f;
        }

        if (arm_right_power >= 1.0f) {
            arm_right_power = 1.0f;
        } else if (arm_right_power <= -1.0f) {
            arm_right_power = -1.0f;
        }

        motor_setPower(arm_left, arm_left_power);
        motor_setPower(arm_right, arm_right_power);
    }
    void scoop_setPosition(double position, double power) {
        motor_runWithEncoder(scoop);
        position = position * SCOOP_CPD;
        scoop_set = true;
        if (power > 0) {
            if (motor_position(scoop) < position) {
                motor_setPower(scoop, power);
            } else {
                motor_setPower(scoop, 0.0f);
                scoop_set = false;
                scoop_position = 1;
            }
        } else {
            if (motor_position(scoop) > position) {
                motor_setPower(scoop, power);
            } else {
                motor_setPower(scoop, 0.0f);
                scoop_set = false;
                scoop_position = 0;
            }
        }
    }
    void telemetry_update() {
        if (warning) {
            telemetry.addData("0.0", warning_message);
        } else {
            telemetry.addData("0.0", "No Errors");
        }
        telemetry.addData("0.1", "Running for "
                + (Math.round(1000*getRuntime())/1000) + " seconds");

        telemetry.addData("1.0",
                "Left Drive: "
                        + (int)(motor_power(drive_left)*100) + "% Power, "
                        + (int)(drive_left_v) + " Counts/s, "
                        + Math.round(drive_left_scale*100)/100);
        telemetry.addData("1.1",
                "Right Drive: "
                        + (int)(motor_power(drive_right)*100) + "% Power, "
                        + (int)(drive_right_v) + " Counts/s, "
                        + Math.round(drive_right_scale*100)/100);
        telemetry.addData("1.2",
                "Left Arm: "
                        + (int) (motor_power(arm_left) * 100) + "% Power, "
                        + arm_left_count_current + "Counts, "
                        + (int) (arm_left_v) + " Counts/s, "
                        + Math.round(arm_left_scale * 100) / 100);
        telemetry.addData("1.3",
                "Right Arm: "
                        + (int)(motor_power(arm_right)*100) + "% Power, "
                        + arm_right_count_current + "Counts, "
                        + (int)(arm_right_v) + " Counts/s, "
                        + Math.round(arm_right_scale*100)/100);

        telemetry.addData("4.0","Max Arm Error: " + (int)(arm_error_max) + " Counts");
    }
}