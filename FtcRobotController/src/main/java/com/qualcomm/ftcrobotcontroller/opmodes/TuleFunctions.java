package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleFunctions extends TuleVariables {

    public TuleFunctions() {

    }

    void baseInit() {
        motorKill();
        resetStartTime();
        setClimberPosition(0.0f);
        setLeftLeverPosition(1.0f);
        setRightLeverPosition(0.0f);
        setLidPosition(0.0f);
    }

    void setDrivePower(double leftPower, double rightPower) {
        setLeftPower(leftPower);
        setRightPower(rightPower);
    }

    void motorKill() {
        setDrivePower(0.0f,0.0f);
        setArmPower(0.0f);
        setDumpPower(0.0f);
    }

    float x_positiion() {
        last_accelX = current_accelX;
        current_accelX = navX_value("accelX");
        last_timeX = current_timeX;
        current_timeX = getRuntime();
        velocityX += (int)((current_accelX+last_accelX)/2
                *(current_timeX-last_timeX)*inchesPerMeter*20);
        positionX = (float)(velocityX/20*current_timeX);
        return positionX;
    }

    float y_position() {
        last_accelY = current_accelY;
        current_accelY = navX_value("accelY");
        last_timeY = current_timeY;
        current_timeY = getRuntime();
        velocityY += (int)((current_accelY+last_accelY)/2
                *(current_timeY-last_timeY)*inchesPerMeter*20);
        positionY = (float)(velocityY/20*current_timeY);
        return positionY;
    }

    float z_position() {
        last_accelZ = current_accelZ;
        current_accelZ = navX_value("accelZ");
        last_timeZ = current_timeZ;
        current_timeZ = getRuntime();
        velocityZ += (int)((current_accelZ+last_accelZ)/2
                *(current_timeZ-last_timeZ)*inchesPerMeter*20);
        positionZ = (float)(velocityZ/20*current_timeZ);
        return positionZ;
    }
}