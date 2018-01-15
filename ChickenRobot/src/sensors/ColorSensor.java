package sensors;

import java.io.Closeable;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import robot.RobotConfig;

/**
 * The {@link ColorSensor} class represents the color sensor used by the robot.
 */
public class ColorSensor
implements Closeable {
	final EV3ColorSensor colorSensor;
	final SensorMode colorMode;
	final float[] sample;
	
	/**
	 * Constructor. Creates a new color sensor that uses the colorId mode.
	 */
	public ColorSensor() {
		colorSensor = new EV3ColorSensor(RobotConfig.COLOR_SENSOR_PORT);
		colorMode = colorSensor.getColorIDMode();
		sample = new float[colorMode.sampleSize()];
	}

	/**
	 * Checks if the red color is detected by the sensor.
	 *
	 * @return <code>true</code> if red is found, <code>false</code> otherwise
	 */
	public boolean redIsFound() {
		colorMode.fetchSample(sample, 0);
		return sample[0] == 0;
	}
	
	@Override
	public void close() {
		colorSensor.close();
	}
}
