package org.firstinspires.ftc.teamcode.motion.kinematics;

import org.firstinspires.ftc.teamcode.motion.coords_system.Translation2D;

public class MecanumDriveKinematics {
    Translation2D[] modules_pos;
    public MecanumDriveKinematics(Translation2D... modules_pos){
        if (modules_pos.length != 4){
            throw new IllegalArgumentException("The number of modules defined must be 4.");
        }

        this.modules_pos = modules_pos;
    }

    public ModuleState[] toMecanumModuleStates(ChassisSpeeds speeds){
        ModuleState[] states = new ModuleState[4];

        double denominator = Math.max(Math.abs(speeds.vy) + Math.abs(speeds.vx) + Math.abs(speeds.vz), 1);
        double fl_power = (speeds.vy + speeds.vx + speeds.vz) / denominator;
        double fr_power = (speeds.vy - speeds.vx - speeds.vz) / denominator;
        double bl_power = (speeds.vy - speeds.vx + speeds.vz) / denominator;
        double br_power = (speeds.vy + speeds.vx - speeds.vz) / denominator;

        states[0] = new ModuleState(-45, fl_power);
        states[1] = new ModuleState(45, fr_power);
        states[2] = new ModuleState(45, bl_power);
        states[3] = new ModuleState(-45, br_power);

        return states;
    }

    public static void normalize(ModuleState[] states, double max_speed) {
        double[] powers = new double[states.length];
        for (int i = 1; i <= states.length; i++) {
            powers[i - 1] = states[i - 1].speed;
        }

        double max = powers[0];
        for (int i = 1; i <= powers.length; i++) {
            max = Math.max(max, powers[i - 1]);
        }

        if (max > max_speed) {
            for (int i = 1; i <= powers.length; i++) {
                states[i - 1].speed /= max;
            }
        }
    }
}
