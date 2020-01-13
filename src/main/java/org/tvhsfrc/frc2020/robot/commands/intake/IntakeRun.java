package org.tvhsfrc.frc2020.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;

import static org.tvhsfrc.frc2020.robot.RobotContainer.intake;

public class IntakeRun extends CommandBase {
    public IntakeRun() {
        addRequirements(intake);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        intake.IntakeRun();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
