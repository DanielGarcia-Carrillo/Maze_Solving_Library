package Solver.heuristics;

import model.nodes.Position;

/**
 * A* maze solving algorithm using manhattan distance for heuristic
 */
public class ManhattanDistance extends Heuristic {

	@Override
	public int calculateCost(Position start, Position end) {
		// Uses manhattan distance from current to end position
		int xDifference = Math.abs(start.getX() - end.getX());
		int yDifference = Math.abs(start.getY() - end.getY());
		return straightDistanceWeight* (xDifference + yDifference);
	}
}
