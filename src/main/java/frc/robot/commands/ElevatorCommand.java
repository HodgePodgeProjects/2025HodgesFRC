package frc.robot.commands;

import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorCommand extends Command {
    private final ElevatorSubsystem elevatorSubsystem;
    private final XboxController controller;

    public ElevatorCommand(ElevatorSubsystem elevatorSubsystem, XboxController controller) {
        this.elevatorSubsystem = elevatorSubsystem;
        this.controller = controller;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void execute() {
        if (controller.getAButton()) {
            elevatorSubsystem.moveUp();
        } else if (controller.getBButton()) {
            elevatorSubsystem.moveDown();
        } else {
            elevatorSubsystem.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false; // Run until interrupted
    }
}