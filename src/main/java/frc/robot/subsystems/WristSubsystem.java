package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WristSubsystem extends SubsystemBase {
    private final SparkMax wristMotor = new SparkMax(Constants.OperatorConstants.kWristMotorPort, MotorType.kBrushed);
    private final RelativeEncoder wristEncoder = wristMotor.getEncoder();

    private static final double ENCODER_CPR = 2; // Encoder gives 2 ticks per 360° rotation
    private static final double DEGREES_PER_TICK = 360.0 / ENCODER_CPR; // Each tick = 180°
    private static final double TARGET_ANGLE_DEGREES = -90.0;
    private static final double TARGET_TICKS = TARGET_ANGLE_DEGREES / DEGREES_PER_TICK; // -90° → -0.5 ticks
    private static final double MOTOR_POWER = 0.25; // Reduce speed to prevent overshoot
    private double currentTicks;
    private boolean rotated = false; // Tracks current state
    private boolean needToRotate = false;
    private SparkMaxConfig config;

    public WristSubsystem() {
        // Initialize configuration object
        config = new SparkMaxConfig();

        // Set Brake Mode in Configuration
        config.idleMode(SparkBaseConfig.IdleMode.kBrake); // Force Brake Mode
        // config.softLimit.forwardSoftLimitEnabled(true);
        // config.softLimit.reverseSoftLimitEnabled(true);
        // config.softLimit.forwardSoftLimit(0.1);
        // config.softLimit.reverseSoftLimit(-.6);
        // Apply Configuration to Motor
        wristMotor.configure(
                config,
                SparkBase.ResetMode.kResetSafeParameters,
                SparkBase.PersistMode.kPersistParameters);
        wristEncoder.setPosition(0);
    }

    public void toggleRotation() {
        needToRotate = true;
        rotated = !rotated; // Toggle rotation
    }

    @Override
    public void periodic() {
        currentTicks = wristEncoder.getPosition();
        double targetPosition = rotated ? TARGET_TICKS : 0;
        // System.out.println("Brake mode: " + wristMotor.configAccessor.getIdleMode());

        // System.out.println("Encoder Position: " + currentTicks + " | Target: " + targetPosition);

        if (rotated && currentTicks <= TARGET_TICKS) {
        wristMotor.set(-0.1);
        needToRotate = false;// Stop motor if it has reached or exceeded the target
        } else if (!rotated && currentTicks >= -0.05) {
        wristMotor.stopMotor();
        needToRotate = false;// Stop motor if it has returned to zero
        } else if (currentTicks > targetPosition && needToRotate) {
        wristMotor.set(-MOTOR_POWER); // Move forward
        } else if (needToRotate) {
        wristMotor.set(MOTOR_POWER*.75); // Move backward
        }
    }

}
