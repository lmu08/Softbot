package sensors;

import java.io.Closeable;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import robot.RobotConfig;

public class ColorSensor
implements Closeable {	
	final EV3ColorSensor colorSensor;
	final SensorMode colorMode;
	final float[] sample;

	public ColorSensor() {
		colorSensor = new EV3ColorSensor(RobotConfig.COLOR_SENSOR_PORT);
		colorMode = colorSensor.getColorIDMode();
		sample =  new float[colorMode.sampleSize()];		
	}
	
	public boolean redIsFound() {
		colorMode.fetchSample(sample, 0);
		return sample[0] == 0;
	}
	
	@Override
	public void close() {
		colorSensor.close();
	}
}
