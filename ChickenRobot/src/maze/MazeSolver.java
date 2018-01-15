package maze;

import lejos.hardware.Button;
import robot.Robot;
import robot.RobotConfig;
import sensors.ColorSensor;
import sensors.TouchSensor;
import sensors.UltrasonicSensor;

/**
 * The {@link MazeSolver} class defines movement algorithms used by the robot.
 */
public class MazeSolver {
	
	/**
	 * Search the exit of the maze.
	 *
	 * @param robot
	 */
	public static void solve(final Robot robot) {
		//Initialize sensors
		final TouchSensor touchSensor = new TouchSensor();
		final UltrasonicSensor ultrasonicSensor = new UltrasonicSensor();
		final ColorSensor colorSensor = new ColorSensor();

		float distance = 0.5f; //Average distance to the wall
		int cpt = 0; //Counts when the robot is turning right or left

		robot.forward();
		waitForRed(colorSensor);
		
		//Begin the main maze resolution algorithm
		do {
			float sample = ultrasonicSensor.getDistance();
			final boolean isPressed = touchSensor.isPressed();
			
			//Next to a wall
			if ((Math.abs(distance - sample) <= RobotConfig.ULTRASONIC_SENSOR_MARGIN || cpt == 0) && !isPressed && !robot.isMoving()) {
				sample = ultrasonicSensor.getDistance();
				robot.forward();
			}
			
			//Too far from a wall
			if ((sample > distance + RobotConfig.MAXIMUM_DEVIATION || sample > RobotConfig.MAXIMUM_WALL_DISTANCE) && cpt != 0) {
				robot.stop();
				robot.turnLeft();
				robot.travel(35); //Reach the next wall
				sample = ultrasonicSensor.getDistance();
				distance = sample;
				cpt++;
			}

			//When the robot is too close from the wall, step it away
			if (sample < RobotConfig.MINIMUM_WALL_DISTANCE) {
				robot.moveAside();
			}
			
			//Facing a wall
			if (isPressed) {
				robot.stop();
				robot.turnRight();
				cpt--;
				sample = ultrasonicSensor.getDistance();
				distance = sample;
			}
			
		} while (Button.ESCAPE.isUp());
		
		//Close resources
		touchSensor.close();
		ultrasonicSensor.close();
		colorSensor.close();
	}

	/**
	 * Waits for the sensor to detect the red color, and stops the program.
	 *
	 * @param colorSensor the color sensor to use
	 */
	private static void waitForRed(final ColorSensor colorSensor) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!colorSensor.redIsFound()) {
					//wait
				}
				//TODO Make a sound ?
				System.exit(0);
			}
		}).start();
	}
}
