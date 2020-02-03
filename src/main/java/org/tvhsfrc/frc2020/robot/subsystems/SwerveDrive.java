package org.tvhsfrc.frc2020.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import org.tvhsfrc.frc2020.robot.RobotContainer;

import java.util.*;
import java.util.function.Supplier;

public class SwerveDrive extends SubsystemBase {
    RobotContainer robotContainer = new RobotContainer();

    // TODO: Figure out what the actual values for these things are

    // TODO: Set actual states, translation locations, and rotation amounts for the robot
    // The rotation of the robot
    private Rotation2d rotation2d2 = new Rotation2d(100);
    // The "pose/ location and rotation" of the robot for trajectory
    private Pose2d pose2d2 = new Pose2d(10,10,rotation2d2);
    // The time, velocity, acceleration, pose, and curvature of the robot
    private Trajectory.State states = new Trajectory.State(1,10,2,pose2d2,1);

    // The coordinates of the robot relative to the field. Based off the x and y value of the joystick
    private Translation2d translation2d = new Translation2d(robotContainer.joystick.getX(),robotContainer.joystick.getY());
    // The rotation of the robot relative to the field. Based off the "twist" of the joystick
    private Rotation2d rotation2d = new Rotation2d(robotContainer.joystick.getTwist());
    // The "pose" (combined value for translation and rotation of robot)
    private Supplier<Pose2d> pose2d = () -> null;
    // The maximum velocity and maximum acceleration of the robot.
    private TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(10, 5);

    // PID for robot.
    private ProfiledPIDController profiledPIDController = new ProfiledPIDController(.8,.3,.1,constraints);
    // Array list of trajectory states
    ArrayList<Trajectory.State> list;

    public void setList(ArrayList<Trajectory.State> list) {
        this.list = list;
    }

    // Trajectory of robot based off of preset values
    private Trajectory trajectory = new Trajectory(list);
    // Swerve drive kinematics (a translation2d with the distance from the wheels to the center)
    private SwerveDriveKinematics swerveDriveKinematics = new SwerveDriveKinematics(translation2d);
    // PID controller for x axis
    private PIDController pidControllerX = new PIDController(1,1,1);
    // PID controller for y axis
    private PIDController pidControllerY = new PIDController(1,1,1);
    // Swerve module state (speed and rotation of the robot)
    private SwerveModuleState swerveModuleState = new SwerveModuleState(10,rotation2d);

    // TODO: figure out what the "channel" the sparks are on
    public CANSparkMax swerve1 = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
    public CANSparkMax swerve2 = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
    public CANSparkMax swerve3 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
    public CANSparkMax swerve4 = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);

    public SwerveDrive() {
        super();
        list = new ArrayList<>((Collection<? extends Trajectory.State>) states);
    }

    public void SwerveRun(){
        SwerveControllerCommand swerveControllerCommand = new SwerveControllerCommand(trajectory,pose2d,swerveDriveKinematics,pidControllerX,pidControllerY,profiledPIDController,swerveModuleStates -> {});
    }
}
