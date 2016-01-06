package com.qualcomm.ftcrobotcontroller.opmodes;

public class TuleVariables extends TuleNavX {
	
	public TuleVariables() {
		
	}

	final double COUNTS_PER_REVOLUTION_N40 = 1120;
    final double COUNTS_PER_REVOLUTION_N60 = 1680;
    final double GEAR_RATIO_DRIVE = 2;
    final double GEAR_RATIO_ARM = 1;
    final double GEAR_RATIO_DUMP = 3;
    final double GEAR_RATIO_PIVOT = 20;
    final double GEARS_PER_INCH_ARM = 4;
    final double WHEEL_DIAMETER = 5;
    final double TURN_DIAMETER = 18;

    final double COUNTS_PER_INCH_DRIVE =
            COUNTS_PER_REVOLUTION_N60*GEAR_RATIO_DRIVE/(WHEEL_DIAMETER*Math.PI);
    final double COUNTS_PER_INCH_ARM =
            COUNTS_PER_REVOLUTION_N40*GEAR_RATIO_ARM/GEARS_PER_INCH_ARM;

    final double COUNTS_PER_DEGREE_DRIVE =
            (COUNTS_PER_INCH_DRIVE*Math.PI*TURN_DIAMETER)/360;
    final double COUNTS_PER_DEGREE_DUMP =
            (COUNTS_PER_REVOLUTION_N60*GEAR_RATIO_DUMP)/360;
    final double COUNTS_PER_DEGREE_PIVOT =
            (COUNTS_PER_REVOLUTION_N60*GEAR_RATIO_PIVOT)/360;
	
	final float inchesPerMeter = (float)(100/2.54);
	
	int state = 0;

	float positionX = 0.0f;
	float positionY = 0.0f;
	float positionZ = 0.0f;

	float velocityX = 0.0f;
	float velocityY = 0.0f;
	float velocityZ = 0.0f;

	float current_accelX = 0.0f;
	float last_accelX = 0.0f;
	float current_accelY = 0.0f;
	float last_accelY = 0.0f;
	float current_accelZ = 0.0f;
	float last_accelZ = 0.0f;

	double current_timeX = 0.0f;
	double last_timeX = 0.0f;
	double current_timeY = 0.0f;
	double last_timeY = 0.0f;
	double current_timeZ = 0.0f;
	double last_timeZ = 0.0f;
}