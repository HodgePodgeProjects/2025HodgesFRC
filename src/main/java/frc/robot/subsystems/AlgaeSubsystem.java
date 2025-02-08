package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AlgaeSubsystem extends SubsystemBase {
    private final SparkFlex algaeMotor1 = new SparkFlex(Constants.OperatorConstants.kAlgaeMotorPort1, MotorType.kBrushless);
    private final SparkFlex algaeMotor2 = new SparkFlex(Constants.OperatorConstants.kAlgaeMotorPort2, MotorType.kBrushless);

    private static final double MOTOR_POWER = .15; 

    public AlgaeSubsystem() {

    }

    public void intakeAlgae() {
        algaeMotor1.set(MOTOR_POWER);
        algaeMotor2.set(-MOTOR_POWER);
    }
    public void outputAlgae() {
        algaeMotor1.set(-MOTOR_POWER);
        algaeMotor2.set(MOTOR_POWER);
    }

    public void stopMotor() {
        algaeMotor1.stopMotor();
        algaeMotor2.stopMotor();
    }

}
