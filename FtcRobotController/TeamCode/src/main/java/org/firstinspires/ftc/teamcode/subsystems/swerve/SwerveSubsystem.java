package org.firstinspires.ftc.teamcode.subsystems.swerve;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.motion.kinematics.ModuleState;
import org.firstinspires.ftc.teamcode.motion.kinematics.SwerveDriveKinematics;

public class SwerveSubsystem {
    final SwerveModule FL = new SwerveModule(
            Constants.hardwareMap.dcMotor.get("FLPwr"), Constants.hardwareMap.dcMotor.get("FLStr"));
    final SwerveModule FR = new SwerveModule(
            Constants.hardwareMap.dcMotor.get("FRPwr"), Constants.hardwareMap.dcMotor.get("FRStr"));
    final SwerveModule BL = new SwerveModule(
            Constants.hardwareMap.dcMotor.get("BLPwr"), Constants.hardwareMap.dcMotor.get("BLStr"));
    final SwerveModule BR = new SwerveModule(
            Constants.hardwareMap.dcMotor.get("BRPwr"), Constants.hardwareMap.dcMotor.get("BRStr"));

    final IMU imu = Constants.hardwareMap.get(IMU.class, "imu");
    public SwerveSubsystem() {
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
        SwerveDriveKinematics.normalize(states, Constants.MAX_SPEED);
        FL.setDesiredState(states[0]);
        FR.setDesiredState(states[1]);
        BL.setDesiredState(states[2]);
        BR.setDesiredState(states[3]);
    }
}
