package com.qualcomm.ftcrobotcontroller.opmodes;

public class CaneBotController extends CaneBotTelemetry {

    public CaneBotController() {

    }

    @Override
    public void start() {
        super.start();
        motorKill();
        resetStartTime();
    }

    @Override
    public void loop() {

        double c1_RightY = -gamepad1.right_stick_y;
        double c1_LeftY = -gamepad1.left_stick_y;
        setDrivePower(c1_LeftY, c1_RightY);

        double c2_RightY = -gamepad2.right_stick_y;
        setArmPower(c2_RightY);

        if (gamepad2.right_bumper) {
            setDumpPower(0.1);
        } else if (gamepad2.left_bumper) {
            setDumpPower(-0.1);
        } else {
            setDumpPower(0.0);
        }

        if (gamepad2.a) {
            setLidPosition("open");
        }

        if (gamepad2.b) {
            setLidPosition("close");
        }

        updateTelemetry();
    }
}
