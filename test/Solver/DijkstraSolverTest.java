package Solver;

import Solver.heuristics.DiagonalDistance;
import Solver.heuristics.Heuristic;
import Solver.heuristics.NullDistance;
import model.Maze;
import model.nodes.Position;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DijkstraSolverTest {
	private final MazeSolver solver = new AStarMazeSolver();
	// Using simplest heuristic as not to complicate test results
	private final Heuristic heuristic = new NullDistance();

	/**
	 * The solver shouldn't freak out when the start and end positions are the same
	 */
	@Test
	public void testSimplestMaze() {
		boolean[][] simplestMaze = {{true}};
		Position start, end;
		start = end = new Position(0,0);

		assertNotNull(solver);

		List<Position> path = solver.solve(new Maze(simplestMaze, start, end), heuristic, new DiagonalDistance());

		assertNotNull(path);
		assertFalse(path.isEmpty());
		assertEquals(1, path.size());

		assertEquals(start, path.get(0));
	}

	/**
	 * Non square mazes can reveal bounds issues in solver
	 */
	@Test
	public void testRectangularMaze() {
		boolean[][] rectangle = {
			{true, false, true, false, true},
			{true, true, true, false, true},
			{false, false, true, true, true},
			{true, false, true, false, false},
			{false, true, true, true, true},
			{false, false, false, false, true}
		};

		Position start = new Position(0,0);
		Position end = new Position(5,4);

		Maze rectangleMaze = new Maze(rectangle, start, end);

		List<Position> path = solver.solve(rectangleMaze, heuristic, new DiagonalDistance());

		assertNotNull(path);
		assertFalse(path.isEmpty());
		assertEquals(6, path.size());

	}

	/**
	 * Tests that mazes with no path from start to finish will return
	 * an empty list for the path
	 */
	@Test
	public void testUnsolvableMaze() {
		boolean[][] unsolvable = {
			{true, false, false},
			{false, false, false},
			{false, false, true}
		};

		Position start = new Position(0,0);
		Position end = new Position(2,2);

		Maze unsolvableMaze = new Maze(unsolvable, start, end);

		List<Position> noPath = solver.solve(unsolvableMaze, heuristic, new DiagonalDistance());

		assertNotNull(noPath);
		assertTrue(noPath.isEmpty());
	}
}
