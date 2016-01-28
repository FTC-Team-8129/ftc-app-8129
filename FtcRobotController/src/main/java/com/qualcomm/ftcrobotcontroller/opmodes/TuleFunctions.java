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

        runWithEncoder(drive_right);
        runWithEncoder(drive_left);

        last_driveTime = current_driveTime;
        current_driveTime = getRuntime();
        last_driveRightCount = current_driveRightCount;
        current_driveRightCount = motorPosition(drive_right);
        last_driveLeftCount = current_driveLeftCount;
        current_driveLeftCount = motorPosition(drive_left);

        drive_dt = current_driveTime - last_driveTime;

        if (leftPower > 0) {
            p_drive_left_dx = current_driveLeftCount - last_driveLeftCount;
            p_drive_left_v = 0.9f * p_drive_left_v + 0.1f * p_drive_left_dx / drive_dt;
            p_drive_left_p = 0.9f * p_drive_left_p + 0.1f * leftPower;
            p_drive_left_ratio = p_drive_left_v / p_drive_left_p;
            drive_left_v = Math.abs(p_drive_left_v);
            drive_left_ratio = Math.abs(p_drive_left_ratio);
        } else if (leftPower < 0) {
            n_drive_left_dx = current_driveLeftCount - last_driveLeftCount;
            n_drive_left_v = 0.9f * n_drive_left_v + 0.1f * n_drive_left_dx / drive_dt;
            n_drive_left_p = 0.9f * n_drive_left_p + 0.1f * leftPower;
            n_drive_left_ratio = n_drive_left_v / n_drive_left_p;
            drive_left_v = Math.abs(n_drive_left_v);
            drive_left_ratio = Math.abs(n_drive_left_ratio);
        }

        if (rightPower > 0) {
            p_drive_right_dx = current_driveRightCount - last_driveRightCount;
            p_drive_right_v = 0.9f * p_drive_right_v + 0.1f * p_drive_right_dx / drive_dt;
            p_drive_right_p = 0.9f * p_drive_right_p + 0.1f * rightPower;
            p_drive_right_ratio = p_drive_right_v / p_drive_right_p;
            drive_right_v = Math.abs(p_drive_right_v);
            drive_right_ratio = Math.abs(p_drive_right_ratio);
        } else if (rightPower < 0) {
            n_drive_right_dx = current_driveRightCount - last_driveRightCount;
            n_drive_right_v = 0.9f * n_drive_right_v + 0.1f * n_drive_right_dx / drive_dt;
            n_drive_right_p = 0.9f * n_drive_right_p + 0.1f * rightPower;
            n_drive_right_ratio = n_drive_right_v / n_drive_right_p;
            drive_right_v = Math.abs(n_drive_right_v);
            drive_right_ratio = Math.abs(n_drive_right_ratio);
        }

        if (!controller) {

            last_drive_E = current_drive_E;
            current_drive_E = drive_left_ratio - drive_right_ratio;

            if (current_drive_E > 0.0f) {
                if (leftPower > 0) {
                    p_drive_left_scale = p_drive_left_scale - drive_scale;
                    drive_left_scale = p_drive_left_scale;
                } else if (leftPower < 0.0f) {
                    n_drive_left_scale = n_drive_left_scale - drive_scale;
                    drive_left_scale = n_drive_left_scale;
                }
                if (rightPower > 0.0f) {
                    p_drive_right_scale = p_drive_right_scale + drive_scale;
                    drive_right_scale = p_drive_right_scale;
                } else if (rightPower < 0.0f) {
                    n_drive_right_scale = n_drive_right_scale + drive_scale;
                    drive_right_scale = n_drive_right_scale;
                }
            }

            if (current_drive_E < 0.0f) {
                if (leftPower > 0) {
                    p_drive_left_scale = p_drive_left_scale + drive_scale;
                    drive_left_scale = p_drive_left_scale;
                } else if (leftPower < 0.0f) {
                    n_drive_left_scale = n_drive_left_scale + drive_scale;
                    drive_left_scale = n_drive_left_scale;
                }
                if (rightPower > 0.0f) {
                    p_drive_right_scale = p_drive_right_scale - drive_scale;
                    drive_right_scale = p_drive_right_scale;
                } else if (rightPower < 0.0f) {
                    n_drive_right_scale = n_drive_right_scale - drive_scale;
                    drive_right_scale = n_drive_right_scale;
                }
            }

            if (drive_right_scale > drive_left_scale) {
                leftPower = leftPower * drive_left_scale / drive_right_scale;
            } else if (drive_right_scale < drive_left_scale) {
                rightPower = rightPower * drive_right_scale / drive_left_scale;
            }

            if (leftPower >= 1.0f) {
                leftPower = 1.0f;
            } else if (leftPower <= -1.0f) {
                leftPower = -1.0f;
            }

            if (rightPower >= 1.0f) {
                rightPower = 1.0f;
            } else if (rightPower <= -1.0f) {
                rightPower = -1.0f;
            }

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

    void setScoopPosition(double position, double power) {
        runWithEncoder(scoop);
        position = position * COUNTS_PER_DEGREE_SCOOP;
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

    void setBallastPositions(String direction, double time) {
        ballast_currentTime = getRuntime();

        if (direction.equals("up") && ballastPosition == 0) {
            if (!setBallasts) {
                ballast_startTime = ballast_currentTime;
                setBallasts = true;
            }
            ballast_goalTime = ballast_startTime + time;
            setServo(leftBallast, 1.0f);
            setServo(rightBallast, 1.0f);
        } else if (direction.equals("down") && ballastPosition == 1) {
            if (!setBallasts) {
                ballast_startTime = ballast_currentTime;
                setBallasts = true;
            }
            ballast_goalTime = ballast_startTime + time;
            setServo(leftBallast, -1.0f);
            setServo(rightBallast, -1.0f);
        } else {
            setServo(leftBallast, 0.0f);
            setServo(rightBallast, 0.0f);
        }

        if (ballast_currentTime >= ballast_goalTime && setBallasts) {
            setServo(leftBallast, 0.0f);
            setServo(rightBallast, 0.0f);
            setBallasts = false;
            if (ballastPosition == 0) {
                ballastPosition = 1;
            } else {
                ballastPosition = 0;
            }
        }
    }

    void setSlidePosition(double position, double power) {

        if (power > 0.0f) {
            if (motorPosition(slide) >= position) {
                setMotorPower(slide, 0.0f);
            }
        } else if (power < 0.0f) {
            if (motorPosition(slide) <= position) {
                setMotorPower(slide, 0.0f);
            }
        }
    }



    //  TODO Add functions to automatically control dump and pivot motors

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