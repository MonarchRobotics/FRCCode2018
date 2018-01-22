package frc.team1245.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.team1245.robot.Robot;

public class AutoDrive extends Command {

    private Timer timer;
    private double duration;

    private double x, y;

    public AutoDrive(){
        new AutoDrive(0, 0, 0);
    }

    public AutoDrive(double x, double y, double time){
        requires(Robot.drivetrain);

        this.duration = time;
        this.x = x;
        this.y = y;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
