package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class TuleHardware extends OpMode {

    final double COUNTS_PER_REVOLUTION_N40 = 1120;
    final double COUNTS_PER_REVOLUTION_N60 = 1680;
    final double GEAR_RATIO_DRIVE = 2;
    final double GEAR_RATIO_ARM = 1;
    final double GEAR_RATIO_DUMP = 3;
    final double GEARS_PER_INCH_ARM = 11;
    final double WHEEL_DIAMETER = 5;
    final double TURN_DIAMETER = 16;
    final double COUNTS_PER_INCH_DRIVE =
            COUNTS_PER_REVOLUTION_N40*GEAR_RATIO_DRIVE/(WHEEL_DIAMETER*Math.PI);
    final double COUNTS_PER_INCH_ARM =
            COUNTS_PER_REVOLUTION_N40*GEAR_RATIO_ARM/GEARS_PER_INCH_ARM;
    final double COUNTS_PER_DEGREE_DRIVE =
            (COUNTS_PER_INCH_DRIVE*Math.PI*TURN_DIAMETER)/360;
    final double COUNTS_PER_DEGREE_DUMP =
            COUNTS_PER_REVOLUTION_N60*GEAR_RATIO_DUMP/360;

    private int state = 0;

    public TuleHardware() {

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
        motorKill();
        resetStartTime();
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
            motorLeft.setMode(
                    DcMotorController.RunMode.RESET_ENCODERS);
        }
        if (motorRight != null) {
            motorRight.setMode(
                    DcMotorController.RunMode.RESET_ENCODERS
            );
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

	void runDumpEncoder() {
		if (motorDump != null) {
			motorDump.setMode(
					DcMotorController.RunMode.RUN_USING_ENCODERS);
		}
	}
	
    void resetDumpEncoder() {
        if (motorDump != null) {
            motorDump.setMode(
                    DcMotorController.RunMode.RESET_ENCODERS);
        }
    }
	
	void setDumpPosition(String direction, double position) {
		runDumpEncoder();
		int counts = (int)(position*COUNTS_PER_DEGREE_DUMP);
		if (direction.equals("left")) {
			setDumpPower(1.0f);
            if (motorDump_Position() >= counts) {
                setDumpPower(0.0f);
            }
		}
		if (direction.equals("right")) {
			setDumpPower(-1.0f);
            if (motorDump_Position() <= (-1*counts)) {
                setDumpPower(0.0f);
            }
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

    void setLidPosition(double position) {
        Range.clip(position, 0.0f, 1.0f);
        if (servoLid != null) {
            if (position >= 0 && position <= 1) {
                servoLid.setPosition(position);
            }
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

    void linearMove(String movement, double distance, double power) {
        if (movement.equals("forwards")) {
            distance = distance*COUNTS_PER_INCH_DRIVE;
            setDrivePower(power,power);
            if (Math.abs(motorLeft_Position()) >= distance) {
                setLeftPower(0.0f);
            }
            if (Math.abs(motorRight_Position()) >= distance) {
                setRightPower(0.0f);
            }
            if (Math.abs(motorLeft_Position()) >= distance && Math.abs(motorRight_Position()) >= distance) {
                setDrivePower(0.0f,0.0f);
                resetDriveEncoders();
                nextState();
            }
        }
        if (movement.equals("backwards")) {
            distance = distance*COUNTS_PER_INCH_DRIVE;
            setDrivePower(-power,-power);
            if (Math.abs(motorLeft_Position()) >= distance) {
                setLeftPower(0.0f);
            }
            if (Math.abs(motorRight_Position()) >= distance) {
                setRightPower(0.0f);
            }
            if (Math.abs(motorLeft_Position()) >= distance && Math.abs(motorRight_Position()) >= distance) {
                setDrivePower(0.0f,0.0f);
                resetDriveEncoders();
                nextState();
            }
        }
        if (movement.equals("right")) {
            distance = distance*COUNTS_PER_DEGREE_DRIVE;
            setDrivePower(power,-power);
            if (Math.abs(motorLeft_Position()) >= distance) {
                setLeftPower(0.0f);
            }
            if (Math.abs(motorRight_Position()) >= distance) {
                setRightPower(0.0f);
            }
            if (Math.abs(motorLeft_Position()) >= distance && Math.abs(motorRight_Position()) >= distance) {
                setDrivePower(0.0f,0.0f);
                resetDriveEncoders();
                nextState();
            }
        }
        if (movement.equals("left")) {
            distance = distance*COUNTS_PER_DEGREE_DRIVE;
            setDrivePower(-power,power);
            if (Math.abs(motorLeft_Position()) >= distance) {
                setLeftPower(0.0f);
            }
            if (Math.abs(motorRight_Position()) >= distance) {
                setRightPower(0.0f);
            }
            if (Math.abs(motorLeft_Position()) >= distance && Math.abs(motorRight_Position()) >= distance) {
                setDrivePower(0.0f,0.0f);
                resetDriveEncoders();
                nextState();
            }
        }
    }

    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorArm;
    private DcMotor motorDump;

    private Servo servoLid;
}