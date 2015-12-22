package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleController extends TuleTelemetry {

    boolean reverseControl = false;

    public TuleController() {

    }

    @Override
    public void start() {
		
        super.start();
    }

    @Override
    public void loop() {

        if (gamepad1.a) {
            reverseControl = true;
        } else if (gamepad1.b) {
            reverseControl = false;
        }

        if (reverseControl) {
            setDrivePower(gamepad1.right_stick_y, gamepad1.left_stick_y);
        } else {
            setDrivePower(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
        }
		
        setArmPower(-gamepad2.right_stick_y);
        setPivotPower(-gamepad2.left_stick_y);

        if (gamepad2.b) {
            setDumpPower(-1.0f);
        } else if (gamepad2.x) {
            setDumpPower(1.0f);
        } else {
            setDumpPower(0.0f);
        }
		
        if (gamepad2.right_stick_button) {
            setLidPosition(0.6f);
        } else if (gamepad2.left_stick_button) {
            setLidPosition(0.0f);
        }

        if (gamepad2.right_bumper) {
            setRightLeverPosition(0.4f);
        } else if (gamepad2.right_trigger > 0.1f) {
            setRightLeverPosition(1.0f);
        }
        
        if (gamepad2.left_bumper) {
            setLeftLeverPosition(0.6f);
        } else if (gamepad2.left_trigger > 0.1f) {
            setLeftLeverPosition(0.0f);
        }

        if (gamepad2.dpad_up) {
            setClimberPosition(1.0f);
        } else if (gamepad2.dpad_down) {
            setClimberPosition(0.0f);
        }
        
        updateTelemetry();
    }
}