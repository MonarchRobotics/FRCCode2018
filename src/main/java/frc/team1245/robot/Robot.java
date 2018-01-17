package frc.team1245.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team1245.robot.subsystems.Drivetrain;
import frc.team1245.robot.subsystems.Gripper;
import org.opencv.core.Mat;


public class Robot extends IterativeRobot{
    public static OI oi;
    public static Drivetrain drivetrain = new Drivetrain(RobotMap.getFrontLeft(), RobotMap.getFrontRight(),
            RobotMap.getRearLeft(), RobotMap.getRearRight());
    public static Gripper gripper = new Gripper();
    private Command autonomousCommand;

    private Thread visionThread;
    private Mat mat;
    private UsbCamera grabberCamera;
    private MjpegServer outputServer;
    private CvSink cvSink;

    //Spin up the bot!
    @Override
    public void robotInit(){
        //Initialize the input/output
        oi = new OI();
        //Initialize camera stuff
        mat = new Mat();
        grabberCamera = new UsbCamera("Grabber Raw", 0);
        grabberCamera.setResolution(640,480);

        CameraServer.getInstance().addCamera(grabberCamera);

        cvSink = CameraServer.getInstance().getVideo();

        visionThread = new Thread(() -> {
            while(!Thread.interrupted()){
                RobotMap.getCameraOutputStream().putFrame(mat);
            }
        });
        visionThread.setDaemon(true);
        visionThread.start();
    }

    @Override
    public void disabledPeriodic(){
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit(){
        autonomousCommand = null; //Insert autonomous command here
        if (autonomousCommand != null){
            autonomousCommand.start();
        }
    }

    @Override
    public void autonomousPeriodic(){
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit(){
        //cancels autonomous command when teleop period starts
        if (autonomousCommand != null){
            autonomousCommand.cancel();
        }
    }

    @Override
    public void disabledInit(){
        //called when you disable the bot
        //maybe at the end of the match?

    }

    @Override
    public void teleopPeriodic(){
        //called periodically during teleop
        Scheduler.getInstance().run();
    }

    @Override
    public void testPeriodic(){
        // this is called.... during the test mode!
        //LiveWindow.run(); and this is deprecated and we don't use test mode
    }
}
