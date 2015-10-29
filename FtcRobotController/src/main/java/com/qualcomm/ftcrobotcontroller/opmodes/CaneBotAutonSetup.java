package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorController;

public class CaneBotAutonSetup extends CaneBotHardware
{
    final double COUNTS_PER_REVOLUTION = 1120;
    final double GEAR_RATIO = 2;
    final double WHEEL_DIAMETER = 5;
    final double TURN_DIAMETER = 17;
    final double COUNTS_PER_INCH =
            COUNTS_PER_REVOLUTION*GEAR_RATIO/(WHEEL_DIAMETER*Math.PI);
    //  Set constants to be used in autonomous calculations

    private int state = 0;

    public CaneBotAutonSetup()
    //  Constructor to save information
    {
    }

    @Override
    public void init()
    //  Initialization tasks
    {
        motorKill();
        resetStartTime();
        //  Kill motors and set the timer to zero

        motorLeft.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorRight.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorArm.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorArm.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        //  Turn on and reset motor encoders
    }

    @Override
    public void loop()
    //  Main loop
    {
    }

    void drivePower(double left, double right)
    //  Set left and right drive motors to given values
    {
        motorLeft.setPower(left);
        motorRight.setPower(right);
        //  Set left and right motors to values
    }

    void driveTarget(double left, double right)
    //  Set position targets for motor encoders
    {
        driveEncoderReset();
        //  Reset drive encoders

        int left_position = (int)Math.round(COUNTS_PER_INCH*left);
        int right_position = (int)Math.round(COUNTS_PER_INCH*right);
        //  Convert distance in inches target to encoders pulses

        motorLeft.setTargetPosition(left_position);
        motorRight.setTargetPosition(right_position);
        //  Set position target to converted distance values
    }

    void move(String movement, double distance, double power)
    //  Move a certain way for a certain distance at a certain power
    {
        if(movement.equals("forwards"))
        //  If movement is forwards
        {
            drivePower(power, power);
            driveTarget(distance, distance);
            //  Set power and distance to given values
        }
        else if(movement.equals("backwards"))
        //  If movement is backwards
        {
            drivePower(-power, -power);
            driveTarget(-distance, -distance);
            //  Set power and distance to opposite of given values
        }
        else if(movement.equals("right"))
        //  If movement is to turn right
        {
            drivePower(power, -power);
            driveTarget(TURN_DIAMETER*Math.PI*distance/360,
                    -TURN_DIAMETER*Math.PI*distance/360);
            //  Convert distance in degrees to inches
        }
        else if(movement.equals("left"))
        //  If movement is to turn left
        {
            drivePower(-power, power);
            driveTarget(-TURN_DIAMETER*Math.PI*distance/360,
                    TURN_DIAMETER*Math.PI*distance/360);
            //  Convert distance in degrees to inches
        }
        else
        //  If no movement is given
        {
            drivePower(0, 0);
            //  Do not move
        }
    }

    void motorKill()
    //  Function to turn all motors off
    {
        motorLeft.setPower(0);
        motorRight.setPower(0);
        motorArm.setPower(0);
        motorDump.setPower(0);
        //  Set all motors to off
    }

    void driveEncoderReset()
    //  Sets all encoder values to zero
    {
        motorLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        //  Set encoder targets to zero
    }
}
