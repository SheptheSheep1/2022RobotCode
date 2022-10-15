// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  //private Drivetrain drive;
  private RobotContainer m_robotContainer;
  //private double startTime = Timer.getFPGATimestamp();
  //private double time;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    //m_autonomousCommand = new DeprecatedAuto()
    m_robotContainer = new RobotContainer();
   // drive = new Drivetrain();
    RobotContainer.m_drivetrain.m_field.setRobotPose(m_robotContainer.trajectories.get(m_robotContainer.m_numChooser.getSelected()).getXMeters(), 
   m_robotContainer.trajectories.get(m_robotContainer.m_numChooser.getSelected()).getYMeters(), 
   m_robotContainer.trajectories.get(m_robotContainer.m_numChooser.getSelected()).getRotation2d());
   SmartDashboard.putNumber("XBOX leftY", -m_robotContainer.m_driverController.getLeftY());
    SmartDashboard.putNumber("XBOX leftX", m_robotContainer.m_driverController.getLeftX());
    SmartDashboard.putNumber("XBOX rightY", -m_robotContainer.m_driverController.getRightY());
    SmartDashboard.putNumber("XBOX rightX", m_robotContainer.m_driverController.getRightX());
    SmartDashboard.putNumber("PoseX", RobotContainer.m_drivetrain.m_field.getRobotObject().getPose().getX());
    SmartDashboard.putNumber("PoseY", RobotContainer.m_drivetrain.m_field.getRobotObject().getPose().getY());
    SmartDashboard.putNumber("PoseSIN", RobotContainer.m_drivetrain.m_field.getRobotObject().getPose().getRotation().getSin());
    SmartDashboard.putNumber("PoseCOS", RobotContainer.m_drivetrain.m_field.getRobotObject().getPose().getRotation().getCos());
    SmartDashboard.putNumber("PoseTAN", RobotContainer.m_drivetrain.m_field.getRobotObject().getPose().getRotation().getTan());
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    m_robotContainer.rumbleController();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    //Set robot pose to coordinates specified in pathweave
    //startTime = Timer.getFPGATimestamp();
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
   //m_autonomousCommand = new Autonomous(m_robotContainer.m_drivetrain, m_robotContainer.m_shooter, m_robotContainer.m_hopper);
//m_autonomousCommand = new DeprecatedAuto(m_robotContainer.m_drivetrain);
//m_autonomousCommand = new RunCommand(() -> m_robotContainer.m_drivetrain.driveTime(2, -.3), m_robotContainer.m_drivetrain);
    // schedule the autonomous command (example)
    
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    
    /*
    if (Timer.getFPGATimestamp - time < 2) {
      m_robotContainer.m_drivetrain.m_leftMaster.set(.3);
      m_robotContainer.m_drivetrain.m_rightMaster.set(.3);
    } else {
      m_robotContainer.m_drivetrain.m_leftMaster.set(0);
      m_robotContainer.m_drivetrain.m_rightMaster.set(0);
      //m_robotContainer.m_shooter.autoShoot();
    }
    */
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    

    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
