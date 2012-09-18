package Solver.heuristics;

import model.nodes.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EuclideanDistanceTest {
	private final Heuristic euclideanMetric = new EuclideanDistance();
	private final int lateralDistanceWeight = Heuristic.getStraightDistanceWeight();

	/**
	 * Makes sure that the distance doesn't change when flipping order of input
	 */
	@Test
	public void testSymmetry() {
		Position first = new Position(1,2);
		Position second = new Position(4, 1);

		int firstDistance = euclideanMetric.calculateCost(first, second);
		int secondDistance = euclideanMetric.calculateCost(second, first);

		assertTrue(firstDistance == secondDistance);
	}

	/**
	 * This test ensures that diagonal neighbors are equal to the
	 * integer rounding of sqrt(2)*distanceWeight
	 */
	@Test
	public void testDiagonalNeighbors() {
		Position topLeft = new Position(0,0);
		Position bottomRight = new Position(1,1);

		int distance = euclideanMetric.calculateCost(topLeft, bottomRight);
		assertEquals((int) (Math.sqrt(2) * lateralDistanceWeight), distance);

		Position topRight = new Position(0,1);
		Position bottomLeft = new Position(1,0);

		distance = euclideanMetric.calculateCost(topRight, bottomLeft);
		assertEquals((int)(Math.sqrt(2)*lateralDistanceWeight), distance);

	}

	/**
	 * Tests whether neighbors that share a side get a distance equal to one weight unit
	 */
	@Test
	public void testLateralNeighbors() {
		Position left = new Position(0,0);
		Position right = new Position(1,0);

		int distance = euclideanMetric.calculateCost(left, right);
		assertEquals(lateralDistanceWeight, distance);

		Position top = new Position(0,0);
		Position bottom = new Position(0,1);

		distance = euclideanMetric.calculateCost(top, bottom);
		assertEquals(lateralDistanceWeight, distance);
	}

	/**
	 * Tests that this still works correctly for long paths
	 */
	@Test
	public void testLongPath() {
		Position start = new Position(0,0);
		Position end = new Position(20,10);

		// The distance should equal integer value of sqrt(20^2 + 10^2)*weight
		int distance = euclideanMetric.calculateCost(start, end);
		assertEquals((int) (Math.sqrt(500)*lateralDistanceWeight), distance);
	}
}
