package maze;

import robot.Robot;

public class Main {
	public static void main(final String[] args) {
		final Robot robot = new Robot();
		MazeSolver.solve(robot);
	}
}
