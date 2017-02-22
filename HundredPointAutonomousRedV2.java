package org.firstinspires.ftc.robotcontroller.external.samples.com.Ilitetheworldonfire.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by famil on 2/3/2017.
 */
@Autonomous(name="EnigmaAutonomousRedLeft", group="Iterative Opmode")
public class HundredPointAutonomousRedV2 extends LinearOpMode{

    //set up motor variables
    private DcMotor frontLeftMotor = null;
    private DcMotor frontRightMotor = null;
    private DcMotor intake = null;
    private DcMotor elevate = null;
    private DcMotor flywheelLeft = null;
    private DcMotor flywheelRight = null;
    public Servo bumper = null;
    public Servo leftLift = null;
    public Servo rightLift = null;
    public ColorSensor right;
    private ElapsedTime runtime = new ElapsedTime();
    private double flyPower = 0.350;

    public void runOpMode() throws InterruptedException // code runs once when started
    {
        //set motor variables
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        frontLeftMotor.setTargetPosition(0);
        frontRightMotor.setTargetPosition(0);
        bumper.setPosition(180.0);

        elevate = hardwareMap.dcMotor.get("elevator");
        intake = hardwareMap.dcMotor.get("intake");
        flywheelLeft = hardwareMap.dcMotor.get("flywheelLeft");
        flywheelRight = hardwareMap.dcMotor.get("flywheelRight");
        right = hardwareMap.colorSensor.get("rightColor");
        bumper = hardwareMap.servo.get("bumper");
        leftLift = hardwareMap.servo.get("leftLift");
        rightLift = hardwareMap.servo.get("rightLift");

        right.enableLed(false);

        //reverse left wheels
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        shoot(flyPower);
        right(360);
        straight(360);
        right(180);
        straight(200);
        right(180);
        straight(300);
        scan();
        straight(360);
        scan();
    }

    /**
     *
     * @param stopPoint must be positive
     */
    public void straight(int stopPoint) {
        frontLeftMotor.setTargetPosition(stopPoint);
        frontRightMotor.setTargetPosition(stopPoint);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     *
     * @param stopPoint must be negative
     */
    public void backwards(int stopPoint) {
        frontLeftMotor.setTargetPosition(stopPoint);
        frontRightMotor.setTargetPosition(stopPoint);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void left(int stopPoint) {
        frontRightMotor.setTargetPosition(stopPoint);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void right(int stopPoint){
        frontLeftMotor.setTargetPosition(stopPoint);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void scan()
    {
        if(right.blue() > 0 && right.blue() > right.red())
        {
            bumper.setPosition(180);
        }
        else
        {
            bumper.setPosition(90);
        }
    }

    public void shoot(double power) {
        flywheelLeft.setPower(power);
        flywheelRight.setPower(power);
        try
        {
            elevate.setPower(-0.25);
            intake.setPower(-power*2);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
