package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleVariables extends TuleNavX {
	
	public TuleVariables() {
		
	}

	final double COUNTS_PER_REVOLUTION_N40 = 1120;
    final double COUNTS_PER_REVOLUTION_N60 = 1680;

    final double GEAR_RATIO_DRIVE = 1;
    final double WHEEL_DIAMETER = 3;
    final double TURN_DIAMETER = 18;
    final double COUNTS_PER_INCH_DRIVE =
            COUNTS_PER_REVOLUTION_N40*GEAR_RATIO_DRIVE/(WHEEL_DIAMETER*Math.PI);
    final double COUNTS_PER_DEGREE_DRIVE =
            (COUNTS_PER_INCH_DRIVE*Math.PI*TURN_DIAMETER)/360;

    final double GEAR_RATIO_SCOOP = 2;
    final double COUNTS_PER_DEGREE_SCOOP =
            (COUNTS_PER_REVOLUTION_N40*GEAR_RATIO_SCOOP)/360;

    boolean setScoop = false;
    boolean setBallasts = false;
    int scoop_position = 0;

	double last_driveRightCount = 0.0f;
    double current_driveRightCount = 0.0f;
    double last_driveLeftCount = 0.0f;
    double current_driveLeftCount = 0.0f;
    double last_driveTime = 0.0f;
    double current_driveTime = 0.0f;
    double driveLeftScale = 1.0f;
    double driveRightScale = 1.0f;

    double last_armRightCount = 0.0f;
    double current_armRightCount = 0.0f;
    double last_armLeftCount = 0.0f;
    double current_armLeftCount = 0.0f;
    double last_armTime = 0.0f;
    double current_armTime = 0.0f;
    double armLeftPower;
    double armRightPower;
    double armLeftScale = 100.0f;
    double armRightScale = 100.0f;
    double armLeftScale_p = 100.0f;
    double armRightScale_p = 100.0f;
    double armLeftScale_n = 100.0f;
    double armRightScale_n = 100.0f;
    double current_arm_E = 0.0f;
    double last_arm_E = 0.0f;
    double arm_P = 0.0f;
    double arm_I = 0.0f;
    double arm_D = 0.0f;
    double arm_PID = 0.0f;

    double max_error = 0.0f;

    final double arm_KP = 25.0f;
    final double arm_KI = 1.0f;
    final double arm_KD = 50.0f;
    
    double arm_left_v = 0.0f;
    double arm_right_v = 0.0f;
    
    double arm_left_v0 = 0.0f;
    double arm_left_v1 = 0.0f;
    double arm_left_v2 = 0.0f;
    double arm_left_v3 = 0.0f;
    double arm_left_v4 = 0.0f;

    double arm_right_v0 = 0.0f;
    double arm_right_v1 = 0.0f;
    double arm_right_v2 = 0.0f;
    double arm_right_v3 = 0.0f;
    double arm_right_v4 = 0.0f;

    double ballast_startTime = 0.0f;
    double ballast_goalTime = 0.0f;
    double ballast_currentTime = 0.0f;
    int ballastPosition = 0;

	int state = 0;
}