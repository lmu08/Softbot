import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

/**
 * Example leJOS EV3 Project with an ant build file
 */
public class HelloWorld {

	public static void main(final String[] args) {
		touchSensorsTest();
	}

	private static void touchSensorsTest() {
		//Create motors
		final RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
		final RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);

		//Forward
		leftMotor.setSpeed(720);// 2 RPM
		rightMotor.setSpeed(720);
		leftMotor.forward();
		rightMotor.forward();
		
		//Touch sensor
		final EV3TouchSensor touchSensor1 = new EV3TouchSensor(SensorPort.S1);
		final EV3TouchSensor touchSensor2 = new EV3TouchSensor(SensorPort.S2);
		final SensorMode touch1 = touchSensor1.getTouchMode();
		final SensorMode touch2 = touchSensor2.getTouchMode();
		final float[] sample = new float[2];
		
		//Wait for obstacle
		do {
			touch1.fetchSample(sample, 0);
			touch2.fetchSample(sample, 1);
		} while (leftMotor.isMoving() && rightMotor.isMoving() && (sample[0] == 0 || sample[1] == 0));
		
		//Go backwards
		leftMotor.backward();
		rightMotor.backward();
		Delay.msDelay(1000);
		leftMotor.stop();
		rightMotor.stop();
		
		//Close resources
		leftMotor.close();
		rightMotor.close();
		touchSensor1.close();
		touchSensor2.close();
	}
	
}
