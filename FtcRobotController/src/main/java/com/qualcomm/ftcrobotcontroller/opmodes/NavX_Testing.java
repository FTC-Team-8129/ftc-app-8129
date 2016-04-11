package com.qualcomm.ftcrobotcontroller.opmodes;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class NavX_Testing extends OpMode {

    private AHRS navx;

    final int PORT = 0;
    final byte RATE = 50;
    final AHRS.DeviceDataType DATATYPE = AHRS.DeviceDataType.kProcessedData;

    public NavX_Testing() {

    }

    @Override
    public void init() {
        try {
            navx = AHRS.getInstance(
                    hardwareMap.deviceInterfaceModule.get("Device Interface Module 1"),
                    PORT, DATATYPE, RATE);
        } catch (Exception exception) {
            telemetry.addData("0.0.0", "NavX not connected");
            DbgLog.msg(exception.getLocalizedMessage());
            navx = null;
        }
    }

    @Override
    public void loop() {

        navX_telemetry();

    }

    float navX_value(String type) {
        float value = 0;
        if (navx != null) {
            if (type.equals("yaw")) {
                value = navx.getYaw();
            }
            if (type.equals("pitch")) {
                value = navx.getPitch();
            }
            if (type.equals("roll")) {
                value = navx.getRoll();
            }
            if (type.equals("compass")) {
                value = navx.getCompassHeading();
            }
            if (type.equals("accelX")) {
                value = navx.getWorldLinearAccelX();
            }
            if (type.equals("accelY")) {
                value = navx.getWorldLinearAccelY();
            }
            if (type.equals("accelZ")) {
                value = navx.getWorldLinearAccelZ();
            }
            if (type.equals("rAccelX")) {
                value = navx.getRawAccelX();
            }
            if (type.equals("rAccelY")) {
                value = navx.getRawAccelY();
            }
            if (type.equals("rAccelZ")) {
                value = navx.getRawAccelZ();
            }
            if (type.equals("rGyroX")) {
                value = navx.getRawGyroX();
            }
            if (type.equals("rGyroY")) {
                value = navx.getRawGyroY();
            }
            if (type.equals("rGyroZ")) {
                value = navx.getRawGyroZ();
            }
            if (type.equals("rMagX")) {
                value = navx.getRawMagX();
            }
            if (type.equals("rMagY")) {
                value = navx.getRawMagY();
            }
            if (type.equals("rMagZ")) {
                value = navx.getRawMagZ();
            }
            if (type.equals("quatW")) {
                value = navx.getQuaternionW();
            }
            if (type.equals("quatX")) {
                value = navx.getQuaternionX();
            }
            if (type.equals("quatY")) {
                value = navx.getQuaternionY();
            }
            if (type.equals("quatX")) {
                value = navx.getQuaternionZ();
            }
        }
        return value;
    }

    void navX_telemetry() {
        if (navx.getDeviceDataType() == AHRS.DeviceDataType.kProcessedData) {
            telemetry.addData("1.0.0", String.format("Yaw:\t\t%1$3.2f", navX_value("yaw")));
            telemetry.addData("1.0.1", String.format("Pitch:\t\t%1$3.2f", navX_value("pitch")));
            telemetry.addData("1.0.2", String.format("Roll:\t\t%1$3.2f", navX_value("roll")));
            telemetry.addData("1.1.0", String.format("Compass:\t%1$3.2f", navX_value("compass")));
            telemetry.addData("1.2.0", String.format("AccelX:\t\t%1$3.2f", navX_value("accelX")));
            telemetry.addData("1.2.1", String.format("AccelY:\t\t%1$3.2f", navX_value("accelY")));
            telemetry.addData("1.2.2", String.format("AccelZ:\t\t%1$3.2f", navX_value("accelZ")));
        } else if (navx.getDeviceDataType() == AHRS.DeviceDataType.kQuatAndRawData) {
            telemetry.addData("1.0.0", String.format("rAccelX:\t%1$3.2f", navX_value("rAccelX")));
            telemetry.addData("1.0.1", String.format("rAccelY:\t%1$3.2f", navX_value("rAccelY")));
            telemetry.addData("1.0.2", String.format("rAccelZ:\t%1$3.2f", navX_value("rAccelZ")));
            telemetry.addData("1.1.0", String.format("GyroX:\t\t%1$3.2f", navX_value("rGyroX")));
            telemetry.addData("1.1.1", String.format("GyroY:\t\t%1$3.2f", navX_value("rGyroY")));
            telemetry.addData("1.1.2", String.format("GyroZ:\t\t%1$3.2f", navX_value("rGyroZ")));
            telemetry.addData("1.2.0", String.format("MagX:\t\t%1$3.2f", navX_value("rMagX")));
            telemetry.addData("1.2.1", String.format("MagY:\t\t%1$3.2f", navX_value("rMagY")));
            telemetry.addData("1.2.2", String.format("MagZ:\t\t%1$3.2f", navX_value("rMagZ")));
            telemetry.addData("1.3.0", String.format("QuatW:\t\t%1$3.2f", navX_value("quatW")));
            telemetry.addData("1.3.1", String.format("QuatX:\t\t%1$3.2f", navX_value("quatX")));
            telemetry.addData("1.3.2", String.format("QuatY:\t\t%1$3.2f", navX_value("quatY")));
            telemetry.addData("1.3.3", String.format("QuatZ:\t\t%1$3.2f", navX_value("quatZ")));
        } else if (navx.getDeviceDataType() == AHRS.DeviceDataType.kAll) {
            telemetry.addData("1.0.0", String.format("Yaw:\t\t%1$3.2f", navX_value("yaw")));
            telemetry.addData("1.0.1", String.format("Pitch:\t\t%1$3.2f", navX_value("pitch")));
            telemetry.addData("1.0.2", String.format("Roll:\t\t%1$3.2f", navX_value("roll")));
            telemetry.addData("1.1.0", String.format("Compass:\t%1$3.2f", navX_value("compass")));
            telemetry.addData("1.2.0", String.format("AccelX:\t\t%1$3.2f", navX_value("accelX")));
            telemetry.addData("1.2.1", String.format("AccelY:\t\t%1$3.2f", navX_value("accelY")));
            telemetry.addData("1.2.2", String.format("AccelZ:\t\t%1$3.2f", navX_value("accelZ")));
            telemetry.addData("2.0.0", String.format("rAccelX:\t%1$3.2f", navX_value("rAccelX")));
            telemetry.addData("2.0.1", String.format("rAccelY:\t%1$3.2f", navX_value("rAccelY")));
            telemetry.addData("2.0.2", String.format("rAccelZ:\t%1$3.2f", navX_value("rAccelZ")));
            telemetry.addData("2.1.0", String.format("rGyroX:\t\t%1$3.2f", navX_value("rGyroX")));
            telemetry.addData("2.1.1", String.format("rGyroY:\t\t%1$3.2f", navX_value("rGyroY")));
            telemetry.addData("2.1.2", String.format("rGyroZ:\t\t%1$3.2f", navX_value("rGyroZ")));
            telemetry.addData("2.2.0", String.format("rMagX:\t\t%1$3.2f", navX_value("rMagX")));
            telemetry.addData("2.2.1", String.format("rMagY:\t\t%1$3.2f", navX_value("rMagY")));
            telemetry.addData("2.2.2", String.format("rMagZ:\t\t%1$3.2f", navX_value("rMagZ")));
            telemetry.addData("2.3.0", String.format("QuatW:\t\t%1$3.2f", navX_value("quatW")));
            telemetry.addData("2.3.1", String.format("QuatX:\t\t%1$3.2f", navX_value("quatX")));
            telemetry.addData("2.3.2", String.format("QuatY:\t\t%1$3.2f", navX_value("quatY")));
            telemetry.addData("2.3.3", String.format("QuatZ:\t\t%1$3.2f", navX_value("quatZ")));
        } else {
            telemetry.addData("0.0.1", "No device data type found");
        }
    }
}
