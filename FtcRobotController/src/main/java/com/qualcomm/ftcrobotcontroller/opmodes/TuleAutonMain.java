package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleAutonMain extends TuleTelemetry {

    public TuleAutonMain() {

    }

    @Override
    public void start() {
        super.start();
        resetDriveEncoders();
        resetArmEncoder();
        resetDumpEncoder();
        resetPivotEncoder();
    }

    @Override
    public void loop() {
        switch (getState()) {
            case 0:
                waitForReset();
                break;
            case 1:
                checkTime();
                runWithEncoders();
                linearMove("forward", 3, 1.0f);
                break;
            case 2:
                waitForReset();
                break;
            case 3:
                checkTime();
                runWithEncoders();
                linearMove("left", 45, 1.0f);
                break;
            case 4:
                waitForReset();
                break;
            case 5:
                checkTime();
                runWithEncoders();
                linearMove("forward", 102, 1.0f);
                break;
            case 6:
                waitForReset();
                break;
            case 7:
                checkTime();
                runWithEncoders();
                linearMove("left", 45, 1.0f);
                break;
            case 8:
                waitForReset();
                break;
            case 999:
                motorKill();
                resetDriveEncoders();
                waitForReset();
                break;
            default:
                motorKill();
                break;
        }
    }
}
