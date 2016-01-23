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
        telemetry.addData("0.2",
                "Orientation (Y,P,R): "
                        + navX_value("yaw") + ", "
                        + navX_value("pitch") + ", "
                        + navX_value("roll"));

        telemetry.addData("1.0",
                "Left Drive: "
                        + (int)(motorPower(drive_left)*100) + "% Power, "
                        + (int)((current_driveLeftCount - last_driveLeftCount)
                        /(current_driveTime - last_driveTime)) + " Counts/s, "
                        + Math.round(driveLeftScale*100)/100);
        telemetry.addData("1.1",
                "Right Drive: "
                        + (int)(motorPower(drive_right)*100) + "% Power, "
                        + (int)((current_driveRightCount - last_driveRightCount)
                        /(current_driveTime - last_driveTime)) + " Counts/s, "
                        + Math.round(driveRightScale*100)/100);
        telemetry.addData("1.2",
                "Left Arm Motor: "
                        + (int)(motorPower(arm_left)*100) + "% Power, "
                        + current_armLeftCount + "Counts, "
                        + (int)(arm_left_v) + " Counts/s, "
                        + Math.round(armLeftScale*100)/100);
        telemetry.addData("1.3",
                "Right Arm Motor: "
                        + (int)(motorPower(arm_right)*100) + "% Power, "
                        + current_armRightCount + "Counts, "
                        + (int)(arm_right_v) + " Counts/s, "
                        + Math.round(armRightScale*100)/100);
        telemetry.addData("4.0","Max Error: " + (int)(max_error) + " Counts");
    }
}