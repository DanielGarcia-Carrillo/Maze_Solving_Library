package Solver.heuristics;

import model.nodes.Position;

/**
 * Abstract class for the purpose of providing an interface for
 * maze solving and additionally storing common edge weights
 */
public abstract class Heuristic {
	protected static final int diagonalDistanceWeight = 141;
	protected static final int straightDistanceWeight = 100;

	/**
	 * Given the implementation of this heuristic, this will calculate
	 * the weighted cost from the start position to the end position
	 * @param start initial position
	 * @param end final position
	 * @return weighted cost from start to end
	 */
	public abstract int calculateCost(Position start, Position end);

	/**
	 * @return the constant weight that all diagonal movement is multiplied by
	 */
	public static int getDiagonalDistanceWeight() {
		return diagonalDistanceWeight;
	}

	/**
	 * @return the constant weight that all horizontal and vertical movement is multiplied by
	 */
	public static int getStraightDistanceWeight() {
		return straightDistanceWeight;
	}

}
