package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class TuleMotors extends TuleSetup {

    DcMotor drive_left;
    DcMotor drive_right;
    DcMotor arm_left;
	DcMotor arm_right;
    DcMotor arm;
    DcMotor dump;
    DcMotor pivot;
    DcMotor slide;
    DcMotor scoop;

    public TuleMotors() {

    }

    @Override
    public void init() {
        super.init();

        try {
            drive_left = hardwareMap.dcMotor.get("drive left");
        } catch (Exception exception) {
            addWarningMessage("Left Drive Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            drive_left = null;
        }

        try {
            drive_right = hardwareMap.dcMotor.get("drive right");
            drive_right.setDirection(DcMotor.Direction.REVERSE);
        } catch (Exception exception) {
            addWarningMessage("Right Drive Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            drive_right = null;
        }

        try {
            arm_left = hardwareMap.dcMotor.get("arm left");
            arm_left.setDirection(DcMotor.Direction.REVERSE);
        } catch (Exception exception) {
            addWarningMessage("Left Arm Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            arm_left = null;
        }
		
		try {
			arm_right = hardwareMap.dcMotor.get("arm right");
		} catch (Exception exception) {
			addWarningMessage("Right Arm Motor");
			DbgLog.msg(exception.getLocalizedMessage());
			arm_right = null;
		}

        try {
            arm = hardwareMap.dcMotor.get("arm");
        } catch (Exception exception) {
            addWarningMessage("Arm Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            arm = null;
        }

        try {
            dump = hardwareMap.dcMotor.get("dump");
            dump.setDirection(DcMotor.Direction.REVERSE);
        } catch (Exception exception) {
            addWarningMessage("Dump Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            dump = null;
        }

        try {
            pivot = hardwareMap.dcMotor.get("pivot");
        } catch (Exception exception) {
            addWarningMessage("Pivot Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            pivot = null;
        }

        try {
            slide = hardwareMap.dcMotor.get("slide");
            slide.setDirection(DcMotor.Direction.REVERSE);
        } catch (Exception exception) {
            addWarningMessage("Slide Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            slide = null;
        }

        try {
            scoop = hardwareMap.dcMotor.get("scoop");
            scoop.setDirection(DcMotor.Direction.REVERSE);
        } catch (Exception exception) {
            addWarningMessage("Scoop Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            scoop = null;
        }
    }

    double motorPower(DcMotor motor) {
        double power = 0.0f;
        if (motor != null) {
            power = motor.getPower();
        }
        return power;
    }

    int motorPosition(DcMotor motor) {
        int position = 0;
        if (motor != null) {
            position = motor.getCurrentPosition();
        }
        return position;
    }

    void setMotorPower(DcMotor motor, double power) {
        if (motor != null) {
            motor.setPower(power);
        }
    }

    void resetEncoder(DcMotor motor) {
        if (motor != null) {
            motor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    void runWithEncoder(DcMotor motor) {
        if (motor != null) {
            motor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    }

    void runWithoutEncoder(DcMotor motor) {
        if (motor != null) {
            motor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    }
}
