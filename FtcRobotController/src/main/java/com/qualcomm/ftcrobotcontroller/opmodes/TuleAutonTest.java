package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleAutonTest extends TuleAutonFunctions {

    public TuleAutonTest() {

    }

    @Override
    public void start() {
        autonInit();
    }

    @Override
    public void loop() {
        switch (getState()) {
            case 0:
                resetEncoder(drive_left);
                resetEncoder(drive_right);
                waitForReset();
                break;
            case 999:
                motorKill();
                resetEncoder(drive_left);
                resetEncoder(drive_right);
                waitForReset();
                break;
            default:
                break;
        }

        telemetry.addData("0.2", "State: " + getState());
        updateTelemetry();
    }
}