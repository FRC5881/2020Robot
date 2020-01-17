package org.tvhsfrc.frc2020.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

    // TODO: Figure out what device number this will be
    // This is the talon for the belt motor
    private WPI_TalonSRX beltTalon = new WPI_TalonSRX(1);

    // TODO: Figure out what the channels will be for this
    // This is the solenoid that will be used for (fill in the blank)
    private DoubleSolenoid solenoid = new DoubleSolenoid(1, 2);

    /**
     * Creates a new ExampleSubsystem.
     */
    public Intake() {
        super();

        beltTalon.setName("Belt Talon");
        LiveWindow.enableTelemetry(beltTalon);

        LiveWindow.enableTelemetry(solenoid);
    }

    public void IntakeRun(){
        // TODO: Make this work right
        solenoid.set(DoubleSolenoid.Value.kForward);
        beltTalon.set(1);
    }
}

