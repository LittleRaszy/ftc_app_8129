package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Autonomous extends OpMode
{
    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor motorArm
    //  Declare motor names

    final double COUNTS_PER_REVOLUTION = 1160;
    final double GEAR_RATIO = 2;
    final double WHEEL_DIAMETER = 5;
    final double COUNTS_PER_INCH = COUNTS_PER_REVOLUTION*GEAR_RATIO/(WHEEL_DIAMETER*Math.PI);

    private int motorLeft_EncoderTarget;
    private int motorRight_EncoderTarget;

    private State currentState;
    private PathSeg[] currentPath;
    private int currentSegment;

    private enum State
    {
        INITIAL,
        FORWARD,
        BACKWARD,
        TURN,
        STOP,
    }

    public Autonomous()
    //  Constructor to save information
    {

    }

    @Override
    public void init()
    //  Initialization tasks
    {
        motorLeft = hardwareMap.dcMotor.get("left");
        motorRight = hardwareMap.dcMotor.get("right");
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        motorArm = hardwareMap.dcMotor.get("arm");
        //  Set motors to names set in controller app

        resetStartTime();

    }

    @Override
    public void loop()
    {
    }

    void setMotorPower(double left, double right, double arm)
    {
        motorLeft.setPower(left);
        motorRight.setPower(right);
        motorArm.setPower(arm);
    }
}
