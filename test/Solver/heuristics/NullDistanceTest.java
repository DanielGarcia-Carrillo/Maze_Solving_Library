package Solver.heuristics;

import model.nodes.Position;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class NullDistanceTest {
	private final Heuristic nullMetric = new NullDistance();

	/**
	 * Tests that it is zero ALWAYS
	 */
	@Test
	public void testNullity() {
		Position first = new Position(0,0);
		Position second = new Position(5,102092);

		assertEquals(0, nullMetric.calculateCost(first, second));
	}
}
