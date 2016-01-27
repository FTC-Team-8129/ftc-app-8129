package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleAutonPark_Left extends TuleAutonFunctions {
    
    public TuleAutonPark_Left() {

    }

    @Override
    public void start() {
        autonInit();
    }

    @Override
    public void loop() {
        checkTime();
        switch(state) {
            case 0:
                resetEncoder(drive_left);
                resetEncoder(drive_left);
                waitForReset();
                break;
            case 1:
                movement("forward", 3.0f, 1.0f);
                break;
            case 2:
                waitForReset();
                break;
            case 3:
                movement("left", 45.0f, 1.0f);
                break;
            case 4:
                waitForReset();
                break;
            case 5:
                movement("forward", 67.88f, 1.0f);
                break;
            case 6:
                waitForReset();
                break;
            case 7:
                movement("left", 45.0f, 1.0f);
                break;
            case 8:
                waitForReset();
                break;
            case 9:
                movement("forward", 24.0f, 1.0f);
                break;
            case 10:
                waitForReset();
                break;
            case 999:
                motorKill();
                resetEncoder(drive_left);
                resetEncoder(drive_left);
                waitForReset();
                break;
            default:
                break;
        }

        telemetry.addData("0.2", "State: " + getState());
        updateTelemetry();
    }
}
