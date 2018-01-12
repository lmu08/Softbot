package robot;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import sensors.TouchSensor;

public class Robot {
	final DifferentialPilot differentialPilot;
	
	public Robot() {
		final RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(RobotConfig.LEFT_MOTOR_PORT);
		final RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(RobotConfig.RIGHT_MOTOR_PORT);
		differentialPilot = new DifferentialPilot(RobotConfig.WHEEL_DIAMETER, RobotConfig.TRACK_WIDTH, leftMotor, rightMotor, false);
		differentialPilot.setTravelSpeed(RobotConfig.TRAVEL_SPEED);
	}
	
	public void forward() {
		differentialPilot.forward();
	}
	
	public void turnLeft() {
		differentialPilot.arc(RobotConfig.TURN_RADIUS, 90);
	}
	
	public void turnRight() {
		differentialPilot.arc(RobotConfig.TURN_RADIUS, -90);
	}
	
	/**
	 * In progress. Currently, the robot reaches the wall, turns right, and stops.<br/>
	 * TODO Use the ultrasonic sensor
	 */
	public void solveMaze() {
		final TouchSensor touchSensor = new TouchSensor();
		
		//Move until the wall and turn right.
		forward();
		while (!touchSensor.isPressed()) {
			//Wait for an obstacle
		}
		turnRight();

		//Close resources.
		touchSensor.close();
	}
	
}
