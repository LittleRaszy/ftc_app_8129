package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleTelemetry extends TuleHardware {

    public TuleTelemetry() {

    }

    public void updateTelemetry() {

        if (warningGenerated) {
            telemetry.addData("0.0", warningMessage);
        }

        telemetry.addData("0.1", "Running for "
                + (Math.round(1000*getRuntime())/1000) + " seconds");

        telemetry.addData("1.0",
                "Left Drive: "
                        + motorLeft_Power()*100 + "% Power, "
                        + motorLeft_Position()/COUNTS_PER_INCH_DRIVE + " Inches");
        telemetry.addData("1.1",
                "Right Drive: "
                        + motorRight_Power()*100 + "% Power, "
                        + motorRight_Position()/COUNTS_PER_DEGREE_DRIVE + " Inches");
        telemetry.addData("1.2",
                "Arm Motor: "
                        + motorArm_Power()*100 + "% Power, "
                        + motorArm_Position()/COUNTS_PER_INCH_ARM + " Inches");
        telemetry.addData("1.3",
                "Dump Motor: "
                        + motorDump_Power()*100 + "% Power"
                        + motorDump_Position()/COUNTS_PER_DEGREE_DUMP + " Degrees");
        telemetry.addData("1.4",
                "Pivot Motor: "
                        + motorPivot_Power()*100 + "% Power"
                        + motorPivot_Position()/COUNTS_PER_DEGREE_PIVOT + " Degrees");
        telemetry.addData("2.0",
                "Lid Servo: "
                        + servoLid_Position()*270 + " Degrees");
    }
}
