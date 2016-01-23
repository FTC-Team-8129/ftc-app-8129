package com.qualcomm.ftcrobotcontroller.opmodes;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.ftccommon.DbgLog;

public class TuleNavX extends TuleServos {
	
    private AHRS imuNavX;
	
	final int NAVX_PORT = 0;
	final byte NAVX_RATE = 50;
	
	public TuleNavX() {
		
	}
	
	@Override
	public void init() {
		super.init();

		try {
			imuNavX = AHRS.getInstance(
				hardwareMap.deviceInterfaceModule.get("Device Interface Module 1"),
				NAVX_PORT,
				AHRS.DeviceDataType.kProcessedData,
				NAVX_RATE);
		} catch (Exception exception) {
			addWarningMessage("navX-micro IMU");
			DbgLog.msg(exception.getLocalizedMessage());
			imuNavX = null;
		}
	}
	
	float navX_value(String type) {
        float value = 0;
        if (imuNavX != null) {
            if (type.equals("yaw")) {
                value = imuNavX.getYaw();
            }
            if (type.equals("pitch")) {
                value = imuNavX.getPitch();
            }
            if (type.equals("roll")) {
                value = imuNavX.getRoll();
            }
            if (type.equals("compass")) {
                value = imuNavX.getCompassHeading();
            }
            if (type.equals("worldAccelX")) {
                value = imuNavX.getWorldLinearAccelX();
            }
            if (type.equals("worldAccelY")) {
                value = imuNavX.getWorldLinearAccelY();
            }
            if (type.equals("worldAccelZ")) {
                value = imuNavX.getWorldLinearAccelZ();
            }
            if (type.equals("rawAccelX")) {
                value = imuNavX.getRawAccelX();
            }
            if (type.equals("rawAccelY")) {
                value = imuNavX.getRawAccelY();
            }
            if (type.equals("rawAccelZ")) {
                value = imuNavX.getRawAccelZ();
            }
            if (type.equals("rawGyroX")) {
                value = imuNavX.getRawGyroX();
            }
            if (type.equals("rawGyroY")) {
                value = imuNavX.getRawGyroY();
            }
            if (type.equals("rawGyroZ")) {
                value = imuNavX.getRawGyroZ();
            }
            if (type.equals("rawMagX")) {
                value = imuNavX.getRawMagX();
            }
            if (type.equals("rawMagY")) {
                value = imuNavX.getRawMagY();
            }
            if (type.equals("rawMagZ")) {
                value = imuNavX.getRawMagZ();
            }
            if (type.equals("quatW")) {
                value = imuNavX.getQuaternionW();
            }
            if (type.equals("quatX")) {
                value = imuNavX.getQuaternionX();
            }
            if (type.equals("quatY")) {
                value = imuNavX.getQuaternionY();
            }
            if (type.equals("quatX")) {
                value = imuNavX.getQuaternionZ();
            }
        }
        return value;
    }
}