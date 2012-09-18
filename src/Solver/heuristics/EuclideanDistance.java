package Solver.heuristics;

import model.nodes.Position;

/**
 * A heuristic that uses the integer approximation of the true
 * cartesian distance between two points
 */
public class EuclideanDistance extends Heuristic {

	@Override
	public int calculateCost(Position start, Position end) {
		int xSquared = squaredDifference(start.getX(), end.getX());
		int ySquared = squaredDifference(start.getY(), end.getY());

		return (int) (Math.sqrt(xSquared+ySquared)*straightDistanceWeight);
	}

	private int squaredDifference(int a, int b) {
		return (a-b) * (a-b);
	}
}
