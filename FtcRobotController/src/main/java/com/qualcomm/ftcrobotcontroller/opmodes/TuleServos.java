package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class TuleServos extends TuleMotors {

    Servo lid;
    Servo rightLever;
    Servo leftLever;
    Servo rightBallast;
    Servo leftBallast;

    public TuleServos() {

    }

    @Override
    public void loop() {
        super.init();

        try {
            lid = hardwareMap.servo.get("lid");
        } catch (Exception exception) {
            addWarningMessage("Lid Servo");
            DbgLog.msg(exception.getLocalizedMessage());
            lid = null;
        }

        try {
            rightLever = hardwareMap.servo.get("right lever");
        } catch (Exception exception) {
            addWarningMessage("Right Lever Servo");
            DbgLog.msg(exception.getLocalizedMessage());
            rightLever = null;
        }

        try {
            leftLever = hardwareMap.servo.get("left lever");
        } catch (Exception exception) {
            addWarningMessage("Left Lever Servo");
            DbgLog.msg(exception.getLocalizedMessage());
            leftLever = null;
        }

        try {
            rightBallast = hardwareMap.servo.get("right ballast");
        } catch (Exception exception) {
            addWarningMessage("Right Ballast Servo");
            DbgLog.msg(exception.getLocalizedMessage());
            rightBallast = null;
        }

        try {
            leftBallast = hardwareMap.servo.get("left ballast");
        } catch (Exception exception) {
            addWarningMessage("Left Ballast Servo");
            DbgLog.msg(exception.getLocalizedMessage());
            leftBallast = null;
        }
    }

    double servoPosition(Servo servo) {
        double position = 0.0f;
        if (servo != null) {
            position = servo.getPosition();
        }
        return position;
    }

    void setServo(Servo servo, double position) {
        if (servo != null) {
            servo.setPosition(position);
        }
    }
}
