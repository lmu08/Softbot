package robot;

import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;

/**
 * Class containing constants used to configure the LeJOS EV3 robot.
 */
public class RobotConfig {
	/** TODO */
	public static final Port LEFT_MOTOR_PORT = MotorPort.B;
	/** TODO */
	public static final Port RIGHT_MOTOR_PORT = MotorPort.C;
	/** TODO */
	public static final Port LEFT_TOUCH_SENSOR_PORT = SensorPort.S1;
	/** TODO */
	public static final Port RIGHT_TOUCH_SENSOR_PORT = SensorPort.S2;
	/** TODO */
	public static final Port ULTRASONIC_SENSOR_PORT = SensorPort.S4;
	/** TODO */
	public static final double WHEEL_DIAMETER = 5.56;
	/** TODO */
	public static final double TRACK_WIDTH = 12;
	/** TODO */
	public static final double TURN_RADIUS = 10;
	/** TODO */
	public static final double TRAVEL_SPEED = 20;
}
