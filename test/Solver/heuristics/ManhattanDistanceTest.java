package Solver.heuristics;

import Solver.MazeSolver;
import model.Maze;
import model.nodes.Position;
import org.junit.Test;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.junit.Assert.*;

public class ManhattanDistanceTest {
	private final Heuristic manhattanMetric = new ManhattanDistance();
	private final int lateralDistanceWeight = manhattanMetric.getStraightDistanceWeight();

	/**
	 * Makes sure that the distance doesn't change when flipping order of input
	 */
	@Test
	public void testSymmetry() {
		Position first = new Position(1,2);
		Position second = new Position(4, 1);

		int firstDistance = manhattanMetric.calculateCost(first, second);
		int secondDistance = manhattanMetric.calculateCost(second, first);

		assertTrue(firstDistance == secondDistance);
	}

	/**
	 * This test ensures that diagonal neighbors are 2 times the horizontal distance weight
	 * away from each other because of manhattan distance definition
	 */
	@Test
	public void testDiagonalNeighbors() {
		Position topLeft = new Position(0,0);
		Position bottomRight = new Position(1,1);

		int distance = manhattanMetric.calculateCost(topLeft, bottomRight);
		assertEquals(lateralDistanceWeight * 2, distance);

		Position topRight = new Position(0,1);
		Position bottomLeft = new Position(1,0);

		distance = manhattanMetric.calculateCost(topRight, bottomLeft);
		assertEquals(lateralDistanceWeight*2, distance);

	}

	/**
	 * Tests whether neighbors that share a side get a distance equal to one weight unit
	 */
	@Test
	public void testLateralNeighbors() {
		Position left = new Position(0,0);
		Position right = new Position(1,0);

		int distance = manhattanMetric.calculateCost(left, right);
		assertEquals(lateralDistanceWeight, distance);

		Position top = new Position(0,0);
		Position bottom = new Position(0,1);

		distance = manhattanMetric.calculateCost(top, bottom);
		assertEquals(lateralDistanceWeight, distance);
	}
}
