package org.firstinspires.ftc.teamcode.subsystems.mecanum;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.motion.kinematics.MecanumDriveKinematics;
import org.firstinspires.ftc.teamcode.motion.kinematics.ModuleState;
import org.firstinspires.ftc.teamcode.motion.kinematics.SwerveDriveKinematics;

public class MecanumSubsystem {
    final MecanumModule FL = new MecanumModule(Constants.hardwareMap.dcMotor.get("FL"));
    final MecanumModule FR = new MecanumModule(Constants.hardwareMap.dcMotor.get("FR"));
    final MecanumModule BL = new MecanumModule(Constants.hardwareMap.dcMotor.get("BL"));
    final MecanumModule BR = new MecanumModule(Constants.hardwareMap.dcMotor.get("BR"));

    final IMU imu = Constants.hardwareMap.get(IMU.class, "imu");
    public MecanumSubsystem() {
        final IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));

        imu.initialize(parameters);
    }

    public void zeroHeading(){
        imu.resetYaw();
    }

    public double getHeading(){
        double angle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        return angle;
    }

    public void periodic(){
    
    }

    public void stopModules(){
        FL.stop();
        FR.stop();
        BL.stop();
        BR.stop();
    }

    public void setModulesStates(ModuleState[] states){
        MecanumDriveKinematics.normalize(states, Constants.MAX_SPEED);
        FL.setDesiredState(states[0]);
        FR.setDesiredState(states[1]);
        BL.setDesiredState(states[2]);
        BR.setDesiredState(states[3]);
    }
}
