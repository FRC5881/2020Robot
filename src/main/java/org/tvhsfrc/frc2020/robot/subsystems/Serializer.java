package org.tvhsfrc.frc2020.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class Serializer implements Subsystem {
    private final static double SERIALIZER_OVERRIDE_SPEED = 0.3d;
    private final static double LAUNCHER_OVERRIDE_SPEED = 0.3d;
    private final static double INTAKE_OVERRIDE_SPEED = 0.3d;
    private final static double SERIALIZER_SPEED = 0.3d;
    private WPI_TalonSRX serializerMotor = new WPI_TalonSRX(9);
    private WPI_TalonSRX launcherMotor = new WPI_TalonSRX(10);
    private WPI_TalonSRX intakeMotor = new WPI_TalonSRX(11);
    private int fuelCount;
    private boolean intakeTripped;
    private boolean serializerTripped;

    public Serializer() {
        serializerMotor.config_kP(0, 0.01d);
        serializerMotor.config_kI(0, 0);
        serializerMotor.config_kD(0, 0.0001d);

    }

    /**
     * Serializer
     */
    public void stopSerializer() {
        serializerMotor.stopMotor();
    }

    public void runSerializer() {
        serializerMotor.set(SERIALIZER_OVERRIDE_SPEED);
    }

    public void reverseSerializer() {
        serializerMotor.set(-1 * SERIALIZER_OVERRIDE_SPEED);
    }

    /**
     * Launcher
     */
    public void stopLauncher() {
        launcherMotor.stopMotor();
    }

    public void runLauncher() {
        launcherMotor.set(LAUNCHER_OVERRIDE_SPEED);
    }

    public void reverseLauncher() {
        launcherMotor.set(-1*LAUNCHER_OVERRIDE_SPEED);
    }

    /**
     * Intake
     */
    public void stopIntake() {
        intakeMotor.stopMotor();
    }

    public void runIntake() {
        intakeMotor.set(INTAKE_OVERRIDE_SPEED);
    }

    public void reverseIntake() {
        intakeMotor.set(-1*INTAKE_OVERRIDE_SPEED);
    }

    public void intakeRun() {
        if (intakeTripped = true) {
            if (fuelCount < 5) {
                serializerMotor.set(SERIALIZER_SPEED);
                fuelCount++;
            }
            else {

            }
        }
    }
}
