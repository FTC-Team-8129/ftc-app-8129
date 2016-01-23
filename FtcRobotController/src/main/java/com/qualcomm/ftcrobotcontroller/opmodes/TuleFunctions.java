package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleFunctions extends TuleVariables {

    public TuleFunctions() {

    }

    void baseInit() {
        motorKill();
        resetStartTime();
        setClimberPosition(0.0f);
        setLeftLeverPosition(1.0f);
        setRightLeverPosition(0.0f);
        setLidPosition(0.0f);
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

        runWithEncoder(drive_right);
        runWithEncoder(drive_left);
        last_driveRightCount = current_driveRightCount;
        current_driveRightCount = motorPosition(drive_right);
        last_driveLeftCount = current_driveLeftCount;
        current_driveLeftCount = motorPosition(drive_left);
        last_driveTime = current_driveTime;
        current_driveTime = getRuntime();

        double left_Dx = current_driveLeftCount-last_driveLeftCount;
        double right_Dx = current_driveRightCount-last_driveRightCount;
        double Dt = current_driveTime-last_driveTime;

        if (Math.abs(leftPower) < 0.2) {
            leftPower = 0;
        }
        if (Math.abs(rightPower) < 0.2) {
            rightPower = 0;
        }

        double leftSpeed = left_Dx/Dt;
        double rightSpeed = right_Dx/Dt;
        double leftRatio = 0;
        double rightRatio = 0;

        if (Math.abs(leftPower) > 0) {
            leftRatio = Math.abs(leftSpeed / leftPower);
        }
        if (Math.abs(rightPower) > 0) {
            rightRatio = Math.abs(rightSpeed / rightPower);
        }

        if (leftRatio > rightRatio && rightRatio > 0) {
            last_driveLeft_E = current_driveLeft_E;
            current_driveLeft_E = leftRatio - rightRatio;
            driveLeft_Kp = current_driveLeft_E;
            driveLeft_Ki = driveLeft_Ki + ((current_driveLeft_E + last_driveLeft_E) / 2) * Dt;
            driveLeft_Kd = (current_driveLeft_E - last_driveLeft_E) / Dt;
            driveLeftScale = (leftRatio - (driveLeft_Kp + driveLeft_Ki + driveLeft_Kd))/leftRatio;
            if (driveLeftScale < -1.0f) {
                driveLeftScale = -1.0f;
            } else if (driveLeftScale > 1.0f) {
                driveLeftScale = 1.0f;
            }
            leftPower = leftPower * driveLeftScale;
        }

        if (rightRatio > leftRatio && leftRatio > 0) {
            last_driveRight_E = current_driveRight_E;
            current_driveRight_E = rightRatio - leftRatio;
            driveRight_Kp = current_driveRight_E;
            driveRight_Ki = driveRight_Ki + ((current_driveRight_E + last_driveRight_E) / 2) * Dt;
            driveRight_Kd = (current_driveRight_E - last_driveRight_E) / Dt;
            driveRightScale = (rightRatio - (driveRight_Kp + driveRight_Ki + driveRight_Kd))/rightRatio;
            if (driveRightScale < -1.0f) {
                driveRightScale = -1.0f;
            } else if (driveRightScale > 1.0f) {
                driveRightScale = 1.0f;
            }
            rightPower = rightPower * driveRightScale;
        }

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