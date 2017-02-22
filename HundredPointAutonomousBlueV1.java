package org.firstinspires.ftc.robotcontroller.external.samples.com.Ilitetheworldonfire.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by famil on 1/5/2017.
 */

@Autonomous(name="EnigmaAutonomousBlueRight", group="Iterative Opmode")
public class HundredPointAutonomousBlueV1 extends LinearOpMode{
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
    public double rightPower = 0.5;
    public double leftPower = -0.5;




    private ElapsedTime runtime = new ElapsedTime();
    private double flyPower = 0.85;

    public void runOpMode() throws InterruptedException // code runs once when started
    {
        //set motor variables
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        frontLeftMotor.setTargetPosition(0);
        frontRightMotor.setTargetPosition(0);


        elevate = hardwareMap.dcMotor.get("elevator");
        intake = hardwareMap.dcMotor.get("intake");
        flywheelLeft = hardwareMap.dcMotor.get("flywheelLeft");
        flywheelRight = hardwareMap.dcMotor.get("flywheelRight");
        right = hardwareMap.colorSensor.get("rightColor");
        bumper = hardwareMap.servo.get("bumper");
        leftLift = hardwareMap.servo.get("leftLift");
        rightLift = hardwareMap.servo.get("rightLift");
        reset();
        telemetry.addData("Bumper Position", bumper.getPosition());
        telemetry.update();

        right.enableLed(false);

        //reverse left wheels
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        try {
            shoot(flyPower);
            backwards(1440/2);
            left(1460);
            straight(1990);
            left((1440/2)-60);
            straight(2640);
            left(1440/2);
            right(1440/4);
//            straight(300);
//            scan();
//            straight(360);
//            scan();
          }
        catch(Exception e)
        {
            telemetry.addData("Error",e );
        }
    }

    /**
     *
     * @param stopPoint must be positive
     */
    public void left(int stopPoint) {
        reset();

        frontLeftMotor.setTargetPosition(stopPoint);
        frontRightMotor.setTargetPosition(stopPoint);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftMotor.setPower(leftPower);
        frontRightMotor.setPower(rightPower);

        while(frontLeftMotor.isBusy() && frontRightMotor.isBusy())
        {
            telemetry.addData("Left Drive Position:", frontLeftMotor.getCurrentPosition());
            telemetry.addData("Right Drive Position:", frontRightMotor.getCurrentPosition());
            telemetry.addData("Red  ", right.red());
            telemetry.addData("Green", right.green());
            telemetry.addData("Blue ", right.blue());
            telemetry.update();
        }

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    /**
     *
     * @param stopPoint must be positive
     */
    public void right(int stopPoint) {
        reset();

        frontLeftMotor.setTargetPosition(-stopPoint);
        frontRightMotor.setTargetPosition(-stopPoint);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftMotor.setPower(-leftPower);
        frontRightMotor.setPower(-rightPower);

        while(frontLeftMotor.isBusy() && frontRightMotor.isBusy())
        {
            telemetry.addData("Left Drive Position:", frontLeftMotor.getCurrentPosition());
            telemetry.addData("Right Drive Position:", frontRightMotor.getCurrentPosition());
            telemetry.addData("Red  ", right.red());
            telemetry.addData("Green", right.green());
            telemetry.addData("Blue ", right.blue());
            telemetry.update();
        }

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void backwards(int stopPoint) {
        reset();

        frontLeftMotor.setTargetPosition(stopPoint);
        frontRightMotor.setTargetPosition(-stopPoint);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftMotor.setPower(1);
        frontRightMotor.setPower(1);

        while(frontLeftMotor.isBusy() && frontRightMotor.isBusy())
        {
            telemetry.addData("Left Drive Position:", frontLeftMotor.getCurrentPosition());
            telemetry.addData("Right Drive Position:", frontRightMotor.getCurrentPosition());
            telemetry.addData("Red  ", right.red());
            telemetry.addData("Green", right.green());
            telemetry.addData("Blue ", right.blue());
            telemetry.update();
        }

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void straight(int stopPoint){
        reset();

        frontLeftMotor.setTargetPosition(-stopPoint);
        frontRightMotor.setTargetPosition(stopPoint);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftMotor.setPower(-1);
        frontRightMotor.setPower(1);

        while(frontLeftMotor.isBusy() && frontRightMotor.isBusy())
        {

        }

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void reset()
    {
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bumper.setPosition(0.5);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
    }

    public void scan()
    {
        telemetry.addData("Color Sensor value", right.blue());
        telemetry.addData("Bumper Value", bumper.getPosition());
        telemetry.update();
        if(right.blue() > right.red())
        {
            bumper.setPosition(0);
        }
        else if(right.blue() < right.red())
        {
            backwards(1440/4);
            bumper.setPosition(0);
        }
        else {
            reset();
        }
    }

    public void shoot(double power) {
        try
        {
            flywheelLeft.setPower(power);
            flywheelRight.setPower(-power);
            elevate.setPower(-0.7);
            intake.setPower(power*2);
            Thread.sleep(3500);
            reset();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
