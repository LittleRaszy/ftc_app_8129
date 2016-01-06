package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleTelemetry extends TuleFunctions {

    public TuleTelemetry() {

    }

    public void updateTelemetry() {

        if (warningGenerated) {
            telemetry.addData("0.0", warningMessage);
        } else {
            telemetry.addData("0.0", "No Errors");
        }

        telemetry.addData("0.1", "Running for "
                + (Math.round(1000*getRuntime())/1000) + " seconds");
        telemetry.addData("0.2",
                "Magnetometer (X,Y,Z): "
                        + navX_value("rawMagX") + ", "
                        + navX_value("rawMagY") + ", "
                        + navX_value("rawMagZ"));
        telemetry.addData("0.3",
                "Gyroscope (X,Y,Z): "
                        + navX_value("rawGyroX") + ", "
                        + navX_value("rawGyroY") + ", "
                        + navX_value("rawGyroZ"));
        telemetry.addData("0.4",
                "Accelerometer (X,Y,Z): "
                        + navX_value("rawAccelX") + ", "
                        + navX_value("rawAccelY") + ", "
                        + navX_value("rawAccelZ"));
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
                        + motorDump_Position() + " Counts");
        telemetry.addData("1.4",
                "Pivot Motor: "
                        + motorPivot_Power()*100 + "% Power"
                        + motorPivot_Position() + " Counts");
        telemetry.addData("2.0",
                "Lid Servo: "
                        + (int)(servoLid_Position()*180) + " Degrees");
        telemetry.addData("2.1",
                "Right Lever Servo: "
                        + (int)(servoRightLever_position()*180) + " Degrees");
        telemetry.addData("2.2",
                "Left Lever Servo: "
                        + (int)(servoLeftLever_position()*180) + " Degrees");
    }
}