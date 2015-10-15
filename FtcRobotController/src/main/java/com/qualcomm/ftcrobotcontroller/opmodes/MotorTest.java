package com.qualcomm.ftcrobotcontroller.opmodes;
//  Use in robot controller opmode package

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
//  Import modules used in file

public class MotorTest extends OpMode
//  Declare class, take code from OpMode file
{
    DcMotor motorLeft;
    DcMotor motorRight;
    //  Declare motor names

    public MotorTest()
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
        //  Find left and right motors and reverse right motor
    }

    @Override
    public void loop()
    //  Main loop
    {
        //  CONTROLLER 1

        float c1_RightY = -gamepad1.right_stick_y;
        float c1_LeftY = -gamepad1.left_stick_y;
        //  Save controller 1 joystick values

        motorRight.setPower(c1_RightY);
        //  Set right motor to right joystick
        motorRight.setPower(c1_LeftY);
        //  Set left motor to left joystick

    }

    @Override
    public void stop()
    //  Final processes before shutdown
    {

    }
}
