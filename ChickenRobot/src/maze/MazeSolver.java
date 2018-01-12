package maze;

import lejos.hardware.Button;
import robot.Robot;
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

		float distance = 0.5f; //Average distance to the wall
		int cpt = 0; //Counts when the robot is turning right or left

		robot.forward();

		do {
			float sample = ultrasonicSensor.getDistance();

			System.out.println("sample " + sample);
			System.out.println("distance " + distance);
			System.out.println("compteur " + cpt);

			final boolean isPressed = touchSensor.isPressed();

			//Next to a wall
			if (((sample >= distance - 0.1 && sample <= distance + 0.1) || cpt == 0) && !isPressed && !robot.isMoving()) {
				System.out.println("Is moving");
				sample = ultrasonicSensor.getDistance();
				robot.forward();
			}

			//Too far from a wall
			if (sample > distance + 0.2) {
				System.out.println("N'est plus contre le mur / distance :" + distance);
				robot.stop();
				robot.turnLeft();
				robot.travel(35.0);
				cpt++;
				sample = ultrasonicSensor.getDistance();
				distance = sample;
			}

			//Facing a wall
			if (isPressed) {
				System.out.println("stop toi");
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
	}
}
