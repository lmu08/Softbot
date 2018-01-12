package maze;

import lejos.hardware.Button;
import lejos.utility.Delay;
import robot.Robot;
import sensors.TouchSensor;
import sensors.UltrasonicSensor;

/**
 * Class that defines the movement algorithms used by the robot.
 */
public class MazeSolver {
	
	public static void solveMaze(final Robot robot) {
		final TouchSensor touchSensor = new TouchSensor();
		final UltrasonicSensor ultrasonicSensor = new UltrasonicSensor();
		
		//Move
		robot.forward();
		
		float distance = 10000;
		int cpt = 0;
		
		do {
			//Ultra son
			final float distance2 = ultrasonicSensor.getDistance();
			
			//distance en metre
			System.out.println("sample " + distance2);
			Delay.msDelay(500);
			System.out.println("distance " + distance);
			Delay.msDelay(500);
			
			//touch sensor
			final boolean isPressed = touchSensor.isPressed();
			
			if (((distance2 >= distance - 0.01 && distance2 <= distance + 0.01) || cpt == 0) && !isPressed && !robot.isMoving()) {
				System.out.println("Is moving");
				robot.forward();
			}
			if (distance2 > distance + 0.11) {
				System.out.println("N'est plus contre le mur / distance :" + distance);
				robot.stop();
				robot.turnLeft();
				cpt++;
			}
			if (isPressed) {
				System.out.println("stop toi");
				robot.stop();
				robot.turnRight();
				cpt--;
				System.out.println("Sleep");
				Delay.msDelay(3000);
				distance = distance2;
			}
		} while (Button.ESCAPE.isUp());
		
		//Close resources
		touchSensor.close();
		ultrasonicSensor.close();
	}
	
	/**
	 * The robot reaches the wall, turns right, and stops.
	 *
	 * @param robot robot that will perform this action
	 */
	public static void reachWallAndTurnRight(final Robot robot) {
		final TouchSensor touchSensor = new TouchSensor();
		
		//Move until the wall and turn right.
		robot.forward();
		while (!touchSensor.isPressed()) {
			//Wait for an obstacle
		}
		robot.turnRight();
		
		//Close resources.
		touchSensor.close();
	}
}
