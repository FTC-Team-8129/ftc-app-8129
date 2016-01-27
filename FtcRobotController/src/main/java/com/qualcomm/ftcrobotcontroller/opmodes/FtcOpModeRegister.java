package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

public class FtcOpModeRegister implements OpModeRegister {

    public void register(OpModeManager manager) {

        manager.register("Controller", TuleController.class);
        manager.register("Auton - Park - Right", TuleAutonPark_Right.class);
        manager.register("Auton - Park - Left", TuleAutonPark_Left.class);
        //  TODO Set up autonomous modes - dump climbers, climb ramp, etc.

    }
}
