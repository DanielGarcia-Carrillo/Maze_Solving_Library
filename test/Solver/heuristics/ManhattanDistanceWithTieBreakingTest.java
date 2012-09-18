package Solver.heuristics;

import model.nodes.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ManhattanDistanceWithTieBreakingTest {
	private Heuristic manhattanWithTies;
	private final int lateralDistanceWeight = Heuristic.getStraightDistanceWeight();

	/**
	 * Makes sure that the distance doesn't change when flipping order of input
	 */
	@Test
	public void testSymmetry() {
		manhattanWithTies = new ManhattanDistanceWithTieBreaking(32432,987987);
		Position first = new Position(1,2);
		Position second = new Position(4, 1);

		int firstDistance = manhattanWithTies.calculateCost(first, second);
		int secondDistance = manhattanWithTies.calculateCost(second, first);

		assertTrue(firstDistance == secondDistance);
	}


	/**
	 * This test ensures that diagonal neighbors are 2*weight*(1+1/maze perimeter)
	 */
	@Test
	public void testDiagonalNeighbors() {
		manhattanWithTies = new ManhattanDistanceWithTieBreaking(2,2);
		Position topLeft = new Position(0,0);
		Position bottomRight = new Position(1,1);

		int distance = manhattanWithTies.calculateCost(topLeft, bottomRight);
		int expectedDistance = (int) (2*lateralDistanceWeight*(1+1/8.));
		assertEquals(expectedDistance, distance);

		Position topRight = new Position(0,1);
		Position bottomLeft = new Position(1,0);

		distance = manhattanWithTies.calculateCost(topRight, bottomLeft);
		assertEquals(expectedDistance, distance);

	}

	/**
	 * Tests whether neighbors that share a side get a distance equal to one weight* (1+bias)
	 * This bias equals 1/perimeter which in this test is 1/8 = .125
	 */
	@Test
	public void testLateralNeighbors() {
		manhattanWithTies = new ManhattanDistanceWithTieBreaking(2,2);

		Position left = new Position(0,0);
		Position right = new Position(1,0);

		int distance = manhattanWithTies.calculateCost(left, right);
		int expectedDistance = (int) (lateralDistanceWeight*1.125);
		assertEquals(expectedDistance, distance);

		Position top = new Position(0,0);
		Position bottom = new Position(0,1);

		distance = manhattanWithTies.calculateCost(top, bottom);
		assertEquals(expectedDistance, distance);
	}

}
