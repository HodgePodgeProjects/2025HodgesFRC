// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class RobotContainer {
  private final CommandXboxController m_driverController = new CommandXboxController(
      OperatorConstants.kDriverControllerPort);

  // Create a Shuffleboard tab
  private final ShuffleboardTab driverTab = Shuffleboard.getTab("Driver Controls");

  // Shuffleboard button states
  private final GenericEntry aButtonEntry = driverTab.add("A Button Pressed", false).getEntry();
  private final GenericEntry bButtonEntry = driverTab.add("B Button Pressed", false).getEntry();
  private final GenericEntry xButtonEntry = driverTab.add("X Button Pressed", false).getEntry();
  private final GenericEntry yButtonEntry = driverTab.add("Y Button Pressed", false).getEntry();

  public RobotContainer() {
    configureBindings();
    startButtonUpdater();
    setupCamera();
  }

  private void configureBindings() {

  }

  private void startButtonUpdater() {
    new Thread(() -> {
      while (true) {
        // Continuously update Shuffleboard button states
        aButtonEntry.setBoolean(m_driverController.a().getAsBoolean());
        bButtonEntry.setBoolean(m_driverController.b().getAsBoolean());
        xButtonEntry.setBoolean(m_driverController.x().getAsBoolean());
        yButtonEntry.setBoolean(m_driverController.y().getAsBoolean());

        // Sleep for a short duration to prevent overloading CPU
        Timer.delay(0.05); // 50ms delay
      }
    }).start();
  }

  private void setupCamera() {
    UsbCamera camera = CameraServer.startAutomaticCapture();
    camera.setResolution(320, 240);
    camera.setFPS(15);

    // driverTab
    //     .add("Driver Cam", CameraServer.getServer().getSource()) // Use camera source directly
    //     .withWidget("Camera Stream")
    //     .withSize(16, 16);

  }
}
