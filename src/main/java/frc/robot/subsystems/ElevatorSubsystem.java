package frc.robot.subsystems;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
    private final SparkMax elevatorMotor;

    public ElevatorSubsystem(int motorPort) {
        elevatorMotor = new SparkMax(motorPort, MotorType.kBrushless);
    }

    public void moveUp() {
        elevatorMotor.set(0.5); // Set speed for upward movement
    }

    public void moveDown() {
        elevatorMotor.set(-0.5); // Set speed for downward movement
    }

    public void stop() {
        elevatorMotor.set(0.0); // Stop the motor
    }
}