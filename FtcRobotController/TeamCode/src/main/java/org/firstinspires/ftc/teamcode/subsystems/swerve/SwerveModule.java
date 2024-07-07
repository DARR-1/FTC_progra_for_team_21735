package org.firstinspires.ftc.teamcode.subsystems.swerve;

import android.media.midi.MidiOutputPort;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.motion.kinematics.ModuleState;
import org.firstinspires.ftc.teamcode.motion.odometry.ModulePosition;

public class SwerveModule {
    public DcMotor steering_motor;
    public DcMotor power_motor;
    double steering_position;
    public SwerveModule(DcMotor steering_motor, DcMotor power_motor){
        this.steering_motor = steering_motor;
        this.power_motor = power_motor;

        resetEncoder();
    }

    public ModuleState getState(){
        ModuleState state = new ModuleState(
                steering_motor.getCurrentPosition() * Constants.TICKS_PER_REVOLUTION_STEERING_MOTOR /
                        (2 * Math.PI), power_motor.getPower() * Constants.MAX_SPEED);
        return state;
    }

    public ModulePosition getModulePosition(){
        double steering_position = steering_motor.getCurrentPosition() * Constants.TICKS_PER_REVOLUTION_STEERING_MOTOR / (2 * Math.PI);
        double power_position = power_motor.getCurrentPosition() * Constants.METERS_PER_TICK;

        return new ModulePosition(steering_position, power_position);
    }
    public void resetEncoder(){
        steering_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        steering_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        power_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    // Stops the motors
    public void stop(){
        power_motor.setPower(0);
        steering_motor.setPower(0);
    }

    public void inmediateStop(){
        power_motor.setPower(-power_motor.getPower());
        steering_motor.setPower(-steering_motor.getPower());
        power_motor.setPower(0);
        steering_motor.setPower(0);
    }

    public void setDesiredState(ModuleState state){
        if (Math.abs(state.speed) < 0.09){
            stop();
            return;
        }

        steering_position = steering_motor.getCurrentPosition() * Constants.TICKS_PER_REVOLUTION_STEERING_MOTOR / (2 * Math.PI);
        state.speed *= Math.cos((state.steering - steering_position));
        double power = Constants.MAX_SPEED / state.speed;
        power_motor.setPower(power);

        int target = (int)Math.round(state.steering * Constants.TICKS_PER_REVOLUTION_STEERING_MOTOR / (2 * Math.PI));
        steering_motor.setTargetPosition(target);
        steering_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        steering_motor.setPower(1);
    }
}
