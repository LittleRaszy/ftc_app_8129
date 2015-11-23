package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleController extends TuleTelemetry {

    public TuleController() {

    }

    @Override
    public void start() {
        super.start();
        motorKill();
        resetStartTime();
        setLidPosition(0.0f);
    }

    @Override
    public void loop() {

        double c1_RightY = -gamepad1.right_stick_y;
        double c1_LeftY = -gamepad1.left_stick_y;
        setDrivePower(c1_LeftY, c1_RightY);

        double c2_RightY = -gamepad2.right_stick_y;
        setArmPower(c2_RightY);

        if (gamepad2.back) {
            resetDumpEncoder();
        } else if (gamepad2.right_bumper) {
            setDumpPosition("right", 90);
        } else if (gamepad2.left_bumper) {
            setDumpPosition("left", 90);
        } else if (gamepad2.x) {
            if (motorDump_Position() > 0) {
                setDumpPosition("right", 0);
            }
            if (motorDump_Position() < 0) {
                setDumpPosition("left", 0);
            }
        } else if (gamepad2.dpad_right) {
            setDumpPower(-0.1f);
        } else if (gamepad2.dpad_left) {
            setDumpPower(0.1f);
        } else {
            setDumpPower(0.0f);
        }
		
        if (gamepad2.a) {
            setLidPosition(0.6f);
        } else if (gamepad2.b) {
            setLidPosition(0.0f);
        }

        updateTelemetry();
    }
}
