package org.firstinspires.ftc.teamcode.motion.coords_system;

public class Pose2D{
    Translation2D position;
    double angle;

    public Pose2D(Translation2D position, double angle){
        this.position = position;
        this.angle = angle;
    }

    public double getAngle(){
        return  angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Translation2D getPosition(){
        return position;
    }

    public void setPosition(Translation2D position) {
        this.position = position;
    }
}
