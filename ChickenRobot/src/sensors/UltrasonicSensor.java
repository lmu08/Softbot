package sensors;

import java.io.Closeable;

import lejos.hardware.sensor.EV3UltrasonicSensor;
import robot.RobotConfig;

public class UltrasonicSensor
implements Closeable {
	final EV3UltrasonicSensor sensor;
	final float[] sample;

	public UltrasonicSensor() {
		sensor = new EV3UltrasonicSensor(RobotConfig.ULTRASONIC_SENSOR_PORT);
		sample = new float[sensor.getDistanceMode().sampleSize()];
	}

	public float getDistance() {
		sensor.getDistanceMode().fetchSample(sample, 0);
		return sample[0];
	}

	@Override
	public void close() {
		sensor.close();
	}
}
