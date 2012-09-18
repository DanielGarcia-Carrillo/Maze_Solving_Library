package model;

import model.nodes.MazeNode;
import model.nodes.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MazeTest {
	private final boolean[][] rawMaze = {
		{true, false, true},
		{true, true, true},
		{false, false, true}
	};
	private final Position startPosition = new Position(0,0);
	private final Position endPosition = new Position(2,2);

	private Maze testMaze;

	@Before
	public void setUp() throws Exception {
		testMaze = new Maze(rawMaze, startPosition, endPosition);
	}

	/**
	 * Tests that all nodes marked walkable are walkable in this structure,
	 * and that the endpoints are the same
	 */
	@Test
	public void testConstructorFromBooleanMatrix() {
		Maze maze = new Maze(rawMaze, startPosition, endPosition);

		assertNotNull(maze);
		assertEquals(startPosition, maze.getStartPosition());
		assertEquals(endPosition, maze.getEndPosition());
		assertEquals(3, maze.getMazeWidth());
		assertEquals(3, maze.getMazeHeight());

		for (int x=0; x < maze.getMazeWidth(); x++) {
			for (int y=0; y < maze.getMazeHeight(); y++) {
				Position nodePosition = new Position(x, y);
				assertEquals(rawMaze[x][y], maze.isWalkable(nodePosition));
			}
		}
	}

	@Test
	public void testConstructorRectangleMaze() {
		boolean[][] boolMaze = {
			{true, false, true, false},
			{true, true, true, true},
			{false, false, true, true}
		};

		Maze maze = new Maze(boolMaze, startPosition, endPosition);

		assertNotNull(maze);
		assertEquals(startPosition, maze.getStartPosition());
		assertEquals(endPosition, maze.getEndPosition());
		assertEquals(3, maze.getMazeWidth());
		assertEquals(4, maze.getMazeHeight());
	}

	/**
	 * Tests that the copy constructor actually gets the relevant information and
	 * doesn't copy the costs and such
	 */
	@Test
	public void testCopyConstructor() {
		Maze copiedMaze = new Maze(testMaze);

		assertNotNull(copiedMaze);
		assertEquals(copiedMaze.getStartPosition(), testMaze.getStartPosition());
		assertEquals(copiedMaze.getEndPosition(), testMaze.getEndPosition());
		assertEquals(copiedMaze.getMazeHeight(), testMaze.getMazeHeight());
		assertEquals(copiedMaze.getMazeWidth(), testMaze.getMazeWidth());

		for (int x=0; x < copiedMaze.getMazeWidth(); x++) {
			for (int y=0; y < copiedMaze.getMazeHeight(); y++) {
				Position nodePosition = new Position(x,y);
				assertEquals(testMaze.isWalkable(nodePosition), copiedMaze.isWalkable(nodePosition));
			}
		}
	}

	@Test
	public void testGetNeighbors() {
		Position center = new Position(1,1);
		List<Position> neighbors = testMaze.getNeighbors(center);

		assertNotNull(neighbors);
		assertFalse(neighbors.isEmpty());
		assertEquals(5, neighbors.size());
	}

	@Test
	public void testGetNeighbors_edgeCases() {
		Position bottomLeft = new Position(2,0);
		List<Position> neighbors = testMaze.getNeighbors(bottomLeft);
		assertTrue(neighbors.isEmpty()); // This position is unwalkable

		Position bottomRight = new Position(2,2);
		neighbors = testMaze.getNeighbors(bottomRight);
		assertEquals(2, neighbors.size());
	}

}
