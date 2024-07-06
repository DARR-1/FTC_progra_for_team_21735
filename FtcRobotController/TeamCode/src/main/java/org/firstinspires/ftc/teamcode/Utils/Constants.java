package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Constants {
    public static final double TICKS_PER_REVOLUTION_STEERING_MOTOR = 5;
    public static final double TICKS_PER_REVOLUTION_POWER_MOTOR = 5;
    public static final double MAX_SPEED = 5;

    // Linear OpMode classes
    public static Telemetry telemetry;

    public void setTelemetry(Telemetry telemetry){
        Constants.telemetry = telemetry;
    }

    public static HardwareMap hardwareMap;

    public void setHardwareMap(HardwareMap hardwareMap){
        Constants.hardwareMap = hardwareMap;
    }
}
