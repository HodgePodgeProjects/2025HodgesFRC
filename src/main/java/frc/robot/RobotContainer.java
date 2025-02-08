// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.WristSubsystem;
import frc.robot.subsystems.AlgaeSubsystem;
import frc.robot.subsystems.CoralSubsystem;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.StartEndCommand;

public class RobotContainer {
  private final CommandXboxController m_driverController = new CommandXboxController(
      OperatorConstants.kDriverControllerPort);

  private final WristSubsystem wristSubsystem = new WristSubsystem();
  private final CoralSubsystem coralSubsystem = new CoralSubsystem();
  private final AlgaeSubsystem algaeSubsystem = new AlgaeSubsystem();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {

    m_driverController.a().whileTrue(new StartEndCommand(
        wristSubsystem::toggleRotation,
        () -> {}, 
        wristSubsystem));

    m_driverController.b().whileTrue(new StartEndCommand(
        coralSubsystem::intakeCoral,
        coralSubsystem::stopMotor,
        coralSubsystem));

    m_driverController.x().whileTrue(new StartEndCommand(
        algaeSubsystem::intakeAlgae,
        algaeSubsystem::stopMotor,
        algaeSubsystem));
    m_driverController.y().whileTrue(new StartEndCommand(
        algaeSubsystem::outputAlgae,
        algaeSubsystem::stopMotor,
        algaeSubsystem));

    // SmartDashboard.putBoolean("Joystick B value", bButton.getAsBoolean());
  }
}
