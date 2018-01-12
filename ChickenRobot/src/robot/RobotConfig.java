package robot;

import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;

/**
 * Class containing constants used to configure the LeJOS EV3 robot.
 */
public final class RobotConfig {
	/** Port of the left motor of the robot. */
	public static final Port LEFT_MOTOR_PORT = MotorPort.B;
	/** Port of the right motor of the robot. */
	public static final Port RIGHT_MOTOR_PORT = MotorPort.C;
	/** Port of the left touch sensor of the robot. */
	public static final Port LEFT_TOUCH_SENSOR_PORT = SensorPort.S1;
	/** Port of the right touch sensor of the robot. */
	public static final Port RIGHT_TOUCH_SENSOR_PORT = SensorPort.S2;
	/** Port of the ultrasonic sensor of the robot. */
	public static final Port ULTRASONIC_SENSOR_PORT = SensorPort.S4;
	/** Wheel diameter of the robot (in cm). */
	public static final double WHEEL_DIAMETER = 5.56;
	/** Distance between the center of the two wheels of our robot (in cm). */
	public static final double TRACK_WIDTH = 12;
	/** Radius of the circle that the robot follows when turning (in cm). */
	public static final double TURN_RADIUS = 10;
	/** Base speed of the robot (in cm/s) */
	public static final double TRAVEL_SPEED = 20;
}
