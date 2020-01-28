package org.tvhsfrc.frc2020.robot.commands.swerveDrive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static org.tvhsfrc.frc2020.robot.RobotContainer.swerveDrive;

public class SwerveDrive extends CommandBase {
  public SwerveDrive(){
    addRequirements(swerveDrive);
  }


    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
      swerveDrive.SwerveRun();
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
