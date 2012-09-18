package model.nodes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * User: garciac2
 */
public class PositionTest {
	/**
	 * Regular case testing that a identical positions are equal
	 * and different ones aren't
	 */
	@Test
	public void testEquals() {
		Position first = new Position(0,0);
		Position identicalToFirst = new Position(0,0);
		Position second = new Position(1,1);

		assertTrue(first.equals(identicalToFirst));
		assertFalse(first.equals(second));
	}

	/**
	 * Tests to make sure that two objects referencing same object are equal
	 */
	@Test
	public void testEquals_sameReference() {
		Position first = new Position(0,0);
		Position second = first;

		assertTrue(first.equals(second));
	}

	/**
	 * Tests that a position will not throw an exception or equal and object
	 * that is not even the same type
	 */
	@Test
	public void testEquals_invalidObjects() {
		Position goodPosition = new Position(0,0);
		Object arbitraryObject = new Object();
		Position nullPosition = null;

		assertFalse(goodPosition.equals(arbitraryObject));
		assertFalse(goodPosition.equals(nullPosition));
	}

	/**
	 * Tests that we can use the copy constructor to create new Position objects
	 */
	@Test
	public void testCopyConstructor() {
		Position position = new Position(0,0);
		Position copiedPosition = new Position(position);

		assertTrue(position.equals(copiedPosition));
	}

	@Test
	public void testToString() {
		Position position = new Position(4,-4);

		assertEquals("(4, -4)", position.toString());
	}

}
