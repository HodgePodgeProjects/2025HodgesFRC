// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import frc.robot.Constants.OperatorConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SparkFlexSubsystem extends SubsystemBase {

  private SparkFlex sparkFlexMotor;

  /** Creates a new ExampleSubsystem. */
  public SparkFlexSubsystem() {
    sparkFlexMotor = new SparkFlex(OperatorConstants.sparkFlexMotorPort, MotorType.kBrushless);
  }

  public void intake() {
    sparkFlexMotor.set(.25);
  }
  public void out() {
    sparkFlexMotor.set(-.25);
  }
  public void stop() {
    sparkFlexMotor.set(0.0);
  }

}
