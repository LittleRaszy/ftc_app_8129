package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleAutonFunctions extends TuleTelemetry {
    public TuleAutonFunctions() {

    }

    void autonInit() {
        baseInit();

        runWithEncoders();
        resetArmEncoder();
        resetDriveEncoders();
        resetDumpEncoder();
        resetPivotEncoder();
    }

    void waitForReset() {
        if (driveEncodersReset()) {
            state++;
        }
    }

    void checkTime() {
        if (getRuntime() >= 30) {
            state = 999;
        }
    }

    int getState() {
        return state;
    }

    void nextState() {
        state++;
    }

    boolean driveEncodersReset() {
        boolean reset = false;
        if (motorRight_Position() == 0 && motorLeft_Position() == 0) {
            reset = true;
        }
        return reset;
    }

    void linearMove(String movement, double distance, double power) {
        if (movement.equals("forward")) {
            distance = distance*COUNTS_PER_INCH_DRIVE;
            setDrivePower(0.6*power,power);
            if (Math.abs(motorLeft_Position()) >= distance) {
                setLeftPower(0.0f);
            }
            if (Math.abs(motorRight_Position()) >= distance) {
                setRightPower(0.0f);
            }
            if (Math.abs(motorLeft_Position()) >= distance
                    && Math.abs(motorRight_Position()) >= distance) {
                setDrivePower(0.0f,0.0f);
                resetDriveEncoders();
                nextState();
            }
        }
        if (movement.equals("backwards")) {
            distance = distance*COUNTS_PER_INCH_DRIVE;
            setDrivePower(-0.6*power,-power);
            if (Math.abs(motorLeft_Position()) >= distance) {
                setLeftPower(0.0f);
            }
            if (Math.abs(motorRight_Position()) >= distance) {
                setRightPower(0.0f);
            }
            if (Math.abs(motorLeft_Position()) >= distance
                    && Math.abs(motorRight_Position()) >= distance) {
                setDrivePower(0.0f,0.0f);
                resetDriveEncoders();
                nextState();
            }
        }
        if (movement.equals("right")) {
            distance = distance*COUNTS_PER_DEGREE_DRIVE;
            setDrivePower(0.6*power,-power);
            if (Math.abs(motorLeft_Position()) >= distance) {
                setLeftPower(0.0f);
            }
            if (Math.abs(motorRight_Position()) >= distance) {
                setRightPower(0.0f);
            }
            if (Math.abs(motorLeft_Position()) >= distance
                    && Math.abs(motorRight_Position()) >= distance) {
                setDrivePower(0.0f,0.0f);
                resetDriveEncoders();
                nextState();
            }
        }
        if (movement.equals("left")) {
            distance = distance*COUNTS_PER_DEGREE_DRIVE;
            setDrivePower(-0.6*power,power);
            if (Math.abs(motorLeft_Position()) >= distance) {
                setLeftPower(0.0f);
            }
            if (Math.abs(motorRight_Position()) >= distance) {
                setRightPower(0.0f);
            }
            if (Math.abs(motorLeft_Position()) >= distance
                    && Math.abs(motorRight_Position()) >= distance) {
                setDrivePower(0.0f,0.0f);
                resetDriveEncoders();
                nextState();
            }
        }
    }
}
