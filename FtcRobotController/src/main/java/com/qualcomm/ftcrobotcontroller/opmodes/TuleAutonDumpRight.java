package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleAutonDumpRight extends TuleTelemetry {

    public TuleAutonDumpRight() {

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
            case 3:
                checkTime();
                runWithEncoders();
                linearMove("forward", 3, 1.0f);
                break;
            case 4:
                waitForReset();
                break;
            case 5:
                checkTime();
                runWithEncoders();
                linearMove("right", 45, 1.0f);
                break;
            case 6:
                waitForReset();
                break;
            case 7:
                checkTime();
                runWithEncoders();
                linearMove("forward", 100, 1.0f);
                break;
            case 8:
                waitForReset();
                break;
            case 9:
                checkTime();
                runWithEncoders();
                linearMove("right", 45, 1.0f);
                break;
            case 10:
                waitForReset();
                break;
            case 11:
                checkTime();
                runWithEncoders();
                setArmPosition("up", 4);
                break;
            case 12:
                waitForReset();
                break;
            case 13:
                checkTime();
                runWithEncoders();
                linearMove("forward", 3, 1.0f);
                break;
            case 14:
                checkTime();
                setClimberPosition(0.6f);
                if (servoClimber_Position() == 0.6f) {
                    nextState();
                }
                break;
            case 15:
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

        updateTelemetry();
    }
}