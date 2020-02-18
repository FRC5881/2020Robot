package org.tvhsfrc.frc2020.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
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

    private DigitalInput serializerSensor = new DigitalInput(0);
    private DigitalInput intakeSensor = new DigitalInput(1);
    /**
     * States
     */
    private int fuelCount;
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
        if (fuelCount == 0) {
            if (intakeSensor.get() && serializerSensor.get()) { // Ruh Roh Raggy
                serializerMotor.set(SERIALIZER_SPEED);
                stopIntake();
                fuelCount++;
            } else {
                intakeMotor.set(1);
            }
            if (!serializerSensor.get() && lastSerializer) {
                serializerMotor.set(SERIALIZER_SPEED);
                fuelCount++;
                stopSerializer();
                // 28 Degrees and Stop please
                // Increment after
            } else {
                serializerMotor.set(SERIALIZER_SPEED);
                fuelCount++;
            }
        }
        else if (fuelCount <= 4) {
            if (!intakeSensor.get()) {
                if (serializerSensor.get() && !lastSerializer) {
                    serializerMotor.set(1);
                    // intake not tripped, serializer goes high, intake dc, serializer on
                } else if (!serializerSensor.get() && lastSerializer) {
                    serializerMotor.set(SERIALIZER_SPEED);
                    fuelCount++;
                    stopSerializer();
                    // Intake not tripped, serializer goes low, intake dc, serializer 28deg
                }
            } else if (intakeSensor.get() && !lastIntake) {
                if (!serializerSensor.get() && lastSerializer) {
                    serializerMotor.set(SERIALIZER_SPEED);
                    fuelCount++;
                    stopSerializer();
                    // intake goes high, serializer goes low, intake dc/on, serializer 28deg then off
                    // 28 Degrees and Stop please
                    // Increment After
                } else if (serializerSensor.get()) {
                    serializerMotor.set(SERIALIZER_SPEED);
                    fuelCount++;
                    stopIntake();
                } else if (!serializerSensor.get()) {
                    stopSerializer(); // Assume rotation and stop completed
                    intakeMotor.set(INTAKE_OVERRIDE_SPEED);
                }
            } else if (intakeSensor.get()) {
                if (serializerSensor.get()) {
                    // intake tripped, serializer high, intake off, serializer on
                    stopIntake();
                    serializerMotor.set(SERIALIZER_SPEED);
                    fuelCount++;
                } else {
                    stopSerializer();
                }
            }

            serializerMotor.set(SERIALIZER_SPEED);
            stopSerializer();
            lastSerializer = false;
            lastIntake = false;
        }
        else if (fuelCount == 5) { // Oh no you didn't...
            stopIntake();
            stopSerializer();
        }

        // Save last state of sensors for next iteration
        lastSerializer = serializerSensor.get();
        lastIntake = intakeSensor.get();
    }
}