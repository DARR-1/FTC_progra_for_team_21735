package org.firstinspires.ftc.teamcode.subsystems.mecanum;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.motion.kinematics.ModuleState;

public class MecanumModule {
    public DcMotor power_motor;
    public MecanumModule(DcMotor power_motor){
        this.power_motor = power_motor;

        resetEncoder();
    }

    public ModuleState getState(){
        ModuleState state = new ModuleState(0, power_motor.getPower());
        return state;
    }
    public void resetEncoder(){
        power_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    // Stops the motors
    public void stop(){
        power_motor.setPower(0);
    }

    public void setDesiredState(ModuleState state){
        if (Math.abs(state.speed) < 0.09){
            stop();
            return;
        }

        double power = state.speed / Constants.MAX_SPEED;
        power_motor.setPower(power);

    }
}
