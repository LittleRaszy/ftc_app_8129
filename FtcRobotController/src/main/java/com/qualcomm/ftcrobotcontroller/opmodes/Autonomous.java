package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Autonomous extends OpMode {

    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor motorScoop;
    DcMotor motorArm1;
    DcMotor motorArm2;
    //  Declare motor names

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
        motorScoop = hardwareMap.dcMotor.get("scoop");
        motorArm1 = hardwareMap.dcMotor.get("arm1");
        motorArm2 = hardwareMap.dcMotor.get("arm2");
        //  Set motors to names set in controller app

    }

    @Override
    public void loop()
    {
        resetStartTime();

    }

//    void forward(int speed, double distance)
//    {
//        while
//        motorLeft.setPower(speed);
//        motorRight.setPower(speed);
//    }
}
