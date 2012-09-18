package Solver.heuristics;

import model.nodes.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiagonalDistanceTest {
	private final Heuristic diagonalMetric = new DiagonalDistance();
	private final int lateralDistanceWeight = diagonalMetric.getStraightDistanceWeight();
	private final int diagonalDistanceWeight = diagonalMetric.getDiagonalDistanceWeight();

	/**
	 * Makes sure that the distance doesn't change when flipping order of input
	 */
	@Test
	public void testSymmetry() {
		Position first = new Position(1,2);
		Position second = new Position(4, 1);

		int firstDistance = diagonalMetric.calculateCost(first, second);
		int secondDistance = diagonalMetric.calculateCost(second, first);

		assertTrue(firstDistance == secondDistance);
	}

	/**
	 * This test ensures that diagonal neighbors are 1 diagonal weight away
	 */
	@Test
	public void testDiagonalNeighbors() {
		Position topLeft = new Position(0,0);
		Position bottomRight = new Position(1,1);

		int distance = diagonalMetric.calculateCost(topLeft, bottomRight);
		assertEquals(diagonalDistanceWeight, distance);

		Position topRight = new Position(0,1);
		Position bottomLeft = new Position(1,0);

		distance = diagonalMetric.calculateCost(topRight, bottomLeft);
		assertEquals(diagonalDistanceWeight, distance);

	}

	/**
	 * Tests whether neighbors that share a side get a distance equal to one weight unit
	 */
	@Test
	public void testLateralNeighbors() {
		Position left = new Position(0,0);
		Position right = new Position(1,0);

		int distance = diagonalMetric.calculateCost(left, right);
		assertEquals(lateralDistanceWeight, distance);

		Position top = new Position(0,0);
		Position bottom = new Position(0,1);

		distance = diagonalMetric.calculateCost(top, bottom);
		assertEquals(lateralDistanceWeight, distance);
	}

	@Test
	public void testLongerPath() {
		Position start = new Position(0,0);
		Position end = new Position(2,4);

		int distance = diagonalMetric.calculateCost(start, end);
		assertEquals(2*(diagonalDistanceWeight + lateralDistanceWeight), distance);
	}
}
