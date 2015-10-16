package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class TelemetryTest extends OpMode {

    public TelemetryTest ()
    {

    }

    @Override
    public void init()
    {
        resetStartTime();
    }

    @Override
    public void loop()
    {
        //  GAMEPAD 1
        double c1_yValRight = gamepad1.right_stick_y;
        double c1_xValRight = gamepad1.right_stick_x;
        double c1_yValLeft = gamepad1.left_stick_y;
        double c1_xValLeft = gamepad1.left_stick_x;

        //  GAMEPAD 2
        double c2_yValRight = gamepad2.right_stick_y;
        double c2_xValRight = gamepad2.right_stick_x;
        double c2_yValLeft = gamepad2.left_stick_y;
        double c2_xValLeft = gamepad2.left_stick_x;

        telemetry.addData("Gamepad 1", String.format("Left = (%1.3f,%1.3f), Right = (%1.3f,%1.3f)", c1_xValLeft, c1_yValLeft, c1_xValRight, c1_yValRight));
        telemetry.addData("Gamepad 2", String.format("Left = (%1.3f,%1.3f), Right = (%1.3f,%1.3f)", c2_xValLeft, c2_yValLeft, c2_xValRight, c2_yValRight));
        telemetry.addData("Runtime", String.format("Robot has been running for %3.1f seconds", getRuntime()));

    }
}
