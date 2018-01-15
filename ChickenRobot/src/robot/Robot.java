package robot;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * The {@link Robot} class represents a basic robot with 2 wheels.
 */
public class Robot {
	private final DifferentialPilot differentialPilot;
	
	/**
	 * Constructor. Creates a new Robot with 2 wheels, each one connected to a motor.
	 */
	public Robot() {
		final RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(RobotConfig.LEFT_MOTOR_PORT);
		final RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(RobotConfig.RIGHT_MOTOR_PORT);
		differentialPilot = new DifferentialPilot(RobotConfig.WHEEL_DIAMETER, RobotConfig.TRACK_WIDTH, leftMotor, rightMotor, false);
		differentialPilot.setTravelSpeed(RobotConfig.TRAVEL_SPEED);
	}
	
	/**
	 * Starts the robot moving forward.
	 */
	public void forward() {
		differentialPilot.forward();
	}
	
	/**
	 * Moves the robot a specific distance in a (hopefully) straight line.
	 *
	 * @param distance The distance to move, in cm.
	 */
	public void travel(final double distance) {
		differentialPilot.travel(distance);
	}
	
	/**
	 * Turns the robot to the left. </br>
	 * The robot starts by moving backwards, 45 degrees along an arc to the left.
	 * Then it moves forward along an other arc of 45 degrees, to the right. </br>
	 * This helps the robot to use less space when it turns.
	 */
	public void turnLeft() {
		differentialPilot.arc(RobotConfig.TURN_RADIUS, 45);
		differentialPilot.arc(-RobotConfig.TURN_RADIUS, 45);
	}
	
	/**
	 * Turns the robot to the right. </br>
	 * The robot starts by moving backwards, 45 degrees along an arc to the right.
	 * Then it moves forward along an other arc of 45 degrees, to the left. </br>
	 * This helps the robot to use less space when it turns.
	 */
	public void turnRight() {
		differentialPilot.arc(RobotConfig.TURN_RADIUS, -45);
		differentialPilot.arc(-RobotConfig.TURN_RADIUS, -45);
	}

	/**
	 * Moves the robot a few centimeters to the right.
	 */
	public void moveAside() {
		differentialPilot.rotate(RobotConfig.STEP_AWAY_ANGLE);
		differentialPilot.travel(-RobotConfig.STEP_AWAY_DISTANCE);
		differentialPilot.rotate(-RobotConfig.STEP_AWAY_ANGLE);
		differentialPilot.travel(RobotConfig.STEP_AWAY_DISTANCE);
	}
	
	/**
	 * Stops the robot.
	 */
	public void stop() {
		differentialPilot.stop();
	}

	/**
	 * Checks if the robot is moving.
	 *
	 * @return <code>true</code> if the robot is moving, <code>false</code> otherwise.
	 */
	public boolean isMoving() {
		return differentialPilot.isMoving();
	}
}
