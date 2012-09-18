package Solver.heuristics;

import model.nodes.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EuclideanDistanceSquaredTest {
	private final Heuristic euclideanSquaredMetric = new EuclideanDistanceSquared();
	private final int lateralDistanceWeight = Heuristic.getStraightDistanceWeight();

	/**
	 * Makes sure that the distance doesn't change when flipping order of input
	 */
	@Test
	public void testSymmetry() {
		Position first = new Position(1,2);
		Position second = new Position(4, 1);

		int firstDistance = euclideanSquaredMetric.calculateCost(first, second);
		int secondDistance = euclideanSquaredMetric.calculateCost(second, first);

		assertTrue(firstDistance == secondDistance);
	}

	/**
	 * This test ensures that diagonal neighbors are equal to the
	 * integer rounding of 2*distanceWeight
	 */
	@Test
	public void testDiagonalNeighbors() {
		Position topLeft = new Position(0,0);
		Position bottomRight = new Position(1,1);

		int distance = euclideanSquaredMetric.calculateCost(topLeft, bottomRight);
		assertEquals(2 * lateralDistanceWeight, distance);

		Position topRight = new Position(0,1);
		Position bottomLeft = new Position(1,0);

		distance = euclideanSquaredMetric.calculateCost(topRight, bottomLeft);
		assertEquals(2*lateralDistanceWeight, distance);

	}

	/**
	 * Tests whether neighbors that share a side get a distance equal to one weight unit
	 */
	@Test
	public void testLateralNeighbors() {
		Position left = new Position(0,0);
		Position right = new Position(1,0);

		int distance = euclideanSquaredMetric.calculateCost(left, right);
		assertEquals(lateralDistanceWeight, distance);

		Position top = new Position(0,0);
		Position bottom = new Position(0,1);

		distance = euclideanSquaredMetric.calculateCost(top, bottom);
		assertEquals(lateralDistanceWeight, distance);
	}

	/**
	 * Tests that this still works correctly for long paths
	 */
	@Test
	public void testLongPath() {
		Position start = new Position(0,0);
		Position end = new Position(20,10);

		// The distance should equal integer value of (20^2 + 10^2)*weight
		int distance = euclideanSquaredMetric.calculateCost(start, end);
		assertEquals(500*lateralDistanceWeight, distance);
	}
}
