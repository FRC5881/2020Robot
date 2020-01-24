package org.tvhsfrc.frc2020.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import org.tvhsfrc.frc2020.robot.RobotContainer;

import java.util.*;

public class SwerveDrive extends SubsystemBase {
    RobotContainer robotContainer = new RobotContainer();

    private List<Trajectory.State> states = new ArrayList<Trajectory.State>();


    // TODO: Figure out what the actual values for these things are
    private Translation2d translation2d = new Translation2d(robotContainer.joystick.getX(),robotContainer.joystick.getY());
    private Rotation2d rotation2d = new Rotation2d(robotContainer.joystick.getTwist());
    private Pose2d pose2d = new Pose2d(translation2d, rotation2d);
    private TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(10, 5);
    private ProfiledPIDController profiledPIDController = new ProfiledPIDController(.8,.3,.1,constraints);
    private Trajectory trajectory = new Trajectory(states);
    private PIDController pidControllerX = new PIDController(1,1,1);
    private PIDController pidControllerY = new PIDController(1,1,1);

    // TODO: figure out what the "channel" the sparks are on
    public CANSparkMax swerve1 = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
    public CANSparkMax swerve2 = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
    public CANSparkMax swerve3 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
    public CANSparkMax swerve4 = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);

    public SwerveDrive() {
        super();
    }

    public void SwerveBoiDrive(){
        SwerveControllerCommand swerveControllerCommand = new SwerveControllerCommand(trajectory,pose2d,pidControllerX,pidControllerY,profiledPIDController);
    }
}
