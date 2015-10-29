package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class CaneBotHardware extends OpMode
{
    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor motorArm;
    DcMotor motorDump;
    //  Declare motor names

    @Override
    public void init()
    {
        try
        {
            motorLeft = hardwareMap.dcMotor.get("left");
        }
        catch (Exception error)
        {
            motorLeft = null;
        }

        try
        {
            motorRight = hardwareMap.dcMotor.get("right");
            motorRight.setDirection(DcMotor.Direction.REVERSE);
        }
        catch (Exception error)
        {
            motorRight = null;
        }

        try
        {
            motorArm = hardwareMap.dcMotor.get("arm");
            motorArm.setDirection(DcMotor.Direction.REVERSE);
        }
        catch (Exception error)
        {
            motorArm = null;
        }

        try
        {
            motorDump = hardwareMap.dcMotor.get("dump");
            motorDump.setDirection(DcMotor.Direction.REVERSE);
            //  Find motors and set to correct directions
        }
        catch (Exception error)
        {
            motorDump = null;
        }
    }

    @Override
    public void loop()
    {
    }
}
