package com.qualcomm.ftcrobotcontroller.opmodes;
//  Use in robot controller opmode package

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.util.Range;
//  Import modules used in file

public class MotorTest extends OpMode
//  Declare class, take code from OpMode file
{
    DcMotor motorLeft;
    DcMotor motorRight;
    //DcMotor motorClimb;
    //  Declare motor names

    public MotorTest()
    //  Constructer to save information
    {

    }

    @Override
    public void init()
    //  Initialization tasks
    {
        motorLeft = hardwareMap.dcMotor.get("left");
        motorRight = hardwareMap.dcMotor.get("right");
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        //motorClimb = hardwareMap.dcMotor.get("climb");
        //  Set motors to names set in controller app
    }

    @Override
    public void loop()
    //  Main loop
    {
        double yValRight = gamepad1.right_stick_y;
        double yValLeft = gamepad1.left_stick_y;
        //  Save gamepad joystick values

        motorRight.setPower(yValRight);
        motorLeft.setPower(yValLeft);
        //  Set motors to joystick values

//        if(gamepad1.right_trigger>0.2)
//        //  If the right trigger is at least 20% depressed
//        {
//            motorClimb.setPower(gamepad1.right_trigger);
//            //  Set power to right trigger value
//        }
//        else if(gamepad1.left_trigger>0.2)
//        //  If the left trigger is at least 20% depressed
//        {
//            motorClimb.setPower(gamepad1.left_trigger);
//            //  Set power to left trigger value
//        }
    }
}
