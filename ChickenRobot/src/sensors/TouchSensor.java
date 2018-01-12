package sensors;

import java.io.Closeable;

import lejos.hardware.sensor.EV3TouchSensor;
import robot.RobotConfig;

/**
 * The {@link TouchSensor} class represents the 2 touch sensors used by the robot.
 */
public class TouchSensor
implements Closeable {
	private final EV3TouchSensor leftTouchSensor;
	private final EV3TouchSensor rightTouchSensor;
	private final float[] sample;

	/**
	 * Contructor. Creates a new TouchSensor that represents 2 EV3TouchSensor.
	 */
	public TouchSensor() {
		leftTouchSensor = new EV3TouchSensor(RobotConfig.LEFT_TOUCH_SENSOR_PORT);
		rightTouchSensor = new EV3TouchSensor(RobotConfig.RIGHT_TOUCH_SENSOR_PORT);
		sample = new float[2];
	}
	
	/**
	 * Checks if the sensors are being pressed.
	 *
	 * @return <code>true</code> if both sensors are pressed, <code>false</code> otherwise.
	 */
	public boolean isPressed() {
		leftTouchSensor.getTouchMode().fetchSample(sample, 0);
		rightTouchSensor.getTouchMode().fetchSample(sample, 1);
		return (sample[0] != 0) && (sample[1] != 0);
	}
	
	@Override
	public void close() {
		leftTouchSensor.close();
		rightTouchSensor.close();
	}
}
