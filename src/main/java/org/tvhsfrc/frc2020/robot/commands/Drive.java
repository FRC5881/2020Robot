package org.tvhsfrc.frc2020.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * This will have all the "actual" code to make the swerve drives work at some point.
 */
public class Drive extends CommandBase {

    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField", "FieldCanBeLocal"})
    private final org.tvhsfrc.frc2020.robot.subsystems.Drive subsystem;

    /**
     * Creates a new Drive.
     *
     * @param subsystem The subsystem used by this command.
     */
    public Drive(org.tvhsfrc.frc2020.robot.subsystems.Drive subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    /**
     * Everytime drive is run execute is run as well
     */
    @Override
    public void execute() {
    }
}
