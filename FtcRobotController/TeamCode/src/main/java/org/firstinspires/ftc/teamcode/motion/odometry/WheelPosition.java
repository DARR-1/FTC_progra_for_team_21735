package org.firstinspires.ftc.teamcode.motion.odometry;

import org.firstinspires.ftc.teamcode.motion.coords_system.Pose2D;
import org.firstinspires.ftc.teamcode.motion.coords_system.Translation2D;

public class WheelPosition {
        Pose2D pose;
        double last_power_position =  0;

        public WheelPosition(Pose2D intitialPose){
            pose = intitialPose;
        }

    public Pose2D getPose() {
        return pose;
    }

    public Pose2D updatePosition(ModulePosition modulePosition, double gyroAngle){
            double angle = modulePosition.steering + gyroAngle;
            double distance = modulePosition.power - last_power_position;

            double new_x = distance * Math.sin(angle);
            double new_y = distance * Math.cos(angle);
            Pose2D new_position = new Pose2D(new Translation2D(new_x, new_y), angle);

            last_power_position = modulePosition.power;

            return new_position;
        }
}
