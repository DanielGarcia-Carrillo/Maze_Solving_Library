package Solver.heuristics;

import model.nodes.Position;

/**
 * This class calculates cost using the square of the true
 * distance between two points. Somewhat advantageous over
 * actual distance by avoiding costly sqrt() call and exhibits
 * no rounding errors due to integer rounding of sqrt()
 */
public class EuclideanDistanceSquared extends Heuristic {

	@Override
	public int calculateCost(Position start, Position end) {
		int xDistance = squaredDifference(start.getX(), end.getX());
		int yDistance = squaredDifference(start.getY(), end.getY());

		return straightDistanceWeight*(xDistance + yDistance);
	}

	private int squaredDifference(int a, int b) {
		return (a-b)*(a-b);
	}
}
