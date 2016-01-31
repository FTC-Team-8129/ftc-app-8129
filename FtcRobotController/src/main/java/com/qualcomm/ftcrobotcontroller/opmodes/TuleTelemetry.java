package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleTelemetry extends TuleFunctions {

    public TuleTelemetry() {

    }

    public void updateTelemetry() {

        if (warningGenerated) {
            telemetry.addData("0.0", warningMessage);
        } else {
            telemetry.addData("0.0", "No Errors");
        }
        telemetry.addData("0.1", "Running for "
                + (Math.round(1000*getRuntime())/1000) + " seconds");

        telemetry.addData("1.0",
                "Left Drive: "
                        + (int)(motorPower(drive_left)*100) + "% Power, "
                        + (int)(drive_left_v) + " Counts/s, "
                        + Math.round(drive_left_scale*100)/100);
        telemetry.addData("1.1",
                "Right Drive: "
                        + (int)(motorPower(drive_right)*100) + "% Power, "
                        + (int)(drive_right_v) + " Counts/s, "
                        + Math.round(drive_right_scale*100)/100);
        telemetry.addData("1.2",
                "Left Arm Motor: "
                        + (int)(motorPower(arm_left)*100) + "% Power, "
                        + arm_left_count_current + "Counts, "
                        + (int)(arm_left_v) + " Counts/s, "
                        + Math.round(arm_left_scale*100)/100);
        telemetry.addData("1.3",
                "Right Arm Motor: "
                        + (int)(motorPower(arm_right)*100) + "% Power, "
                        + arm_right_count_current + "Counts, "
                        + (int)(arm_right_v) + " Counts/s, "
                        + Math.round(arm_right_scale*100)/100);

        telemetry.addData("4.0","Max Arm Error: " + (int)(arm_error_max) + " Counts");
    }
}