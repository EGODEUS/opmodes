package org.firstinspires.ftc.robotcontroller.external.samples.com.Ilitetheworldonfire.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Titus Okine on 12/8/2016.
 */
@Autonomous(name="Tutorial Autonomous")
public class TutorialAutonomous extends OpMode {
    //set up motor variables
    public DcMotor frontLeftMotor = null;
    public DcMotor frontRightMotor = null;
    public DcMotor intake = null;
    public DcMotor elevate = null;
    public DcMotor flywheelLeft = null;
    public DcMotor flywheelRight = null;
    public boolean test = true;
    //public Servo bumper = null;
    private double flyPower = -0.6;
    public double deadzone = 0.1;

    @Override
    public void init() // code runs once when started
    {
        //set motor variables
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");

        elevate = hardwareMap.dcMotor.get("elevator");
        intake = hardwareMap.dcMotor.get("intake");
        flywheelLeft = hardwareMap.dcMotor.get("flywheelLeft");
        flywheelRight = hardwareMap.dcMotor.get("flywheelRight");
        //backLeftMotor    = hardwareMap.dcMotor.get("back_left_;drive");
        //backRightMotor   = hardwareMap.dcMotor.get("back_right_drive");
        //reverse left wheels
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        //backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void start() //code run continuously when Op mode running
    {
        if (test) {
            try {
                goBackwards(-1.0);
                wait(200);
                shoot(flyPower);
                wait(2000);
                shoot(-flyPower);
                requestOpModeStop();
            } catch (Exception e) {
                e.printStackTrace();
            }
            goBackwards(0);
            test = false;
        }
    }

    public void loop() {

    }


    public void goStraight(double power)
    {
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
    }

    public void goLeft(double power)
    {
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(-power);
    }

    public void goRight(double power)
    {
        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(power);
    }

    public void goBackwards(double power)
    {
        frontRightMotor.setPower(-power);
        frontLeftMotor.setPower(-power);
    }

    public void shoot(double power) {
        if(flywheelLeft.getCurrentPosition() < flywheelRight.getCurrentPosition())
        {
            flywheelRight.setPower(flyPower = flyPower + 0.3);
            flywheelLeft.setPower(flyPower);
        }
        else if(flywheelLeft.getCurrentPosition() > flywheelRight.getCurrentPosition())
        {
            flywheelLeft.setPower(flyPower = flyPower - 0.3);
            flywheelRight.setPower(flyPower);
        }
        else
        {
            flywheelLeft.setPower(flyPower);
            flywheelRight.setPower(flyPower);
        }
    }
}
