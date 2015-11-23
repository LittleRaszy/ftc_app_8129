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
                linearMove("forwards",60,1.0f);
                break;
            case 2:
                waitForReset();
                break;
            case 3:
                checkTime();
                runWithEncoders();
                linearMove("right",180,1.0f);
                break;
            case 4:
                waitForReset();
                break;
            case 5:
                checkTime();
                runWithEncoders();
                linearMove("forwards",60,1.0f);
                break;
            case 6:
                waitForReset();
                break;
            case 999:
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