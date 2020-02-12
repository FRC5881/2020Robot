package org.tvhsfrc.frc2020.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class Serializer implements Subsystem {

    /**
     * Override for command
     */
    private final static double SERIALIZER_OVERRIDE_SPEED = 0.3d;
    private final static double LAUNCHER_OVERRIDE_SPEED = 0.3d;
    private final static double INTAKE_OVERRIDE_SPEED = 0.3d;

    /**
     * Speed for rotating 28 degrees
     */
    private final static double SERIALIZER_SPEED = 0.3d;

    /**
     * SRX Assignments
     */
    private WPI_TalonSRX serializerMotor = new WPI_TalonSRX(9);
    private WPI_TalonSRX launcherMotor = new WPI_TalonSRX(10);
    private WPI_TalonSRX intakeMotor = new WPI_TalonSRX(11);

    /**
     * States
     */
    private int fuelCount;
    private boolean intakeTripped;
    private boolean serializerTripped;
    private boolean lastIntake;
    private boolean lastSerializer;

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

    /**
     * Wow such command
     */
    public void intakeRun() {
        if (serializerTripped) {
            lastSerializer = true;
        }
        if (!serializerTripped) {
            lastSerializer = false;
        }
        if (intakeTripped) {
            lastIntake = true;
        }
        if (!intakeTripped) {
            lastIntake = false;
        }
        if (fuelCount == 0) {
            intakeMotor.set(1);
            serializerMotor.set(1);
            if (serializerTripped && !lastSerializer) {
                serializerMotor.set(SERIALIZER_SPEED);
                lastSerializer = false;
                fuelCount ++;
            }
        }
        else if (fuelCount <= 4 && !serializerTripped && !intakeTripped) {
            serializerMotor.set(SERIALIZER_SPEED);
            stopSerializer();
            lastSerializer = false;
            lastIntake = false;
        }
        else if (fuelCount == 5) {
            stopIntake();
            stopSerializer();
        }
        else if (fuelCount >= 0 && fuelCount < 5 && intakeTripped && serializerTripped) {
            stopIntake();
            serializerMotor.set(SERIALIZER_SPEED);
            lastIntake = true;
            lastSerializer = true;
        }
        else if (fuelCount >= 1 && fuelCount <5 && !intakeTripped && serializerTripped) {
            serializerMotor.set(SERIALIZER_SPEED);
            fuelCount ++;
            lastIntake = false;
            lastSerializer = true;
        }
        else if (fuelCount >= 0 && fuelCount <=4 && intakeTripped && !serializerTripped){
            fuelCount ++;
            serializerMotor.set(SERIALIZER_SPEED);
            lastIntake = true;
            lastSerializer = false;
        }
    }
}