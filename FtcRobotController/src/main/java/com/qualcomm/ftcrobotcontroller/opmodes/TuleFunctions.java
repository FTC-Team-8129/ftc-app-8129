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
        setMotorPower(drive_left, leftPower);
        setMotorPower(drive_right, rightPower);
    }

    void setArmPower(double power) {

        runWithEncoder(arm_right);
        runWithEncoder(arm_left);

        last_armTime = current_armTime;
        current_armTime = getRuntime();
        last_armRightCount = current_armRightCount;
        current_armRightCount = motorPosition(arm_right);
        last_armLeftCount = current_armLeftCount;
        current_armLeftCount = motorPosition(arm_left);

        last_arm_E = current_arm_E;
        if (power >= 0) {
            current_arm_E = current_armLeftCount - current_armRightCount;
        } else {
            current_arm_E = -(current_armLeftCount - current_armRightCount);
        }

        arm_P = arm_KP * current_arm_E;
        arm_I = arm_KI * (arm_I + (((current_arm_E + last_arm_E) / 2)
                * (current_armTime - last_armTime)));
        arm_D = arm_KD * (current_arm_E - last_arm_E)
                / (current_armTime - last_armTime);
        arm_PID = arm_P + arm_I + arm_D;
        
        arm_left_v4 = arm_left_v3;
        arm_left_v3 = arm_left_v2;
        arm_left_v2 = arm_left_v1;
        arm_left_v1 = arm_left_v0;
        arm_left_v0 = Math.abs((current_armLeftCount - last_armLeftCount)
                / (current_armTime - last_armTime));
        arm_left_v = (arm_left_v0 + arm_left_v1 + arm_left_v2
                + arm_left_v3 + arm_left_v4) / 5;
        
        arm_right_v4 = arm_right_v3;
        arm_right_v3 = arm_right_v2;
        arm_right_v2 = arm_right_v1;
        arm_right_v1 = arm_right_v0;
        arm_right_v0 = Math.abs((current_armRightCount - last_armRightCount)
                / (current_armTime - last_armTime));
        arm_right_v = (arm_right_v0 + arm_right_v1 + arm_right_v2
                + arm_right_v3 + arm_right_v4) / 5;

        if (Math.abs(current_arm_E) > max_error) {
            max_error = Math.abs(current_arm_E);
        }
        
        if (power > 0 && arm_PID > 0 && arm_left_v > arm_right_v) {
            armRightScale_p = armRightScale_p + Math.abs(arm_PID);
        } else if (power < 0 && arm_PID > 0 && arm_left_v > arm_right_v) {
            armRightScale_n = armRightScale_n + Math.abs(arm_PID);
        }

        if (power > 0 && arm_PID < 0 && arm_right_v > arm_left_v) {
            armLeftScale_p = armLeftScale_p + Math.abs(arm_PID);
        } else if (power < 0 && arm_PID < 0 && arm_right_v > arm_left_v) {
            armLeftScale_n = armLeftScale_n + Math.abs(arm_PID);
        }

        if (power > 0) {
            armLeftScale = armLeftScale_p;
            armRightScale = armRightScale_p;
        } else if (power < 0) {
            armLeftScale = armLeftScale_n;
            armRightScale = armRightScale_n;
        }

        if (armLeftScale > armRightScale) {
            armRightPower = power * armRightScale/armLeftScale;
            armLeftPower = power;
        } else if (armRightScale > armLeftScale) {
            armLeftPower = power * armLeftScale/armRightScale;
            armRightPower = power;
        } else {
            armRightPower = power;
            armLeftPower = power;
        }

        if (armLeftPower >= 1.0f) {
            armLeftPower = 1.0f;
        } else if (armLeftPower <= -1.0f) {
            armLeftPower = -1.0f;
        }

        if (armRightPower >= 1.0f) {
            armRightPower = 1.0f;
        } else if (armRightPower <= -1.0f) {
            armRightPower = -1.0f;
        }

        setMotorPower(arm_left, armLeftPower);
        setMotorPower(arm_right, armRightPower);
    }

    void setScoopPosition(double position, double power) {
        runWithEncoder(scoop);
        position = position * COUNTS_PER_DEGREE_SCOOP;
        setScoop = true;
        if (power > 0) {
            if (motorPosition(scoop) < position) {
                setMotorPower(scoop, power);
            } else if (motorPosition(scoop) >= position) {
                setMotorPower(scoop, 0.0f);
                setScoop = false;
            }
        } else {
            if (motorPosition(scoop) > position) {
                setMotorPower(scoop, power);
            } else if (motorPosition(scoop) <= position) {
                setMotorPower(scoop, 0.0f);
                setScoop = false;
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