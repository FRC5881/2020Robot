package org.tvhsfrc.frc2020.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Intake extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField", "FieldCanBeLocal"})
    private final Intake subsystem;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public Intake(Intake subsystem) {
        this.subsystem = subsystem;
    }

    @Override
    public void execute(){

    }
}
