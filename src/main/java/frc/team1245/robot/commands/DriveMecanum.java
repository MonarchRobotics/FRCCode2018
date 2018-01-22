package frc.team1245.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.team1245.robot.OI;
import frc.team1245.robot.Robot;
import frc.team1245.robot.RobotMap;

public class DriveMecanum extends Command {
    public DriveMecanum(){
        requires(Robot.drivetrain);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void execute(){
        // Get joystick input and filter it through the dead zone function
        double y = OI.scaleSpeed(-OI.deadZone(OI.driverPad.getX(GenericHID.Hand.kLeft), RobotMap.getTranslationalDeadZone()), 1);
        double x = OI.scaleSpeed(-OI.deadZone(OI.driverPad.getY(GenericHID.Hand.kLeft), RobotMap.getTranslationalDeadZone()), 1);
        double twist = OI.scaleSpeed(OI.deadZone(OI.driverPad.getX(GenericHID.Hand.kRight), RobotMap.getRotationalDeadZone()), 1);

        // Drive the robot based on the user input
        Robot.drivetrain.getDrivetrain().driveCartesian(x,y,twist,0);
    }
}
