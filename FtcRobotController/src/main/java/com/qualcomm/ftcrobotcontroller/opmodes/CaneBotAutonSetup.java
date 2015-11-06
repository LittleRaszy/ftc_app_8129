package com.qualcomm.ftcrobotcontroller.opmodes;

public class CaneBotAutonSetup extends CaneBotTelemetry {

    final double COUNTS_PER_REVOLUTION = 1120;
    final double GEAR_RATIO = 2;
    final double WHEEL_DIAMETER = 5;
    final double TURN_DIAMETER = 17;
    final double COUNTS_PER_INCH =
            COUNTS_PER_REVOLUTION*GEAR_RATIO/(WHEEL_DIAMETER*Math.PI);
    final double COUNTS_PER_DEGREE =
            Math.PI*TURN_DIAMETER/(360*COUNTS_PER_INCH);

    public int state = 0;

    public CaneBotAutonSetup() {

    }

    @Override
    public void start() {
        super.start();
        motorKill();
        resetStartTime();
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

    void linearMove(String movement, double distance, double power) {
        if (movement.equals("forwards")) {
            distance = (int)(distance*COUNTS_PER_INCH);
            setDrivePower(power,power);
            if (motorLeft_Position() >= Math.abs(distance)) {
                setLeftPower(0.0f);
            }
            if (motorRight_Position() >= Math.abs(distance)) {
                setRightPower(0.0f);
            }
            if (motorLeft_Position() >= Math.abs(distance) && motorRight_Position() >= Math.abs(distance)) {
                setDrivePower(0.0f,0.0f);
                resetDriveEncoders();
                state++;
            }
        }
        if (movement.equals("backwards")) {
            distance = (int)(distance*COUNTS_PER_INCH);
            setDrivePower(-power,-power);
            if (motorLeft_Position() >= Math.abs(distance)) {
                setLeftPower(0.0f);
            }
            if (motorRight_Position() >= Math.abs(distance)) {
                setRightPower(0.0f);
            }
            if (motorLeft_Position() >= Math.abs(distance) && motorRight_Position() >= Math.abs(distance)) {
                setDrivePower(0.0f,0.0f);
                resetDriveEncoders();
                state++;
            }
        }
        if (movement.equals("right")) {
            distance = (int)(distance*COUNTS_PER_DEGREE);
            setDrivePower(power,-power);
            if (motorLeft_Position() >= Math.abs(distance)) {
                setLeftPower(0.0f);
            }
            if (motorRight_Position() >= Math.abs(distance)) {
                setRightPower(0.0f);
            }
            if (motorLeft_Position() >= Math.abs(distance) && motorRight_Position() >= Math.abs(distance)) {
                setDrivePower(0.0f,0.0f);
                resetDriveEncoders();
                state++;
            }
        }
        if (movement.equals("left")) {
            distance = (int)(distance*COUNTS_PER_DEGREE);
            setDrivePower(-power,power);
            if (motorLeft_Position() >= Math.abs(distance)) {
                setLeftPower(0.0f);
            }
            if (motorRight_Position() >= Math.abs(distance)) {
                setRightPower(0.0f);
            }
            if (motorLeft_Position() >= Math.abs(distance) && motorRight_Position() >= Math.abs(distance)) {
                setDrivePower(0.0f,0.0f);
                resetDriveEncoders();
                state++;
            }
        }
    }
}