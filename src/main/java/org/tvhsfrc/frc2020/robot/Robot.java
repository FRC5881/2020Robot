/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.tvhsfrc.frc2020.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * methods corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private Command autonomousCommand;

    private RobotContainer robotContainer;


    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
        // autonomous chooser on the dashboard.
        robotContainer = new RobotContainer();
    }

    /**
     * This method is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /**
     * This method is called once each time the robot enters Disabled mode.
     */
    @Override
    public void disabledInit() {
    }

    /**
     * This method is called periodically when the robot is in disabled mode.
     */
    @Override
    public void disabledPeriodic() {
    }

    /**
     * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
     */
    @Override
    public void autonomousInit() {
        autonomousCommand = robotContainer.getAutonomousCommand();

        // schedule the autonomous command (example)
        if (autonomousCommand != null)
        {
            autonomousCommand.schedule();
        }
    }

    /**
     * This method is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
    }

    /**
     * This method is called when the robot first enters teleop mode.
     */
    @Override
    public void teleopInit() {

        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
        {
            autonomousCommand.cancel();
        }
    }

    // Define the joystick.
    public Joystick stick = new Joystick(0);

    // Define the talons (which control the motors) so we can access them in the future.
    // TODO: Figure out what the actual ports/ device number
    WPI_TalonSRX leftFrontTalon = new WPI_TalonSRX(1);
    WPI_TalonSRX leftRearTalon = new WPI_TalonSRX(3);
    WPI_TalonSRX rightFrontTalon = new WPI_TalonSRX(2);
    WPI_TalonSRX rightRearTalon = new WPI_TalonSRX(4);

    /**
     * These "SpeedControllerGroups" have the two drive motors from each side in them, one for the left and one for the
     * right. This is done because the "differentialDrive" group only takes two motors/ speedControllers.
     */
    SpeedControllerGroup left = new SpeedControllerGroup(leftFrontTalon, leftRearTalon);
    SpeedControllerGroup right = new SpeedControllerGroup(rightFrontTalon, rightRearTalon);

    // This is the "control group" for the drive motors.
    DifferentialDrive drive = new DifferentialDrive(left, right);

    /**
     * This method is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        // This takes the raw values from the joystick and runs the drive motors accordingly
        drive.arcadeDrive(stick.getRawAxis(1), stick.getRawAxis(2));
    }

    /**
     * This method is run when the robot first enters test mode.
     */
    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }

    /**
     * This method is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}
