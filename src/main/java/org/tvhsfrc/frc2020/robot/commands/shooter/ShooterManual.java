package org.tvhsfrc.frc2020.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.tvhsfrc.frc2020.robot.RobotContainer;

public class ShooterManual extends CommandBase {
    /**
     * String name of command for debugging
     */
    private static final String CommandName = "Shooter Manual";

    public ShooterManual()
    {
        addRequirements(RobotContainer.shooter);
    }

    /**
     * Called once when the command is initially scheduled.
     */
    @Override
    public void initialize() {
        System.out.println(CommandName + "command has been initialize");
    }

    /**
     * The main body of a command.  Called repeatedly while the command is scheduled.
     */
    @Override
    public void execute() {
        RobotContainer.shooter.shootAtJoystickSpeed();
    }

    /**
     * The action to take when the command ends.  Called when either the command finishes normally,
     * or when it interrupted/canceled.
     *
     * @param interrupted whether the command was interrupted/canceled
     */
    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            // Interrupted sequence
            System.out.println(CommandName + "command was interrupted... ):");
        }
        // End sequence
        System.out.println(CommandName + "command has ended...");
    }

    /**
     * Whether the command has finished.  Once a command finishes, the scheduler will call its
     * end() method and un-schedule it.
     *
     * @return whether the command has finished.
     */
    @Override
    public boolean isFinished() {
        return false;
    }
}