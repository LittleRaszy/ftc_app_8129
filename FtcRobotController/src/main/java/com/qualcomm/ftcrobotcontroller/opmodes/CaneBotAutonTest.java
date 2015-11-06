package com.qualcomm.ftcrobotcontroller.opmodes;

public class CaneBotAutonTest extends CaneBotAutonSetup {

    public CaneBotAutonTest() {

    }

    @Override
    public void loop() {
        switch (state) {
            case 0:
                resetDriveEncoders();
                resetArmEncoder();
                state++;
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
                linearMove("right",180,1.0f);
                break;
            case 4:
                waitForReset();
                break;
            case 5:
                checkTime();
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

        telemetry.addData("0.3", "State: " + state);
        updateTelemetry();
    }
}