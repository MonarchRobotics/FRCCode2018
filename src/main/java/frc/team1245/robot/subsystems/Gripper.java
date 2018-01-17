package frc.team1245.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.team1245.robot.commands.ZanathyrsGrasp;

public class Gripper extends Subsystem{

    public WPI_TalonSRX leftArmOfZanathyr, rightArmOfZanathyr;

    public Gripper(int leftArm, int rightArm) {
        this.leftArmOfZanathyr = new WPI_TalonSRX(leftArm);
        this.rightArmOfZanathyr = new WPI_TalonSRX(rightArm);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ZanathyrsGrasp());
    }
}
