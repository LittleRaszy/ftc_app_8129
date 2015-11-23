package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleTelemetry extends TuleHardware {

    public TuleTelemetry() {

    }

    public void updateTelemetry() {

        if (warningGenerated) {
            telemetry.addData("0.0", warningMessage);
        }

        telemetry.addData("0.1", "Running for " + (Math.round(1000*getRuntime())/1000) + " seconds");

        telemetry.addData("1.0",
                "Left Drive: "
                        + motorLeft_Power()*100 + "% Power, "
                        + motorLeft_Position() + " Counts");
        telemetry.addData("1.1",
                "Right Drive: "
                        + motorRight_Power()*100 + "% Power, "
                        + motorRight_Position() + " Counts");
        telemetry.addData("1.2",
                "Arm Motor: "
                        + motorArm_Power()*100 + "% Power, "
                        + motorArm_Position() + " Counts");
        telemetry.addData("1.3",
                "Dump Motor: "
                        + motorDump_Power()*100 + "% Power"
                        + motorDump_Position());
        telemetry.addData("2.0",
                "Lid Servo: "
                        + servoLid_Position());
    }
}
