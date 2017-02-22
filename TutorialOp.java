package org.firstinspires.ftc.robotcontroller.external.samples.com.Ilitetheworldonfire.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created 10/29/2016 by Cameron Rhodes  Edited By Titus Okine and Gabriel Hakime
 */
@TeleOp(name="EnigmaCode", group="Iterative Opmode")
public class TutorialOp extends OpMode
{
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
    private double deadzone = 0.1;
    private double flyPowerFast = -0.95;
    private double flypowerSlow = -0.65;

    @Override
    public void init() // code runs once when started
    {
        //set motor variables
        frontLeftMotor   = hardwareMap.dcMotor.get("frontLeftMotor");
        frontRightMotor  = hardwareMap.dcMotor.get("frontRightMotor");
        elevate = hardwareMap.dcMotor.get("elevator");
        intake = hardwareMap.dcMotor.get("intake");
        flywheelLeft = hardwareMap.dcMotor.get("flywheelLeft");
        flywheelRight = hardwareMap.dcMotor.get("flywheelRight");
        leftLift = hardwareMap.servo.get("leftLift");
        rightLift = hardwareMap.servo.get("rightLift");
        bumper = hardwareMap.servo.get("bumper");
        leftLift = hardwareMap.servo.get("leftLift");
        rightLift = hardwareMap.servo.get("rightLift");
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //backLeftMotor    = hardwareMap.dcMotor.get("back_left_;drive");
        //backRightMotor   = hardwareMap.dcMotor.get("back_right_drive");
        //reverse left wheels
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        //backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() //code run continuously when Op mode running
    {
        //get joystic values
        float leftPower = gamepad1.left_stick_y;
        float rightPower = -gamepad1.right_stick_y;
        float intakePower = gamepad2.left_stick_y;
        float elevatePower = gamepad2.right_stick_y;
        //float flyPower = gamepad2.left_trigger;
        //if joystick values are less than 0.1 set them to 0
        //prevent`1s joystick sticking
//        if(Math.abs(leftPower) < deadzone)
//        {
//            leftPower = 0;
//        }
//        if(Math.abs(rightPower) < deadzone)
//        {
//            rightPower = 0;
//        }
        //set motor speed
        frontLeftMotor.setPower(leftPower);
        frontRightMotor.setPower(rightPower);
        intake.setPower(intakePower);
        elevate.setPower(elevatePower);
        if(gamepad2.right_trigger == 1)
        {
                flywheelLeft.setPower(-flypowerSlow);
                flywheelRight.setPower(flypowerSlow);

        }
        else if(gamepad2.left_trigger == 1)
        {
            flywheelLeft.setPower(-flyPowerFast);
            flywheelRight.setPower(flyPowerFast);
        }
        else
        {
            flywheelLeft.setPower(0);
            flywheelRight.setPower(0);
        }

        if(gamepad1.left_trigger == 1)
        {
            leftLift.setPosition(180);
            rightLift.setPosition(0);
        }
        else
        {
            leftLift.setPosition(0);
            rightLift.setPosition(180);
        }

        if(gamepad1.right_trigger == 1)
        {
            bumper.setPosition(0);
        }
        else
        {
            bumper.setPosition(1);
        }
        //add telemetry data to veiw controller output


        telemetry.addData("Left Speed: ",leftPower);
        telemetry.addData("Right Speed: ", rightPower);
        telemetry.addData("Intake Power: ", intakePower);
        telemetry.addData("Elevate Power: ", elevatePower);
        telemetry.addData("Left Position", frontLeftMotor.getCurrentPosition());
        telemetry.addData("Right Position", frontRightMotor.getCurrentPosition());
        telemetry.addData("Bumper Position",bumper.getPosition());
        telemetry.addData("Fly Power", flyPowerFast);
    }
}
