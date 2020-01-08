package org.tvhsfrc.frc2020.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.tvhsfrc.frc2020.robot.subsystems.ExampleSubsystem;

public class Drive extends CommandBase {

    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField", "FieldCanBeLocal"})
    private final org.tvhsfrc.frc2020.robot.subsystems.Drive subsystem;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public Drive(org.tvhsfrc.frc2020.robot.subsystems.Drive subsystem)
    {
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }
}
