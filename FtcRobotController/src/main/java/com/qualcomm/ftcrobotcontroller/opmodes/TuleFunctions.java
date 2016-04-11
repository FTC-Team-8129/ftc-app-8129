package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleFunctions extends TuleVariables {

    public TuleFunctions() {

    }

    void baseInit() {
        motorKill();
        resetStartTime();
    }

    void runAllEncoders() {
        runWithEncoder(drive_left);
        runWithEncoder(drive_right);
        runWithEncoder(arm_left);
        runWithEncoder(arm_right);
        runWithEncoder(dump);
        runWithEncoder(pivot);
        runWithEncoder(scoop);
        runWithEncoder(slide);
    }

    void resetAllEncoders() {
        resetEncoder(drive_left);
        resetEncoder(drive_right);
        resetEncoder(arm_left);
        resetEncoder(arm_right);
        resetEncoder(dump);
        resetEncoder(pivot);
        resetEncoder(scoop);
        resetEncoder(slide);
    }

    void setDrivePower(double leftPower, double rightPower) {

        if (Math.abs(leftPower) < 0.2f) {
            leftPower = 0.0f;
        }
        if (Math.abs(rightPower) < 0.2f) {
            rightPower = 0.0f;
        }

//        runWithEncoder(drive_right);
//        runWithEncoder(drive_left);
//
//        drive_time_last = drive_time_current;
//        drive_time_current = getRuntime();
//        drive_right_count_last = drive_right_count_current;
//        drive_right_count_current = motorPosition(drive_right);
//        drive_left_count_last = drive_right_count_current;
//        drive_right_count_current = motorPosition(drive_left);
//
//        drive_dt = drive_time_current - drive_time_last;
//
//        if (leftPower > 0) {
//            drive_left_dx_p = drive_left_count_current - drive_left_count_last;
//            drive_left_v_p = 0.9f * drive_left_v_p + 0.1f * drive_left_dx_p / drive_dt;
//            drive_left_power_p = 0.9f * drive_left_power_p + 0.1f * leftPower;
//            drive_left_ratio_p = drive_left_v_p / drive_left_power_p;
//            drive_left_v = Math.abs(drive_left_v_p);
//            drive_left_ratio = Math.abs(drive_left_ratio_p);
//        } else if (leftPower < 0) {
//            drive_left_dx_n = drive_left_count_current - drive_left_count_last;
//            drive_left_v_n = 0.9f * drive_left_v_n + 0.1f * drive_left_dx_n / drive_dt;
//            drive_left_power_n = 0.9f * drive_left_power_n + 0.1f * leftPower;
//            drive_left_ratio_n = drive_left_v_n / drive_left_power_n;
//            drive_left_v = Math.abs(drive_left_v_n);
//            drive_left_ratio = Math.abs(drive_left_ratio_n);
//        }
//
//        if (rightPower > 0) {
//            drive_right_dx_p = drive_right_count_current - drive_right_count_last;
//            drive_right_v_p = 0.9f * drive_right_v_p + 0.1f * drive_right_dx_p / drive_dt;
//            drive_right_power_p = 0.9f * drive_right_power_p + 0.1f * rightPower;
//            drive_right_ratio_p = drive_right_v_p / drive_right_power_p;
//            drive_right_v = Math.abs(drive_right_v_p);
//            drive_right_ratio = Math.abs(drive_right_ratio_p);
//        } else if (rightPower < 0) {
//            drive_right_dx_n = drive_right_count_current - drive_right_count_last;
//            drive_right_v_n = 0.9f * drive_right_v_n + 0.1f * drive_right_dx_n / drive_dt;
//            drive_right_power_n = 0.9f * drive_right_power_n + 0.1f * rightPower;
//            drive_right_ratio_n = drive_right_v_n / drive_right_power_n;
//            drive_right_v = Math.abs(drive_right_v_n);
//            drive_right_ratio = Math.abs(drive_right_ratio_n);
//        }
//
//        if (!controller) {
//
//            drive_error_last = drive_error_current;
//            drive_error_current = drive_left_ratio - drive_right_ratio;
//
//            if (drive_error_current > 0.0f) {
//                if (leftPower > 0) {
//                    drive_left_scale_p = drive_left_scale_p - drive_scale;
//                    drive_left_scale = drive_left_scale_p;
//                } else if (leftPower < 0.0f) {
//                    drive_left_scale_n = drive_left_scale_n - drive_scale;
//                    drive_left_scale = drive_left_scale_n;
//                }
//                if (rightPower > 0.0f) {
//                    drive_right_scale_p = drive_right_scale_p + drive_scale;
//                    drive_right_scale = drive_right_scale_p;
//                } else if (rightPower < 0.0f) {
//                    drive_right_scale_n = drive_right_scale_n + drive_scale;
//                    drive_right_scale = drive_right_scale_n;
//                }
//            }
//
//            if (drive_error_current < 0.0f) {
//                if (leftPower > 0) {
//                    drive_left_scale_p = drive_left_scale_p + drive_scale;
//                    drive_left_scale = drive_left_scale_p;
//                } else if (leftPower < 0.0f) {
//                    drive_left_scale_n = drive_left_scale_n + drive_scale;
//                    drive_left_scale = drive_left_scale_n;
//                }
//                if (rightPower > 0.0f) {
//                    drive_right_scale_p = drive_right_scale_p - drive_scale;
//                    drive_right_scale = drive_right_scale_p;
//                } else if (rightPower < 0.0f) {
//                    drive_right_scale_n = drive_right_scale_n - drive_scale;
//                    drive_right_scale = drive_right_scale_n;
//                }
//            }
//
//            if (drive_right_scale > drive_left_scale) {
//                leftPower = leftPower * drive_left_scale / drive_right_scale;
//            } else if (drive_right_scale < drive_left_scale) {
//                rightPower = rightPower * drive_right_scale / drive_left_scale;
//            }
//
//            if (leftPower >= 1.0f) {
//                leftPower = 1.0f;
//            } else if (leftPower <= -1.0f) {
//                leftPower = -1.0f;
//            }
//
//            if (rightPower >= 1.0f) {
//                rightPower = 1.0f;
//            } else if (rightPower <= -1.0f) {
//                rightPower = -1.0f;
//            }
//        }

        setMotorPower(drive_left, leftPower);
        setMotorPower(drive_right, rightPower);
    }

    void setArmPower(double power) {

        runWithEncoder(arm_right);
        runWithEncoder(arm_left);

        arm_time_last = arm_time_current;
        arm_time_current = getRuntime();
        arm_right_count_last = arm_right_count_current;
        arm_right_count_current = motorPosition(arm_right);
        arm_left_count_last = arm_left_count_current;
        arm_left_count_current = motorPosition(arm_left);

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

        setMotorPower(arm_left, arm_left_power);
        setMotorPower(arm_right, arm_right_power);
    }

    void setScoopPosition(double position, double power) {
        runWithEncoder(scoop);
        position = position * SCOOP_CPD;
        setScoop = true;
        if (power > 0) {
            if (motorPosition(scoop) < position) {
                setMotorPower(scoop, power);
            } else {
                setMotorPower(scoop, 0.0f);
                setScoop = false;
                scoop_position = 1;
            }
        } else {
            if (motorPosition(scoop) > position) {
                setMotorPower(scoop, power);
            } else {
                setMotorPower(scoop, 0.0f);
                setScoop = false;
                scoop_position = 0;
            }
        }
    }

    void motorKill() {
        setMotorPower(drive_left, 0.0f);
        setMotorPower(drive_right, 0.0f);
        setMotorPower(arm_left, 0.0f);
        setMotorPower(arm_right, 0.0f);
        setMotorPower(dump, 0.0f);
        setMotorPower(pivot, 0.0f);
        setMotorPower(slide, 0.0f);
        setMotorPower(scoop, 0.0f);
    }
}