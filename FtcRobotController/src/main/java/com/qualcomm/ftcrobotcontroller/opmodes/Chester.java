package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
//  Import modules needed for this file

public class Chester extends OpMode
//  Declare OpMode class
{
    final static double Door1_OPEN = 0.294;
    final static double Door1_CLOSE = 0.118;
    final static double Door2_OPEN = 0.549;
    final static double Door2_CLOSE = 0.392;
    final static double Lock_OPEN = 0.980;
    final static double Lock_CLOSE = 0.216;
    //  Set servo open and close positions (ROBOTC position divided by 255)

    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor motorArm;
    DcMotor motorLift;
    //  Declare the names used for motors

    Servo servoDoor1;
    Servo servoDoor2;
    Servo servoLock;
    //  Declare the names used for servos


    public Chester()
    //  This is only a constructor, the program itself puts stuff here as it runs
    {

    }

    @Override
    public void init()
    //  Initialization processes
    {
        motorRight = hardwareMap.dcMotor.get("right");
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        motorLeft = hardwareMap.dcMotor.get("left");
        motorArm = hardwareMap.dcMotor.get("arm");
        motorLift = hardwareMap.dcMotor.get("lift");
        motorLift.setDirection(DcMotor.Direction.REVERSE);
        //  Set motors declared in robot configuration file to names labeled above

        servoDoor1 = hardwareMap.servo.get("door1");
        servoDoor2 = hardwareMap.servo.get("door2");
        servoLock = hardwareMap.servo.get("lock");
        //  Set servos declared in robot configuration file to names labeled above

        servoDoor1.setPosition(Door1_CLOSE);
        servoDoor2.setPosition(Door2_CLOSE);
        servoLock.setPosition(Lock_OPEN);
        //  Set servos to starting positions
    }

    @Override
    public void loop()
    //  Main loop for running Tele-Op functions
    {
    //  GAMEPAD 1

        float yValRight = gamepad1.right_stick_y;
        float yValLeft = gamepad1.left_stick_y;
        yValRight = Range.clip(yValRight, -1, 1);
        yValLeft = Range.clip(yValLeft, -1, 1);
        //  Save joystick values and trim them so they stay between -1 and 1

        float motorRightSpeed = (float) motorScale(yValRight);
        float motorLeftSpeed = (float) motorScale(yValLeft);
        //  Scale joystick value to motor speeds

        if (gamepad1.right_bumper)
        //  If the right bumper is pressed
        {
            motorRight.setPower(0.25);
            motorLeft.setPower(0.25);
            //  Set motors to going forward at 25% power
        } 
		
		else if (gamepad1.left_bumper)
        //  If the left bumper is pressed
        {
            motorRight.setPower(-0.25);
            motorLeft.setPower(-0.25);
            //  Set motors to going backward at 25% power
        } 
		
		else if (gamepad1.right_trigger > 0.5)
        //  If the right trigger is pressed past the half-way point
        {
            motorRight.setPower(-0.25 * gamepad1.right_trigger);
            motorLeft.setPower(0.25 * gamepad1.right_trigger);
            //  Set motors to turning right at a max of 25% power
        } 
		
		else if (gamepad1.left_trigger > 0.5)
        //  If the left trigger is pressed past the half-way point
        {
            motorRight.setPower(0.25 * gamepad1.left_trigger);
            motorLeft.setPower(-0.25 * gamepad1.left_trigger);
            //  Set motors to turning left at a max of 25% power
        } 
		
		else
        //  If no top buttons are pressed
        {
            motorRight.setPower(motorRightSpeed);
            motorLeft.setPower(motorLeftSpeed);
            //  Set motors to values based on joystick positions
        }

    //  GAMEPAD 2

        float yValArm = gamepad2.right_stick_y;
        float yValLift = gamepad2.left_stick_y;
        yValArm = Range.clip(yValArm, -1, 1);
        yValLift = Range.clip(yValLift, -1, 1);
        //  Save joystick values and trim them so they stay between -1 and 1

        float motorArmSpeed = (float) motorScale(yValArm);
        float motorLiftSpeed = (float) motorScale(yValLift);
        //  Scale joystick value to motor speeds

        motorArm.setPower(motorArmSpeed);
        motorLift.setPower(motorLiftSpeed);
        //  Set motors to values based on joystick positions

        if(gamepad2.right_bumper)
        //  If the right bumper is pressed
        {
            servoDoor1.setPosition(Door1_OPEN);
            //  Open Door1
        }

        else
        //  While the right bumper is not pressed
        {
            servoDoor1.setPosition(Door1_CLOSE);
            //  Keep Door1 closed
        }

        if(gamepad2.left_bumper)
        //  If the left bumper is pressed
        {
            servoDoor2.setPosition(Door2_OPEN);
            //  Open Door2
        }

        else
        //  While the left bumper is not pressed
        {
            servoDoor2.setPosition(Door2_CLOSE);
            //  Keep Door2 closed
        }

        if(gamepad2.right_trigger > 0.5)
        //  If the right trigger is pressed past the half way point
        {
            servoLock.setPosition(Lock_CLOSE);
            //  Close the lock
        }

        if(gamepad2.left_trigger > 0.5)
        //  If the left trigger is pressed past the half way point
        {
            servoLock.setPosition(Lock_OPEN);
            //  Open the lock
        }
    }


    double motorScale(double dVal)
    {
        double scaledValue = Math.pow(Math.abs(dVal), 1.5);
        //  Scale the joystick value to the graph of x^1.5

        if(Math.abs(scaledValue) > 1)
        //  If the absolute value of scaledValue is greater than one
        {
            scaledValue = 1;
            //  Set scaledValue to one
        }

        if(Math.abs(dVal) < 0.125)
        //  If the absolute value of the joystick is less than one-eighth
        {
            scaledValue = 0;
            //  Set scaledValue to zero
        }

        if(dVal < 0)
        //  If the joystick value is negative
        {
            scaledValue = -scaledValue;
            //  Negate scaledValue
        }

        return scaledValue;
        //  Return final value of scaledValue
    }
}