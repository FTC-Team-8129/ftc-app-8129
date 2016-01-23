package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class TuleSetup extends OpMode {
    public TuleSetup() {

    }

    @Override
    public void init() {
        warningGenerated = false;
        warningMessage = "Can't Map: ";
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {

    }

    boolean warningGenerated = false;
    String warningMessage;

    void addWarningMessage(String errorMessage) {
        if (warningGenerated) {
            warningMessage += ", ";
        }
        warningGenerated = true;
        warningMessage += errorMessage;
    }
}