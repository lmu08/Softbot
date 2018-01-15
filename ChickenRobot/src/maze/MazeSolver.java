package maze;

import java.io.File;
import java.util.Objects;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import robot.Robot;
import robot.RobotConfig;
import sensors.ColorSensor;
import sensors.TouchSensor;
import sensors.UltrasonicSensor;

/**
 * The {@link MazeSolver} class defines movement algorithms used by the robot.
 */
public class MazeSolver {
	private static boolean mazeSolved = false;
	private static boolean aborted = false;
	
	/**
	 * Search the exit of the maze.
	 *
	 * @param robot the robot used to solve the maze
	 */
	public static void solve(final Robot robot) {
		Objects.requireNonNull(robot, "robot cannot be null");
		assert (RobotConfig.ULTRASONIC_SENSOR_MARGIN > 0);
		assert (RobotConfig.MAXIMUM_DEVIATION > 0);
		assert (RobotConfig.MAXIMUM_WALL_DISTANCE > 0);
		assert (RobotConfig.MINIMUM_WALL_DISTANCE > 0);
		
		//Initialize sensors
		final TouchSensor touchSensor = new TouchSensor();
		final UltrasonicSensor ultrasonicSensor = new UltrasonicSensor();
		final Thread redChecker = waitForRed(robot);
		
		try {
			float distance = 0.5f; //Average distance to the wall
			int cpt = 0; //Counts when the robot is turning right or left
			
			robot.forward();
			
			//Begin the main maze resolution algorithm
			do {
				float sample = ultrasonicSensor.getDistance();
				final boolean isPressed = touchSensor.isPressed();
				
				//On red color
				if (!redChecker.isAlive()) {
					mazeSolved = true;
					robot.stop();
					Sound.playSample(new File("/home/root/secret.wav"));
					Delay.msDelay(1500);
					return;
				}
				
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
			aborted = true;
		} finally {
			//Close resources
			touchSensor.close();
			ultrasonicSensor.close();
		}
	}

	/**
	 * Waits for the sensor to detect the red color, and stops the program.
	 *
	 * @param colorSensor the color sensor to use
	 * @return
	 */
	private static Thread waitForRed(final Robot robot) {
		assert (robot != null);
		
		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				final ColorSensor colorSensor = new ColorSensor();
				while (!colorSensor.redIsFound()) {
					//wait
				}
				colorSensor.close();
			}
		});
		thread.setDaemon(true);
		thread.start();
		return thread;
	}

	public static boolean isMazeSolved() {
		return mazeSolved;
	}

	public static boolean isResolutionAborted() {
		return aborted;
	}
}
