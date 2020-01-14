package org.tvhsfrc.frc2020.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMax;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.tvhsfrc.frc2020.robot.Robot;
import org.tvhsfrc.frc2020.robot.RobotContainer;


public class Shooter extends SubsystemBase {
    //Define motor
    private static CANSparkMax shooterMaster = new CANSparkMax(100, CANSparkMaxLowLevel.MotorType.kBrushless);
    private static CANSparkMax shooterFollower = new CANSparkMax(101, CANSparkMaxLowLevel.MotorType.kBrushless);

    public Shooter() {
        super();

        init();
    }

    public void init() {
        shooterFollower.follow(shooterMaster);

        // PID controller
        CANPIDController pidController = shooterMaster.getPIDController();

        // display PID coefficients on SmartDashboard
        SmartDashboard.putNumber("Shooter P", 0);
        SmartDashboard.putNumber("Shooter I", 0);
        SmartDashboard.putNumber("Shooter D", 0);
        SmartDashboard.putNumber("Shooter IZ", 0);
        SmartDashboard.putNumber("Shooter F", 0);

        // set PID coefficients
        pidController.setP(getkP());
        pidController.setI(getkI());
        pidController.setD(getkD());
        pidController.setIZone(getkIz());
        pidController.setFF(getkFF());
        pidController.setOutputRange(-1, 1);
    }

    public void shootAtSpeed(double speed) {
        shooterMaster.set(speed);
    }

    public void shootAtJoystickSpeed() {
        shootAtSpeed(Robot.robotContainer.driverController.getRawAxis(RobotContainer.XBOX_LEFT_Y_AXIS));
    }

    // --- Getters ---
    public double getkP() {
        return SmartDashboard.getNumber("Shooter P" ,0);
    }

    public double getkI() {
        return SmartDashboard.getNumber("Shooter I",0);
    }

    public double getkD() {
        return SmartDashboard.getNumber("Shooter D",0);
    }

    public double getkIz() {
        return SmartDashboard.getNumber("Shooter IZ", 0);
    }

    public double getkFF() {
        return SmartDashboard.getNumber("Shooter F", 0);
    }
}



