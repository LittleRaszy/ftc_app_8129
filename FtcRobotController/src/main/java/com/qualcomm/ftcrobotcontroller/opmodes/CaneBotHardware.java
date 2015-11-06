package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

public class CaneBotHardware extends OpMode {

    double servoLid_OPEN = 0.500;
    double servoLid_CLOSE = 0.000;

    public CaneBotHardware() {

    }

    @Override
    public void init() {

        warningGenerated = false;
        warningMessage = "Can't Map: ";

        try {
            motorLeft = hardwareMap.dcMotor.get("left");
        } catch (Exception exception) {
            addWarningMessage("Left Drive Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            motorLeft = null;
        }

        try {
            motorRight = hardwareMap.dcMotor.get("right");
            motorRight.setDirection(DcMotor.Direction.REVERSE);
        } catch (Exception exception) {
            addWarningMessage("Right Drive Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            motorRight = null;
        }

        try {
            motorArm = hardwareMap.dcMotor.get("arm");
            motorArm.setDirection(DcMotor.Direction.REVERSE);
        } catch (Exception exception) {
            addWarningMessage("Arm Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            motorArm = null;
        }

        try {
            motorDump = hardwareMap.dcMotor.get("dump");
            motorDump.setDirection(DcMotor.Direction.REVERSE);
        } catch (Exception exception) {
            addWarningMessage("Dump Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            motorDump = null;
        }

        try {
            servoLid = hardwareMap.servo.get("lid");
        } catch (Exception exception) {
            addWarningMessage("Lid Servo");
            DbgLog.msg(exception.getLocalizedMessage());
            servoLid = null;
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {

    }

    boolean warningGenerated = false;
    String warningMessage;

    void addWarningMessage(String errorMessage) {
        if (warningGenerated) {
            warningMessage += ", ";
        }
        warningGenerated = true;
        warningMessage += errorMessage;
    }

    double motorLeft_Power() {
        double power = 0.0;
        if (motorLeft != null) {
            power = motorLeft.getPower();
        }
        return power;
    }

    int motorLeft_Position() {
        int position = 0;
        if (motorLeft != null) {
            position = motorLeft.getCurrentPosition();
        }
        return position;
    }

    void setLeftPower(double power) {
        if (motorLeft != null) {
            motorLeft.setPower(power);
        }
    }

    double motorRight_Power() {
        double power = 0.0;
        if (motorRight != null) {
            power = motorRight.getPower();
        }
        return power;
    }

    int motorRight_Position() {
        int position = 0;
        if (motorRight != null) {
            position = motorRight.getCurrentPosition();
        }
        return position;
    }

    void setRightPower(double power) {
        if (motorRight != null) {
            motorRight.setPower(power);
        }
    }

    void setDrivePower(double leftPower, double rightPower) {
        setLeftPower(leftPower);
        setRightPower(rightPower);
    }

    void resetDriveEncoders() {
        if (motorLeft != null) {
            motorLeft.setChannelMode(
                    DcMotorController.RunMode.RESET_ENCODERS);
        }
        if (motorRight != null) {
            motorRight.setChannelMode(
                    DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    boolean driveEncodersReset() {
        boolean reset = false;
        if (motorRight_Position() == 0 && motorLeft_Position() == 0) {
            reset = true;
        }
        return reset;
    }

    double motorArm_Power() {
        double power = 0.0;
        if (motorArm != null) {
            power = motorArm.getPower();
        }
        return power;
    }

    int motorArm_Position() {
        int position = 0;
        if (motorArm != null) {
            position = motorArm.getCurrentPosition();
        }
        return position;
    }

    void setArmPower(double power) {
        if (motorArm != null) {
            motorArm.setPower(power);
        }
    }

    void resetArmEncoder() {
        if (motorArm != null) {
            motorArm.setChannelMode(
                    DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    double motorDump_Power() {
        double power = 0.0;
        if (motorDump != null) {
            power = motorDump.getPower();
        }
        return power;
    }

    void setDumpPower(double power) {
        if (motorDump != null) {
            motorDump.setPower(power);
        }
    }

    double servoLid_Position() {
        double position = 0.0;
        if (servoLid != null) {
            position = servoLid.getPosition();
        }
        return position;
    }

    void motorKill() {
        setDrivePower(0,0);
        setArmPower(0);
        setDumpPower(0);
    }

    void setLidPosition(String position) {
        if (servoLid != null) {
            if (position.equals("open")) {
                servoLid.setPosition(servoLid_OPEN);
            }
            if (position.equals("close")) {
                servoLid.setPosition(servoLid_CLOSE);
            }
        }
    }

    void runWithEncoders() {
        if (motorLeft != null) {
            motorLeft.setChannelMode(
                    DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
        if (motorRight != null) {
            motorRight.setChannelMode(
                    DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
        if (motorArm != null) {
            motorArm.setChannelMode(
                    DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    }

    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorArm;
    private DcMotor motorDump;

    private Servo servoLid;
}