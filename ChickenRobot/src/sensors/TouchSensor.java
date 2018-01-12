package sensors;

import java.io.Closeable;

import lejos.hardware.sensor.EV3TouchSensor;
import robot.RobotConfig;

public class TouchSensor
implements Closeable {
	final EV3TouchSensor leftTouchSensor;
	final EV3TouchSensor rightTouchSensor;
	final float[] sample;
	
	public TouchSensor() {
		leftTouchSensor = new EV3TouchSensor(RobotConfig.LEFT_TOUCH_SENSOR_PORT);
		rightTouchSensor = new EV3TouchSensor(RobotConfig.RIGHT_TOUCH_SENSOR_PORT);
		sample = new float[2];
	}
	
	public boolean isPressed() {
		leftTouchSensor.getTouchMode().fetchSample(sample, 0);
		rightTouchSensor.getTouchMode().fetchSample(sample, 1);
		return (sample[0] != 0) || (sample[1] != 0);
	}
	
	@Override
	public void close() {
		leftTouchSensor.close();
		rightTouchSensor.close();
	}
}
