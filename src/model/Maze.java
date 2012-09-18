package model;

import model.nodes.MazeNode;
import model.nodes.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**\
 * model.Maze class contains implementation of model.Maze data structure
 * User: Daniel
 */
public class Maze {
	private final MazeNode[][] maze;
	private final Position startPosition;
	private final Position endPosition;
	private final int mazeWidth;
	private final int mazeHeight;

	/**
	 * Constructs a maze from a 2D boolean array
	 * @param maze boolean 2D array indicating whether the spot is passable
	 * @param start the position to start from
	 * @param end the position that we are trying to get to
	 */
    public Maze(boolean[][] maze, Position start, Position end) {
		this.mazeWidth = maze.length;
		this.mazeHeight = maze[0].length;

		this.maze = new MazeNode[mazeWidth][mazeHeight];
		for (int x=0; x < mazeWidth; x++) {
			for (int y=0; y < mazeHeight; y++) {
				this.maze[x][y] = new MazeNode(maze[x][y], new Position(x,y));
			}
		}

		startPosition = new Position(start);
		endPosition = new Position(end);
    }

	/**
	 * Constructs a maze object given some threshold of passable pixels
	 * @param image a grayscale BufferedImage that should be a maze
	 * @param passableThreshold this indicates the color at which anything brighter is impassable
	 *                          and anything equal or darker is passable.
	 * @param start where the maze path starts on the image (the given pixel coordinate)
	 * @param end where the path should end
	 */
	public Maze(BufferedImage image, Color passableThreshold, Position start, Position end) {
		this.mazeHeight = image.getHeight();
		this.mazeWidth = image.getWidth();

		this.startPosition = start;
		this.endPosition = end;

		this.maze = new MazeNode[mazeWidth][mazeHeight];
		for (int x=0; x < mazeWidth; x++) {
			for (int y=0; y < mazeHeight; y++) {
				Color currentPixel = new Color(image.getRGB(x,y));
				// A point is passable if it's not brighter
				boolean isPassable = !isBrighter(currentPixel, passableThreshold);

				Position currentPosition = new Position(x, y);
				this.maze[x][y] = new MazeNode(isPassable, currentPosition);
			}
		}
	}

	/**
	 * @return boolean value if the first color is brighter than the threshold
	 */
	private boolean isBrighter(Color color, Color threshold) {
		// The maze class should only receive grayscale images so we can just compare
		// one of the rgb values (ie all three values should be equal)
		return color.getRed() > threshold.getRed();
	}

	/**
	 * Constructs a new maze by performing a deep copy on all contents
	 * of the given maze
	 * @param maze maze to be copied
	 */
	public Maze(Maze maze) {
		this.startPosition = maze.getStartPosition();
		this.endPosition = maze.getEndPosition();

		this.mazeWidth = maze.getMazeWidth();
		this.mazeHeight = maze.getMazeHeight();

		this.maze = new MazeNode[this.mazeWidth][this.mazeHeight];
		for (int x=0; x < this.mazeWidth; x++) {
			for (int y=0; y < this.mazeHeight; y++) {
				Position current = new Position(x,y);
				this.maze[x][y] = new MazeNode(maze.isWalkable(current), current);
			}
		}
	}

	/**
	 * Indicates whether the given position is some sort of wall or passable region
	 * @param position where one would like to check walkability
	 * @return true if position is passable, false otherwise
	 */
	public boolean isWalkable(Position position) {
		if (isValidPosition(position)) {
			return maze[position.getX()][position.getY()].isWalkable();
		}
		return false;
	}

	/**
	 * @return the width of the maze
	 */
	public int getMazeWidth() {
		return this.mazeWidth;
	}

	/**
	 * @return the height of the maze
	 */
	public int getMazeHeight() {
		return this.mazeHeight;
	}

	/**
	 * Checks if the position is in range of the maze's vertical and horizontal components.
	 * @param position the position that may or may not exist in maze
	 * @return boolean value indicating whether position is in maze
	 */
	private boolean isValidPosition(Position position) {
		int x = position.getX();
		int y = position.getY();

		boolean xInRange = x >= 0 && x < maze.length;
		boolean yInRange = y >= 0 && y < maze[0].length;

		return xInRange && yInRange;
	}

	/**
	 * @return the position that the maze should be started from
	 */
	public Position getStartPosition() {
		return startPosition;
	}

	/**
	 * @return the position that indicates the goal of the maze
	 */
	public Position getEndPosition() {
		return endPosition;
	}

	/**
	 * Returns a list containing up to 8 neighbors to the given position. Will not return
	 * positions that do not exist, eg that extend past the borders of the maze.  Returns an empty
	 * list if the starting position is not valid or all neighbors are unwalkable
	 * @param position The position for which we want all adjacent positions
	 * @return a list containing all existing positions adjacent
	 */
	public List<Position> getNeighbors(Position position) {
		if (!isWalkable(position)) {
			return new ArrayList<Position>();
		}

		int x = position.getX();
		int y = position.getY();

		List<Position> neighbors = new ArrayList<Position>(8);
		// Find all neighbors by iterating through all offsets that are 1 distance away
		for (int horizontalOffset=-1; horizontalOffset <=1; horizontalOffset++) {
			for (int verticalOffset=-1; verticalOffset <=1; verticalOffset++) {
				Position currentPosition = new Position(horizontalOffset + x, verticalOffset + y);

				// This position is out of bounds or it's the original position (which we don't want)
				if (!(isWalkable(currentPosition)) ||
					(x == currentPosition.getX() && y == currentPosition.getY())) {
					continue;
				}

				// Populate list with current valid neighbor
				neighbors.add(currentPosition);
			}
		}

		return neighbors;
	}

}
