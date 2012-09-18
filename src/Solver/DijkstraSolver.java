package Solver;

import Solver.heuristics.Heuristic;
import Solver.heuristics.NullDistance;
import model.Maze;
import model.nodes.Position;

import java.util.List;

public class DijkstraSolver implements MazeSolver {

	@Override
	public List<Position> solve(Maze maze, Heuristic heuristic) {
		/* Dijkstra's algorithm is just a specific case of A* where
		 * the heuristic function is set to 0
		 * The heuristic is still used for the purpose of calculating
		 * the accumulated cost of nodes in the maze
		 */
		MazeSolver solver = new AStarMazeSolver();
		return solver.solve(maze, new NullDistance(), heuristic);

	}

	@Override
	public List<Position> solve(Maze maze, Heuristic heuristic, Heuristic accumulated) {
		return solve(maze, heuristic);
	}
}
