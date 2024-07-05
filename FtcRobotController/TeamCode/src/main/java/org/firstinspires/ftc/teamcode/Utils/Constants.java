package org.firstinspires.ftc.teamcode.Utils;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Constants {
    public static final double TICKS_PER_REVOLUTION_STEERING_MOTOR = 5;
    public static final double TICKS_PER_REVOLUTION_POWER_MOTOR = 5;
    public static final double MAX_SPEED = 5;
    public static Telemetry telemetry;

    public void setTelemetry(Telemetry telemetry){
        this.telemetry = telemetry;
    }
}
