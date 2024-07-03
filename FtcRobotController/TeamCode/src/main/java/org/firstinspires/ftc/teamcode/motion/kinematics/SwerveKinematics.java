package org.firstinspires.ftc.teamcode.motion.kinematics;

import org.firstinspires.ftc.teamcode.motion.coords_system.Translation2D;

public class SwerveKinematics {
    public Translation2D[] swerve_modules_pos;
    public SwerveKinematics(Translation2D... swerve_modules_pos){
        if (swerve_modules_pos.length < 2){
            throw new IllegalArgumentException("The minimum number of swerves defined mustn't be less than 2.");
        }

        this.swerve_modules_pos = swerve_modules_pos;
    }

    public ModuleState[] toSwerveModuleStates(ChassisSpeeds speeds){
        ModuleState[] states = new ModuleState[swerve_modules_pos.length];
        double[] velocity = {0, 0};
        double power;
        double steering;

        for (int i = 1; i <= swerve_modules_pos.length; i++) {
            velocity[0] = speeds.vx + Math.sin(2 * Math.PI / swerve_modules_pos.length * i) * speeds.vz;
            velocity[1] = speeds.vy + Math.cos(2 * Math.PI / swerve_modules_pos.length * i) * speeds.vz;
            power = Math.sqrt(Math.pow(velocity[0], 2) + Math.pow(velocity[1], 2));
            power = (velocity[0] / power < 0) ? -power : power;
            steering = Math.asin(velocity[1]/power);

            states[i-1] = new ModuleState(steering, power);
        }

        return states;
    }

        public void normalizeModules(ModuleState[] states, double max_speed){
            double[] powers = new double[states.length];
            for (int i = 1; i <= states.length; i++) {
                powers[i-1] = states[i-1].power;
            }

            double max = powers[0];
            for (int i = 1; i <= powers.length; i++) {
                max = Math.max(max, powers[i-1]);
            }

            if (max > max_speed){
                for (int i = 1; i <= powers.length; i++) {
                    states[i-1].power /= max;
                }
            }
        }

}
