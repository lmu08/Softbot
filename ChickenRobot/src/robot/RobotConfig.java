package robot;

import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;

/**
 * The {@link RobotConfig} class contains constants used to configure the LeJOS EV3 robot.
 */
public final class RobotConfig {
	
	private RobotConfig() {
		// Prevent instanciation.
	}
	
	/*
	 * Motor ports
	 */
	
	/** Port of the left motor of the robot. */
	public static final Port LEFT_MOTOR_PORT = MotorPort.B;
	
	/** Port of the right motor of the robot. */
	public static final Port RIGHT_MOTOR_PORT = MotorPort.C;
	
	/*
	 * Sensor ports
	 */
	
	/** Port of the left touch sensor of the robot. */
	public static final Port LEFT_TOUCH_SENSOR_PORT = SensorPort.S1;
	
	/** Port of the right touch sensor of the robot. */
	public static final Port RIGHT_TOUCH_SENSOR_PORT = SensorPort.S2;
	
	/** Port of the ultrasonic sensor of the robot. */
	public static final Port COLOR_SENSOR_PORT = SensorPort.S3;
	
	/** Port of the ultrasonic sensor of the robot. */
	public static final Port ULTRASONIC_SENSOR_PORT = SensorPort.S4;
	
	/*
	 * Dimensions
	 */
	
	/** Wheel diameter of the robot (in cm). */
	public static final double WHEEL_DIAMETER = 5.56;
	
	/** Distance between the center of the two wheels of our robot (in cm). */
	public static final double TRACK_WIDTH = 12.5;
	
	/*
	 * Movement / Distances
	 */
	
	/** Radius of the circle that the robot follows when turning (in cm). */
	public static final double TURN_RADIUS = 15;
	
	/** Base speed of the robot (in cm/s) */
	public static final double TRAVEL_SPEED = 20;
	
	/** Distance that the robot sould travel when stepping away (in cm). */
	public static final double STEP_AWAY_DISTANCE = 10;
	
	/** Angle that the robot should use when stepping away (in degrees). */
	public static final double STEP_AWAY_ANGLE = 25;

	/** Error margin for the measures of the ultrasonic sensor (meters). */
	public static final float ULTRASONIC_SENSOR_MARGIN = 0.1f;
	
	/** Maximum distance that the robot is allowed to deviate (meters). */
	public static final float MAXIMUM_DEVIATION = 0.15f;

	/** Maximum distance to the wall (meters). */
	public static final float MAXIMUM_WALL_DISTANCE = 0.4f;

	/** Minimum distance to the wall (meters). */
	public static final float MINIMUM_WALL_DISTANCE = 0.05f;
}
