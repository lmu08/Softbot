package maze;

import java.util.concurrent.TimeUnit;

import lejos.hardware.Button;
import lejos.utility.Delay;
import robot.Robot;
import sensors.ColorSensor;
import sensors.TouchSensor;
import sensors.UltrasonicSensor;

/**
 * Class that defines the movement algorithms used by the robot.
 */
public class MazeSolver {
	
	public static void solveMaze(final Robot robot) {
		final TouchSensor touchSensor = new TouchSensor();
		final UltrasonicSensor ultrasonicSensor = new UltrasonicSensor();
		final ColorSensor colorSensor = new ColorSensor();
		
		//Move
		robot.forward();
		
		float distance = 0.5f;
		int cpt = 0;
		
		do {
			//Ultra son
			float sample = ultrasonicSensor.getDistance();
			
			//distance en metre
			System.out.println("sample " + sample);
			System.out.println("distance " + distance);
			System.out.println("compteur " + cpt);
			
			//touch sensor
			final boolean isPressed = touchSensor.isPressed();
			
			//color sensor
			final boolean redIsFound = colorSensor.redIsFound();
			
			//Cas où le robot est à côté d'un mur
			if (((sample >= distance - 0.1 && sample <= distance + 0.1) || cpt == 0) && !isPressed && !robot.isMoving()) {
				System.out.println("Is moving");
				sample = ultrasonicSensor.getDistance();
				robot.forward();
			}
			
			//Cas où le robot n'est plus à côté d'un mur
			if ((sample > distance + 0.15 || sample > 0.4) && cpt != 0) {
				System.out.println("N'est plus contre le mur / distance :" + distance);
				robot.stop();
				robot.turnLeft();
				robot.travel(35.0);
				sample = ultrasonicSensor.getDistance();
				distance = sample;
				cpt++;
			}
			
			//Recalibrage du robot si trop proche du mur
			if(sample < 0.05) {
				robot.moveAside();
			}
			
			//Si le robot touche un obstacle/mur
			if (isPressed) {
				//Ultra son				
				System.out.println("stop toi");
				robot.stop();
				robot.turnRight();
				cpt--;
				sample = ultrasonicSensor.getDistance();
				distance = sample;
			}
			
			//Si le robot arrive à la ligne rouge
			if(redIsFound) {
				robot.stop();
				System.out.println("Je suis arrivé");
				robot.travel(17.0);
				robot.stop();
				System.exit(0);
			}
		} while (Button.ESCAPE.isUp());
		
		//Close resources
		touchSensor.close();
		ultrasonicSensor.close();
		colorSensor.close();
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
