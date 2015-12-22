package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleAutonTest extends TuleTelemetry {

    public TuleAutonTest() {

    }

    @Override
    public void start() {
        super.start();
        resetDriveEncoders();
        resetArmEncoder();
    }

    @Override
    public void loop() {
        switch (getState()) {
            case 0:
                resetDriveEncoders();
                resetArmEncoder();
                waitForReset();
                break;
            case 1:
                checkTime();
                runWithEncoders();
                setArmPosition("up", 2);
                break;
            case 2:
                waitForReset();
                break;
            case 999:
                motorKill();
                resetDriveEncoders();
                waitForReset();
                break;
            default:
                break;
        }

        telemetry.addData("0.2", "State: " + getState());
        updateTelemetry();
    }
}