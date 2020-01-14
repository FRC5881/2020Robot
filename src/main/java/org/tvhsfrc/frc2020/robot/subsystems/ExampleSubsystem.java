/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.tvhsfrc.frc2020.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ExampleSubsystem extends SubsystemBase {
    // Example motor
    private WPI_TalonSRX exampleMotor = new WPI_TalonSRX(50);

    /**
     * Creates a new ExampleSubsystem.
     */
    public ExampleSubsystem() {
        super();

        init();
    }

    /**
     * Initialize the subsystem
     */
    public void init() {
        // Set up PID controllers and first time stuff here
        exampleMotor.setName("Example Motor");
        LiveWindow.enableTelemetry(exampleMotor);
    }
}
