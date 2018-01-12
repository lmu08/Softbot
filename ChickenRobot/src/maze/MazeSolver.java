package maze;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import robot.Robot;
import sensors.TouchSensor;

/**
 * Class that defines the movement algorithms used by the robot.
 */
public class MazeSolver {

	/**
	 * In progress. Currently, the robot reaches the wall, turns right, and stops.<br/>
	 * TODO Use the ultrasonic sensor
	 *
	 * @param robot robot used to solve the maze
	 */
	public static void solve(final Robot robot) {
		final TouchSensor touchSensor = new TouchSensor();

		//Move until the wall and turn right.
		robot.forward();
		while (!touchSensor.isPressed()) {
			//Wait for an obstacle
		}
		robot.turnRight();

		//Close resources.
		touchSensor.close();
	}
	
	/**
	 * Sample code for ultrasonic sensors, not up-to-date.
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private static void ultrasonicSensorTest() {
		//Ultrasonic sensor
		final EV3UltrasonicSensor eyes = new EV3UltrasonicSensor(SensorPort.S4);
		final SampleProvider eyesSampleProvider = eyes.getDistanceMode();
		final float[] sample = new float[eyesSampleProvider.sampleSize()];

		//Get range
		do {
			eyesSampleProvider.fetchSample(sample, 0);
			System.out.println(sample[0]);
		} while (Button.ESCAPE.isUp());

		//Close resources
		eyes.close();
	}
	
	/**
	 * Sample code for touch sensors, not up-to-date.
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private static void touchSensorsTest() {
		//Create motors
		final RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
		final RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
		
		//Forward
		leftMotor.setSpeed(720);// 2 RPM
		rightMotor.setSpeed(720);
		leftMotor.forward();
		rightMotor.forward();
		
		//Touch sensor
		final EV3TouchSensor touchSensor1 = new EV3TouchSensor(SensorPort.S1);
		final EV3TouchSensor touchSensor2 = new EV3TouchSensor(SensorPort.S2);
		final SensorMode touch1 = touchSensor1.getTouchMode();
		final SensorMode touch2 = touchSensor2.getTouchMode();
		final float[] sample = new float[2];
		
		//Wait for obstacle
		do {
			touch1.fetchSample(sample, 0);
			touch2.fetchSample(sample, 1);
		} while (leftMotor.isMoving() && rightMotor.isMoving() && ((sample[0] == 0) || (sample[1] == 0)));
		
		//Go backwards
		leftMotor.backward();
		rightMotor.backward();
		Delay.msDelay(1000);
		leftMotor.stop();
		rightMotor.stop();
		
		//Close resources
		leftMotor.close();
		rightMotor.close();
		touchSensor1.close();
		touchSensor2.close();
	}
}
