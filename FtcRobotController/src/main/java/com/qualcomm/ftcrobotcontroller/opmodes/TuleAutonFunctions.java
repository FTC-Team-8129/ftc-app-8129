package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleAutonFunctions extends TuleTelemetry {
    public TuleAutonFunctions() {

    }

    void autonInit() {
        baseInit();
        resetAllEncoders();
    }

    void waitForReset() {
        if (driveEncodersReset()) {
            state++;
        }
    }

    void checkTime() {
        if (getRuntime() >= 30) {
            state = 999;
        }
    }

    int getState() {
        return state;
    }

    void nextState() {
        state++;
    }

    boolean driveEncodersReset() {
        boolean reset = false;
        if (motorPosition(drive_left) == 0 && motorPosition(drive_right) == 0) {
            reset = true;
        }
        return reset;
    }

    void movement(String movement, double distance, double power) {
        if (movement.equals("forward")) {
            distance = distance*COUNTS_PER_INCH_DRIVE;
            setDrivePower(power,power);
            if (Math.abs(motorPosition(drive_left)) >= distance) {
                setMotorPower(drive_left,0.0f);
            }
            if (Math.abs(motorPosition(drive_right)) >= distance) {
                setMotorPower(drive_right,0.0f);
            }
            if (Math.abs(motorPosition(drive_left)) >= distance
                    && Math.abs(motorPosition(drive_right)) >= distance) {
                setDrivePower(0.0f,0.0f);
                resetEncoder(drive_left);
                resetEncoder(drive_right);
                nextState();
            }
        }
        if (movement.equals("backwards")) {
            distance = distance*COUNTS_PER_INCH_DRIVE;
            setDrivePower(-power,-power);
            if (Math.abs(motorPosition(drive_left)) >= distance) {
                setMotorPower(drive_left,0.0f);
            }
            if (Math.abs(motorPosition(drive_right)) >= distance) {
                setMotorPower(drive_right,0.0f);
            }
            if (Math.abs(motorPosition(drive_left)) >= distance
                    && Math.abs(motorPosition(drive_right)) >= distance) {
                setDrivePower(0.0f,0.0f);
                resetEncoder(drive_left);
                resetEncoder(drive_right);
                nextState();
            }
        }
        if (movement.equals("right")) {
            distance = distance*COUNTS_PER_DEGREE_DRIVE;
            setDrivePower(power,-power);
            if (Math.abs(motorPosition(drive_left)) >= distance) {
                setMotorPower(drive_left,0.0f);
            }
            if (Math.abs(motorPosition(drive_right)) >= distance) {
                setMotorPower(drive_right,0.0f);
            }
            if (Math.abs(motorPosition(drive_left)) >= distance
                    && Math.abs(motorPosition(drive_right)) >= distance) {
                setDrivePower(0.0f,0.0f);
                resetEncoder(drive_left);
                resetEncoder(drive_right);
                nextState();
            }
        }
        if (movement.equals("left")) {
            distance = distance*COUNTS_PER_DEGREE_DRIVE;
            setDrivePower(-power,power);
            if (Math.abs(motorPosition(drive_left)) >= distance) {
                setMotorPower(drive_left,0.0f);
            }
            if (Math.abs(motorPosition(drive_right)) >= distance) {
                setMotorPower(drive_right,0.0f);
            }
            if (Math.abs(motorPosition(drive_left)) >= distance
                    && Math.abs(motorPosition(drive_right)) >= distance) {
                setDrivePower(0.0f,0.0f);
                resetEncoder(drive_left);
                resetEncoder(drive_right);
                nextState();
            }
        }
    }
}