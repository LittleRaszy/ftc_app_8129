package com.qualcomm.ftcrobotcontroller.opmodes;
//  Use in robot controller OpMode package

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
//  Import modules used in file

public class Controller extends OpMode
//  Declare class, take code from OpMode file
{
    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor motorArm;
    //  Declare motor names

    public Controller()
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
        //  Find left and right motors and reverse right motor
    }

    @Override
    public void loop()
    //  Main loop
    {
        //  CONTROLLER 1

        double c1_RightY = -gamepad1.right_stick_y;
        double c1_LeftY = -gamepad1.left_stick_y;
        //  Save controller 1 joystick values

        motorRight.setPower(scale("sin",c1_RightY));
        //  Set right motor to right joystick
        motorLeft.setPower(scale("sin",c1_LeftY));
        //  Set left motor to left joystick

        //  CONTROLLER 2

        double c2_RightY = -gamepad2.right_stick_y;
        //  Save controller 2 joystick values

        motorArm.setPower(c2_RightY);
        //  Set arm motor to right joystick

    }

    @Override
    public void stop()
    //  Final processes before shutdown
    {

    }

    double scale(String type, double value)
    //  Scale controller value to different graphs for motor values
    {
        int nType = 0;
        //  Declare numeral version of type for switch statement and set to zero

        if (type.equals("linear"))
        //  If linear function is to be used
        {
            nType = 0;
            //  Set numeral type to zero
        }

        if (type.equals("sin"))
        //  If sin function is to be used
        {
            nType = 1;
            //  Set numeral type to one
        }

        if (type.equals("square"))
        //  If quadratic function is to be used
        {
            nType = 2;
            //  Set numeral type to zero
        }

        if (type.equals("log"))
        //  If logarithm function is to be used
        {
            nType = 3;
            //  Set numeral type to three
        }

        if (type.equals("step"))
        //  If floor function is to be used
        {
            nType = 4;
            //  Set numeral type to four
        }

        switch (nType)
        //  Switch statement based on numeral value of function type
        {
            //  Linear function
            case 0:
                break;

            //  Sin function
            case 1:
                value = Math.sin(Math.PI*value/2);
                break;

            //  Quadratic function
            case 2:
                if (value>=0)
                    value = Math.pow(value, 2);
                else if (value<0)
                    value = -Math.pow(value, 2);
                break;

            //  Logarithmic function
            case 3:
                if (value>=0)
                    value = Math.log(Math.sqrt(3)*Math.abs(value)+1)/Math.log(Math.exp(1));
                else if (value<0)
                    value = -Math.log(Math.sqrt(3)*Math.abs(value)+1)/Math.log(Math.exp(1));
                break;

            //  Floor function
            case 4:
                value = Math.floor(5*value+0.5)/5;
                break;

            //  Default linear function
            default:
                break;
        }

        return value;
    }

}
