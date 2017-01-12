package org.firstinspires.ftc.robotcontroller.external.samples.com.Ilitetheworldonfire.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created 10/29/2016 by Cameron Rhodes  Edited By Titus Okine with the amazing help of Gabriel Hakime
 */
@TeleOp(name="EnigmaCode", group="Iterative Opmode")
public class TutorialOp extends OpMode
{
    //set up motor variables
    public DcMotor frontLeftMotor = null;
    public DcMotor frontRightMotor = null;
    public DcMotor backLeftMotor = null;
    public DcMotor backRightMotor = null;
    public DcMotor intake = null;
    boolean test = true;
    public DcMotor elevate = null;
    public DcMotor flywheelLeft = null;
    public DcMotor flywheelRight = null;
    //public Servo bumper = null;
    private double flyPower = -0.69 / 2.5;
    public double deadzone = 0.1;

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
        float intakePower = gamepad2.left_stick_y/2;
        float elevatePower = gamepad2.right_stick_y;
        //float flyPower = gamepad2.left_trigger;
        //if joystick values are less than 0.1 set them to 0
        //prevent`1s joystic sticking
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
        if(gamepad1.right_trigger == 1)
        {
                flywheelLeft.setPower(flyPower);
                flywheelRight.setPower(flyPower);

        }
        else
        {
            flywheelLeft.setPower(0);
            flywheelRight.setPower(0);
        }
        //add telemetry data to veiw controller output


        telemetry.addData("Left Speed: ",leftPower);
        telemetry.addData("Right Speed: ", rightPower);
        telemetry.addData("Intake Power: ", intakePower);
        telemetry.addData("Elevate Power: ", elevatePower);
        telemetry.addData("Fly Power", flyPower);
    }
}
