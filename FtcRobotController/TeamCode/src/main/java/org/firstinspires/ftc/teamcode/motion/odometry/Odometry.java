package org.firstinspires.ftc.teamcode.motion.odometry;

import org.firstinspires.ftc.teamcode.motion.coords_system.Pose2D;
import org.firstinspires.ftc.teamcode.motion.coords_system.Translation2D;
import org.firstinspires.ftc.teamcode.motion.kinematics.SwerveDriveKinematics;

public class Odometry {
    SwerveDriveKinematics kinematics;
    double gyroAngle;
    Pose2D pose2D;
    double angle_offset;
    ModulePosition[] modulePositions;

    WheelPosition wheelPositions;
    public Odometry(SwerveDriveKinematics kinematics, double gyroAngle, Pose2D pose2D, ModulePosition[] modulePositions) {
        this.kinematics = kinematics;
        this.gyroAngle = gyroAngle;
        this.pose2D = pose2D;
        this.modulePositions = modulePositions;

        angle_offset = pose2D.getAngle() - gyroAngle;
    }

    public Pose2D getPoseMeters(){
        return pose2D;
    }

    public void resetPosition(Pose2D pose, double gyroAngle){
        angle_offset = pose.getAngle() - gyroAngle;
        pose2D = pose;
        pose2D.setAngle(pose.getAngle() + angle_offset);
    }

    public void update(double gyroAngle, ModulePosition[] modulePositions){
        double angle = gyroAngle - angle_offset;
        WheelPosition[] wheelPositions = new WheelPosition[modulePositions.length];
        for (int i = 0; i < modulePositions.length; i++){
            double wheel_x = pose2D.getPosition().x + kinematics.getSwerveModulesPos()[i].x;
            double wheel_y = pose2D.getPosition().y + kinematics.getSwerveModulesPos()[i].y;
            Pose2D wheel_position = new Pose2D(new Translation2D(wheel_x, wheel_y), angle);
            wheelPositions[i] = new WheelPosition(pose2D);
        }


        //in relation to the center of the robot
        double wheel_distance = Math.sqrt(
                Math.pow(kinematics.getSwerveModulesPos()[0].x, 2)
                        + Math.pow(kinematics.getSwerveModulesPos()[0].x, 2));
        double original_wheel_angle = Math.asin(
                kinematics.getSwerveModulesPos()[0].y / wheel_distance);

        double robot_x = wheelPositions[0].getPose().getPosition().x * Math.sin(original_wheel_angle + angle);
        double robot_y = wheelPositions[0].getPose().getPosition().y * Math.cos(original_wheel_angle + angle);
        pose2D = new Pose2D(new Translation2D(robot_x, robot_y), angle);
    }


}
