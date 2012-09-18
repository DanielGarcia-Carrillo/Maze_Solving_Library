package model.nodes;

import org.junit.Test;

import static org.junit.Assert.*;

public class MazeNodeTest {

	/**
	 * This test checks to see if the constructor parameters are
	 * set properly
	 */
	@Test
	public void testConstructor() {
		MazeNode node = new MazeNode(true, new Position(0,0));

		assertNotNull(node);
		assertEquals(new Position(0,0), node.getPosition());
		assertEquals(true, node.isWalkable());
	}

/*	*//**
	 * Tests to make sure that the total cost returns the sum of all
	 * costs in the MazeNode, furthermore checks that the totalCost
	 * update appropriately when we change the values of either cost
	 *//*
	@Test
	public void testTotalCost() {
		MazeNode node = new MazeNode(true, new Position(0,0));

		node.setAccumulatedCost(3);
		node.setHeuristicCost(3);

		assertEquals(6, node.getTotalCost());

		node.setAccumulatedCost(4);
		assertEquals(7, node.getTotalCost());

		node.setHeuristicCost(4);
		assertEquals(8, node.getTotalCost());
	}*/

/*	*//**
	 * Tests comparator for this class, makes sure that all three separate compare cases
	 * work (less than, equal to, greater than)
	 *//*
	@Test
	public void testCompareTo() {
		MazeNode first = new MazeNode(true, new Position(0,0));
		MazeNode second = new MazeNode(true, new Position(0,0));

		first.setAccumulatedCost(4);
		first.setHeuristicCost(3);

		second.setAccumulatedCost(4);
		second.setHeuristicCost(4);

		assertEquals(-1, first.compareTo(second));

		first.setHeuristicCost(4);
		assertEquals(0, first.compareTo(second));

		first.setHeuristicCost(5);
		assertEquals(1, first.compareTo(second));

	}*/

	/**
	 * Makes sure that based on our definition of equals, that two nodes with
	 * the same position get evaluated to equal and different nodes do not.
	 */
	@Test
	public void testEquals() {
		Position firstPosition = new Position(2,2);
		MazeNode first = new MazeNode(true, firstPosition);
		MazeNode identicalToFirst = new MazeNode(true, firstPosition);
		MazeNode stillEqualToFirst = new MazeNode(false, firstPosition);

		MazeNode clearlyDifferent = new MazeNode(true, new Position(1,1));

		assertTrue(first.equals(identicalToFirst));
		assertTrue(first.equals(stillEqualToFirst));
		assertFalse(first.equals(clearlyDifferent));
	}
/*
	*//**
	 * Tests that we can set the parent to a mazenode and that this node
	 * has the same position as what we set
	 *//*
	@Test
	public void testSetParent() {
		MazeNode child = new MazeNode(true, new Position(1,1));
		MazeNode parent = new MazeNode(true, new Position(1,0));

		child.setParent(parent);

		assertNotNull(child.getParent());
		assertTrue(child.getParent() == parent);
		assertTrue(child.getParent().equals(parent));
	}*/
}
