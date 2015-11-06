package com.qualcomm.ftcrobotcontroller.opmodes;

public class CaneBotTelemetry extends CaneBotHardware {

    public CaneBotTelemetry() {

    }

    public void updateTelemetry() {

        if (warningGenerated) {
            telemetry.addData("0.0", warningMessage);
        }

        telemetry.addData("0.1", "Running for " + (Math.round(1000*getRuntime())/1000) + " seconds");

        telemetry.addData("1.1",
                "Left Drive: "
                        + motorLeft_Power()*100 + "% Power, "
                        + motorLeft_Position() + " Counts");
        telemetry.addData("1.2",
                "Right Drive: "
                        + motorRight_Power()*100 + "% Power, "
                        + motorRight_Position() + " Counts");
        telemetry.addData("1.3",
                "Arm Motor: "
                        + motorArm_Power()*100 + "% Power, "
                        + motorArm_Position() + " Counts");
        telemetry.addData("1.4",
                "Dump Motor: "
                        + motorDump_Power()*100 + "% Power");
        telemetry.addData("2.5",
                "Lid Servo: "
                        + servoLid_Position()*270 + " Degrees");
    }
}
