package sensors;

import java.io.Closeable;

import lejos.hardware.sensor.EV3UltrasonicSensor;
import robot.RobotConfig;

/**
 * The {@link UltrasonicSensor} class represents the ultrasonicSensors used by the robot.
 */
public class UltrasonicSensor
implements Closeable {
	private final EV3UltrasonicSensor sensor;
	private final float[] sample;
	
	/**
	 * Contructor. Creates a new UltrasonicSensor that uses an EV3UltrasonicSensor.
	 */
	public UltrasonicSensor() {
		assert (RobotConfig.ULTRASONIC_SENSOR_PORT != null);

		sensor = new EV3UltrasonicSensor(RobotConfig.ULTRASONIC_SENSOR_PORT);
		sample = new float[sensor.getDistanceMode().sampleSize()];
	}
	
	/**
	 * Gets the distance between this sensor and the nearest obstacle, in meters.
	 *
	 * @return the distance in meters
	 */
	public float getDistance() {
		assert (sample.length >= 1);
		assert (sensor != null);
		
		sensor.getDistanceMode().fetchSample(sample, 0);
		return sample[0];
	}
	
	@Override
	public void close() {
		sensor.close();
	}
}
