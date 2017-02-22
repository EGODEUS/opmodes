package org.firstinspires.ftc.robotcontroller.external.samples.com.Ilitetheworldonfire.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by famil on 1/26/2017.
 */
@Autonomous(name="TestAutonomous", group="Iterative Opmode")
public class TestCode extends LinearOpMode{
    public Servo bumper = null;
    public ColorSensor right;

public void runOpMode() throws InterruptedException {
        right = hardwareMap.colorSensor.get("rightColor");
        bumper = hardwareMap.servo.get("servo");
        right.enableLed(false);

    waitForStart();
    telemetry.addData("Red  ", right.red());
    telemetry.addData("Green", right.green());
    telemetry.addData("Blue ", right.blue());
    telemetry.addData("Bumper", bumper.getPosition());

    try {
        scan();
        sleep(4000);
    }
    catch (Exception e) {
        e.printStackTrace();
    }


    telemetry.update();
    }

    public void scan()
    {
        if(right.blue() > 0 && right.blue() > right.red() && right.blue() < 250)
        {
            bumper.setPosition(180);
        }
        else
        {
            bumper.setPosition(0);
        }
    }
}
