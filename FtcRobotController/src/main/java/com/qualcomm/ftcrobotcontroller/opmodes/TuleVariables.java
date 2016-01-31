package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleVariables extends TuleMotors {

    public TuleVariables() {

    }

    final double CPR_N40 = 1120;

    final double GEAR_RATIO_DRIVE = 1;
    final double WHEEL_DIAMETER = 3;
    final double TURN_DIAMETER = 15;
    final double COUNTS_PER_INCH_DRIVE =
            CPR_N40 * GEAR_RATIO_DRIVE / (WHEEL_DIAMETER * Math.PI);
    final double COUNTS_PER_DEGREE_DRIVE =
            (COUNTS_PER_INCH_DRIVE * Math.PI * TURN_DIAMETER) / 360;

    final double SCOOP_GEAR_RATIO = 2;
    final double COUNTS_PER_DEGREE_SCOOP =
            (CPR_N40 * SCOOP_GEAR_RATIO) / 360;

    boolean controller = false;
    boolean reverseControl = false;
    boolean setScoop = false;
    boolean setSlide = false;

    double drive_time_last = 0.0f;
    double drive_time_current = 0.0f;
    double drive_error_last = 0.0f;
    double drive_error_current = 0.0f;
    double drive_dt = 0.0f;
    double drive_scale = 5f;
    double drive_left_count_last = 0.0f;
    double drive_left_count_current = 0.0f;
    double drive_left_dx_p = 0.0f;
    double drive_left_dx_n = 0.0f;
    double drive_left_v = 0.0f;
    double drive_left_v_p = 0.0f;
    double drive_left_v_n = 0.0f;
    double drive_left_power_p = 0.0f;
    double drive_left_power_n = 0.0f;
    double drive_left_ratio = 0.0f;
    double drive_left_ratio_p = 0.0f;
    double drive_left_ratio_n = 0.0f;
    double drive_left_scale = 100.0f;
    double drive_left_scale_p = 100.0f;
    double drive_left_scale_n = 100.0f;
    double drive_right_count_last = 0.0f;
    double drive_right_count_current = 0.0f;
    double drive_right_dx_p = 0.0f;
    double drive_right_dx_n = 0.0f;
    double drive_right_v = 0.0f;
    double drive_right_v_p = 0.0f;
    double drive_right_v_n = 0.0f;
    double drive_right_power_p = 0.0f;
    double drive_right_power_n = 0.0f;
    double drive_right_ratio = 0.0f;
    double drive_right_ratio_p = 0.0f;
    double drive_right_ratio_n = 0.0f;
    double drive_right_scale = 100.0f;
    double drive_right_scale_p = 100.0f;
    double drive_right_scale_n = 100.0f;

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

    double lid_timer = 0.0f;
    double scoop_timer = 0.0f;

    int state = 0;
    int scoop_position = 0;
}