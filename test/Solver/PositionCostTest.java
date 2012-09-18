package Solver;

import model.nodes.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PositionCostTest {
	PositionCost first;
	PositionCost identicalToFirst;
	PositionCost second;

	@Before
	public void setUp() throws Exception {
		first = new PositionCost(new Position(0,0));
		identicalToFirst = new PositionCost(new Position(0,0));
		second = new PositionCost(new Position(1,1));
	}

	/**
	 * Makes sure that the comparison is done by total cost and that
	 * it correctly gives values
	 */
	@Test
	public void testComparison() {
		first.setAccumulatedCost(5);
		first.setHeuristicCost(5);

		second.setAccumulatedCost(5);
		second.setHeuristicCost(4);

		assertTrue(first.compareTo(second) > 0);

		second.setHeuristicCost(5);
		assertTrue(first.compareTo(second) == 0);

		second.setHeuristicCost(6);
		assertTrue(first.compareTo(second) < 0);
	}

	/**
	 * Tests that same object instances and objects with same position are equal
	 * Tests that different objects obviously aren't
	 */
	@Test
	public void testEquals() {
		assertTrue(first.equals(first));
		assertTrue(first.equals(identicalToFirst));

		assertFalse(first.equals(second));
	}

	/**
	 * Tests that nothing funny happens when we get the total cost
	 */
	@Test
	public void testTotalCost() {
		first.setAccumulatedCost(5);
		first.setHeuristicCost(10);

		assertEquals(15, first.getTotalCost());
	}
}
