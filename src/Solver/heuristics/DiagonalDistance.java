package Solver.heuristics;

import model.nodes.Position;

public class DiagonalDistance extends Heuristic {

	@Override
	public int calculateCost(Position start, Position end) {
		int cost = 0;
		cost += getDiagonal(start, end) * diagonalDistanceWeight;
		cost += straightDistance(start, end) * straightDistanceWeight;

		return cost;
	}

	/**
	 * This just calculates the length of the longest diagonal between
	 * the two endpoints
	 */
	private int getDiagonal(Position start, Position end) {
		int xDim = Math.abs(end.getX() - start.getX());
		int yDim = Math.abs(end.getY() - start.getY());

		return Math.min(xDim, yDim);
	}

	/**
	 * This method basically just finds the total manhattan distance
	 * from start to end and then subtracts the manhattan distance of
	 * the diagonal in the path
	 */
	private int straightDistance(Position start, Position end) {
		int xDifference = Math.abs(end.getX() - start.getX());
		int yDifference = Math.abs(end.getY() - start.getY());

		return xDifference + yDifference - 2*getDiagonal(start, end);
	}

}
