package Solver.heuristics;

import model.nodes.Position;

/**
 * NullDistance just gives a value of 0 for the distance
 * between all positions
 */
public class NullDistance extends Heuristic {
	@Override
	public int calculateCost(Position start, Position end) {
		return 0;
	}

}
