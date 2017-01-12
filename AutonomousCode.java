package org.firstinspires.ftc.robotcontroller.external.samples.com.Ilitetheworldonfire.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by famil on 1/5/2017.
 */

public class AutonomousCode extends LinearOpMode {
    //set up motor variables
    private DcMotor frontLeftMotor = null;
    private DcMotor frontRightMotor = null;
    private DcMotor intake = null;
    private DcMotor elevate = null;
    private DcMotor flywheelLeft = null;
    private DcMotor flywheelRight = null;
    private boolean test = true;
    DcMotorController encode;
    //public Servo bumper = null;
    private double flyPower = 0.69;
    private double deadzone = 0.1;

    @Override
    public void runOpMode() throws InterruptedException // code runs once when started
    {
        //set motor variables
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        encode = hardwareMap.dcMotorController.get("drive");

        elevate = hardwareMap.dcMotor.get("elevator");
        intake = hardwareMap.dcMotor.get("intake");
        flywheelLeft = hardwareMap.dcMotor.get("flywheelLeft");
        flywheelRight = hardwareMap.dcMotor.get("flywheelRight");

        //reverse left wheels
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        goBackwards(-1.0,1800);
        shoot(flyPower);
        goLeft(-0.789,360);
        goStraight(-1.0,1080);
    }


    public void goStraight(double power, int stopPoint)
    {
        while( encode.getMotorCurrentPosition(1) <= stopPoint &&
                encode.getMotorCurrentPosition(2) <= stopPoint)
        {
            frontLeftMotor.setPower(power);
            frontRightMotor.setPower(power);
        }
    }

    public void goLeft(double power, int stopPoint) {
        while( encode.getMotorCurrentPosition(1) <= stopPoint ) {
            frontLeftMotor.setPower(power);
            frontRightMotor.setPower(-power);
        }
    }

    /**
     *
     * @param power how much power to move at between -1 and 1
     * @param stopPoint where to stop moving, make sure it is positive
     */
    public void goRight(double power, int stopPoint)
    {
        while( encode.getMotorCurrentPosition(2) <= stopPoint ) {
            frontLeftMotor.setPower(-power);
            frontRightMotor.setPower(power);
        }
    }

    /**
     *
     * @param power amount of power to move at
     * @param stopPoint where to stop. Make sure stopping point is negative
     */
    public void goBackwards(double power,int stopPoint)
    {
        while( encode.getMotorCurrentPosition(1) >= stopPoint &&
            encode.getMotorCurrentPosition(2) >= stopPoint) {
            frontRightMotor.setPower(-power);
            frontLeftMotor.setPower(-power);
        }
    }

    public void shoot(double power) {
        flywheelLeft.setPower(power);
        flywheelRight.setPower(power);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elevate.setPower(power / 2);
        flywheelRight.setPower(power);
        flywheelLeft.setPower(power);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
