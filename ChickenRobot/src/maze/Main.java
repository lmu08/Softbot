package maze;

import robot.Robot;

public class Main {
	public static void main(final String[] args) {
		try {
			final Robot robot = new Robot();
			MazeSolver.solve(robot);
		} catch (final IllegalArgumentException e) { //"Invalid sensor mode" error occurs randomly
			System.out.println("An error occured. Detailed message :");
			e.printStackTrace();
			System.out.println("Restarting...");
			main(new String[0]); //Not sure if this is a great idea tho
		}
	}
}
