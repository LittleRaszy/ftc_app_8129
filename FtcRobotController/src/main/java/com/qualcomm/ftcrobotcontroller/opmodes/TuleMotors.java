package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class TuleMotors extends TuleSetup {

    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorArm;
    private DcMotor motorDump;
    private DcMotor motorPivot;

    public TuleMotors() {

    }

    @Override
    public void init() {
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
            motorPivot = hardwareMap.dcMotor.get("pivot");
        } catch (Exception exception) {
            addWarningMessage("Pivot Motor");
            DbgLog.msg(exception.getLocalizedMessage());
            motorPivot = null;
        }
    }

    double motorLeft_Power() {
        double power = 0.0f;
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
        double power = 0.0f;
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

    void resetDriveEncoders() {
        if (motorLeft != null) {
            motorLeft.setMode(
                    DcMotorController.RunMode.RESET_ENCODERS);
        }
        if (motorRight != null) {
            motorRight.setMode(
                    DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    double motorArm_Power() {
        double power = 0.0f;
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
            motorArm.setMode(
                    DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    int motorDump_Position() {
        int position = 0;
        if (motorDump != null) {
            position = motorDump.getCurrentPosition();
        }
        return position;
    }

    double motorDump_Power() {
        double power = 0.0f;
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

    void resetDumpEncoder() {
        if (motorDump != null) {
            motorDump.setMode(
                    DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    int motorPivot_Position() {
        int position = 0;
        if (motorPivot != null) {
            position = motorPivot.getCurrentPosition();
        }
        return position;
    }

    double motorPivot_Power() {
        double power = 0.0f;
        if (motorPivot != null) {
            power = motorPivot.getPower();
        }
        return power;
    }

    void setPivotPower(double power) {
        if (motorPivot != null) {
            motorPivot.setPower(power);
        }
    }

    void resetPivotEncoder() {
        if (motorPivot != null) {
            motorPivot.setMode(
                    DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    void runWithEncoders() {
        if (motorLeft != null) {
            motorLeft.setMode(
                    DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
        if (motorRight != null) {
            motorRight.setMode(
                    DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
        if (motorArm != null) {
            motorArm.setMode(
                    DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
        if (motorDump != null) {
            motorDump.setMode(
                    DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
        if (motorPivot != null) {
            motorPivot.setMode(
                    DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    }
}
