package Solver.heuristics;

import model.nodes.Position;

/**
 * Similar to regular manhattan distance but includes a small bias
 * per unit length
 * This bias here is specifically 1/perimeter(maze)
 */
public class ManhattanDistanceWithTieBreaking extends Heuristic {
	private final int width;
	private final int height;

	public ManhattanDistanceWithTieBreaking(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public int calculateCost(Position start, Position end) {
		int xDistance = Math.abs(start.getX() - end.getX());
		int yDistance = Math.abs(start.getY() - end.getY());

		return (int) (straightDistanceWeight*(xDistance + yDistance)*(1.0+tieBias()));
	}

	/**
	 * For purposes of tiebreaking, the bias should be less than
	 * 1/(longest possible path) The perimeter of the maze should thus
	 * be an adequate estimate for the longest possible path, in which it isn't
	 * too large to greatly affect the path but not too conservatively small
	 * as to be negligible
	 */
	private double tieBias() {
		return 1.0/(2*(width+height));
	}
}
