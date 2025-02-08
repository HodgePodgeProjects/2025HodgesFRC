package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;



import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CoralSubsystem extends SubsystemBase {
    private final SparkFlex coralMotor = new SparkFlex(Constants.OperatorConstants.kCoralMotorPort, MotorType.kBrushless);

    private static final double MOTOR_POWER = .25; 

    public CoralSubsystem() {

    }

    public void intakeCoral() {
        System.out.println("Getting here 2222");
        coralMotor.set(MOTOR_POWER);
    }
    public void stopMotor() {
        coralMotor.stopMotor();
    }

}
