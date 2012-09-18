package Solver;

import Solver.heuristics.Heuristic;
import model.Maze;
import model.nodes.Position;

import java.util.List;

/**
 * User: Daniel
 */
public interface MazeSolver {

	/**
	 *
	 * @param maze
	 * @param heuristic
	 * @return
	 */
	public List<Position> solve(Maze maze, Heuristic heuristic);

	/**
	 *
	 * @param maze
	 * @param heuristic
	 * @param accumulated
	 * @return
	 */
	public List<Position> solve(Maze maze, Heuristic heuristic, Heuristic accumulated);
}
