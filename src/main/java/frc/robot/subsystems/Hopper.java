// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import frc.robot.subsystems.ColorSensor;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HopperConstants;

public class Hopper extends SubsystemBase {
  /** Creates a new Hopper. */
  CANSparkMax m_hopperMotor;
  ColorSensor color;
  private final Timer m_timer;
  
  public Hopper(ColorSensor color) {
    this.color = color;
    m_hopperMotor = new CANSparkMax(HopperConstants.hopperPort, MotorType.kBrushed);
    m_timer = new Timer();
  }
  

  public void setHopper(double speed) {
    m_hopperMotor.set(speed);
    SmartDashboard.putNumber("Hopper", m_hopperMotor.getOutputCurrent());
    /*
    if (color.readColor().equals("Blue")) {
      m_hopperMotor.set(-HopperConstants.hopperSpeed);
      */
    }
    public void startHopper() {
      m_hopperMotor.set(HopperConstants.hopperSpeed);
      SmartDashboard.putNumber("Hopper", m_hopperMotor.getOutputCurrent());
      }
  public void stopHopper() {
    m_hopperMotor.set(0);
  }
  public void setHopperTime(double seconds) {
    m_timer.reset();
    m_timer.start();

    while(m_timer.get() < seconds) {
      setHopper(0);
    }
    m_timer.stop();
    m_hopperMotor.stopMotor();
  }
  public void stopHopperMotor() {
    m_hopperMotor.stopMotor();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
