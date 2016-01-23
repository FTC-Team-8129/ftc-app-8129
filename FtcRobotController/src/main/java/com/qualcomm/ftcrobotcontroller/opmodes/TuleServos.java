package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class TuleServos extends TuleMotors {

    private Servo servoLid;
    private Servo servoRightLever;
    private Servo servoLeftLever;
    private Servo servoClimber;

    public TuleServos() {

    }

    @Override
    public void loop() {
        super.init();

        try {
            servoLid = hardwareMap.servo.get("lid");
        } catch (Exception exception) {
            addWarningMessage("Lid Servo");
            DbgLog.msg(exception.getLocalizedMessage());
            servoLid = null;
        }

        try {
            servoRightLever = hardwareMap.servo.get("lever_right");
        } catch (Exception exception) {
            addWarningMessage("Right Lever Servo");
            DbgLog.msg(exception.getLocalizedMessage());
            servoRightLever = null;
        }

        try {
            servoLeftLever = hardwareMap.servo.get("lever_left");
        } catch (Exception exception) {
            addWarningMessage("Left Lever Servo");
            DbgLog.msg(exception.getLocalizedMessage());
            servoLeftLever = null;
        }

        try {
            servoClimber = hardwareMap.servo.get("climber");
        } catch (Exception exception) {
            addWarningMessage("Climber Dump Servo");
            DbgLog.msg(exception.getLocalizedMessage());
            servoClimber = null;
        }
    }

    double servoLid_Position() {
        double position = 0.0f;
        if (servoLid != null) {
            position = servoLid.getPosition();
        }
        return position;
    }

    void setLidPosition(double position) {
        Range.clip(position, 0.0f, 1.0f);
        if (servoLid != null) {
            if (position >= 0.0f && position <= 1.0f) {
                servoLid.setPosition(position);
            }
        }
    }

    double servoRightLever_position() {
        double position = 0.0f;
        if (servoRightLever != null) {
            position = servoRightLever.getPosition();
        }
        return position;
    }

    void setRightLeverPosition(double position) {
        Range.clip(position, 0.0f, 1.0f);
        if (servoRightLever != null) {
            if (position >= 0.0f && position <= 1.0f) {
                servoRightLever.setPosition(position);
            }
        }
    }

    double servoLeftLever_position() {
        double position = 0.0f;
        if (servoLeftLever != null) {
            position = servoLeftLever.getPosition();
        }
        return position;
    }

    void setLeftLeverPosition(double position) {
        Range.clip(position, 0.0f, 1.0f);
        if (servoLeftLever != null) {
            if (position >= 0.0f && position <= 1.0f) {
                servoLeftLever.setPosition(position);
            }
        }
    }

    double servoClimber_Position() {
        double position = 0.0f;
        if (servoClimber != null) {
            position = servoClimber.getPosition();
        }
        return position;
    }

    void setClimberPosition(double position) {
        Range.clip(position, 0.0f, 1.0f);
        if (servoClimber != null) {
            if (position >= 0.0f && position <= 1.0f) {
                servoClimber.setPosition(position);
            }
        }
    }
}
