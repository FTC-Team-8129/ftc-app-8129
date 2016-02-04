package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleVariables extends TuleMotors {

    public TuleVariables() {

    }

    final double ENCODER_PULSES = 28;

    final double DRIVE_MOTOR = 60;
    final double DRIVE_GEAR_RATIO = 1;
    final double DRIVE_DIM_WHEEL = 3;
    final double DRIVE_DIM_TURN = 15;
    final double DRIVE_CPI = (ENCODER_PULSES * DRIVE_MOTOR
            * DRIVE_GEAR_RATIO) / (Math.PI * DRIVE_DIM_WHEEL);
    final double DRIVE_CPD =
            DRIVE_CPI * DRIVE_DIM_TURN / 360;

    final double ARM_MOTOR = 60;
    final double ARM_GEAR_RATIO = 1;
    final double ARM_DIM_RACKANDPINION = 2.5;
    final double ARM_CPI = (ENCODER_PULSES * ARM_MOTOR
            * ARM_GEAR_RATIO) / ARM_DIM_RACKANDPINION;

    final double SCOOP_MOTOR = 40;
    final double SCOOP_GEAR_RATIO = 2;
    final double SCOOP_CPD = (ENCODER_PULSES * SCOOP_MOTOR
            * SCOOP_GEAR_RATIO) / 360;

    boolean controller = false;
    boolean reverseControl = false;
    boolean setScoop = false;

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