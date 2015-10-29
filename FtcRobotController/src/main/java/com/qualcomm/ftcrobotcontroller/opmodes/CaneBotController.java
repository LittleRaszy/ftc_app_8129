package com.qualcomm.ftcrobotcontroller.opmodes;
//  Use in robot controller OpMode package

public class CaneBotController extends CaneBotHardware
//  Declare class, take code from OpMode file
{
    public CaneBotController()
    //  Constructor to save information
    {
    }

    @Override
    public void init()
    //  Initialization tasks
    {
    }

    @Override
    public void loop()
    //  Main loop
    {
        //  CONTROLLER 1

        double c1_RightY = -gamepad1.right_stick_y;
        double c1_LeftY = -gamepad1.left_stick_y;
        //  Save controller 1 joystick values

        motorRight.setPower(scale("linear",c1_RightY));
        //  Set right motor to right joystick
        motorLeft.setPower(scale("linear",c1_LeftY));
        //  Set left motor to left joystick


        //  CONTROLLER 2

        double c2_RightY = -gamepad2.right_stick_y;
        //  Save controller 2 joystick values

        motorArm.setPower(c2_RightY);
        //  Set arm motor to right joystick

        if (gamepad2.right_bumper)
        //  If the right bumper is pressed
        {
            motorDump.setPower(0.1);
            //  Turn debris holder right at 10% power
        }
        else if (gamepad2.left_bumper)
        //  If the left bumper is pressed
        {
            motorDump.setPower(-0.1);
            //  Turn debris holder left at 10% power
        }
        else
        //  If no bumper is pressed
        {
            motorDump.setPower(0);
            //  Do not move debris holder
        }
    }

    @Override
    public void stop()
    //  Final processes before shutdown
    {

    }

    double scale(String type, double value)
    //  Scale controller value to different graphs for motor values
    {
        if (type.equals("linear"))
        //  If linear function is to be used
        {
            value = value;
        }

        if (type.equals("sin"))
        //  If sin function is to be used
        {
            value = Math.sin(Math.PI*value/2);
        }

        if (type.equals("quad"))
        //  If quadratic function is to be used
        {
            if (value>=0)
                value = Math.pow(value, 2);
            else if (value<0)
                value = -Math.pow(value, 2);
        }

        if (type.equals("log"))
        //  If logarithm function is to be used
        {
            if (value>=0)
                value = Math.log(Math.sqrt(3)*Math.abs(value)+1)
                        /Math.log(Math.exp(1));
            else if (value<0)
                value = -Math.log(Math.sqrt(3)*Math.abs(value)+1)
                        /Math.log(Math.exp(1));
        }

        return value;
        //  Once value is set, return scaled value
    }

}
