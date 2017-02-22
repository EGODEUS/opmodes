package org.firstinspires.ftc.robotcontroller.external.samples.com.Ilitetheworldonfire.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by famil on 1/5/2017.
 */

@Autonomous(name="EnigmaAutonomous", group="Iterative Opmode")
public class AutonomousCode extends LinearOpMode {
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
    private double deadzone = 0.1;

    @Override
    public void runOpMode() throws InterruptedException // code runs once when started
    {
        //set motor variables
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        frontLeftMotor.setTargetPosition(0);
        frontRightMotor.setTargetPosition(0);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

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
        telemetry.addData("Left Drive Position:", frontLeftMotor.getCurrentPosition());
        telemetry.addData("Right Drive Position:", frontRightMotor.getCurrentPosition());
        telemetry.addData("Red  ", right.red());
        telemetry.addData("Green", right.green());
        telemetry.addData("Blue ", right.blue());
        shoot(-flyPower);
        goLeft(-0.789,360);
        goStraight(-1.0,1080);
        goLeft(-0.789,360);
        goStraight(-1.0,1080);
        scan();
        if(right.blue() < 15)
        {
            goBackwards(-0.1,260);
            scan();
        }
//       frontLeftMotor.setPower(1.0);
//       frontRightMotor.setPower(-1.0);
        try {
            wait(4000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        telemetry.update();
    }


    public void goStraight(double power, int stopPoint)
    {
        frontRightMotor.setTargetPosition(stopPoint);
        frontLeftMotor.setTargetPosition(stopPoint);
//        while( frontLeftMotor.getCurrentPosition() <= stopPoint &&
//                frontRightMotor.getCurrentPosition() <= stopPoint)
//        {
            frontLeftMotor.setPower(power);
            frontRightMotor.setPower(-power);
 //       }
    }

    public void goLeft(double power, int stopPoint) {
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setTargetPosition(stopPoint);
        runtime.reset();
 //       while (frontRightMotor.getCurrentPosition() <= stopPoint) {
            frontRightMotor.setPower(-power);
 //       }
    }

    /**
     *
     * @param power how much power to move at between -1 and 1
     * @param stopPoint where to stop moving, make sure it is positive
     */
    public void goRight(double power, int stopPoint)
    {

        frontLeftMotor.setTargetPosition(stopPoint);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        while( frontLeftMotor.getCurrentPosition() <= stopPoint ) {
            frontRightMotor.setPower(power);
//        }
    }

    /**
     *
     * @param power amount of power to move at
     * @param stopPoint where to stop. Make sure stopping point is negative
     */
    public void goBackwards(double power,int stopPoint)
    {


        while( frontLeftMotor.getCurrentPosition() >= stopPoint &&
            frontRightMotor.getCurrentPosition() >= stopPoint) {
            frontRightMotor.setPower(-power);
            frontLeftMotor.setPower(power);
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

    public void scan()
    {
        if(right.blue() > 0 && right.blue() > right.red())
        {
            bumper.setPosition(180);
        }
        else
        {
            bumper.setPosition(0);
        }
    }
}
