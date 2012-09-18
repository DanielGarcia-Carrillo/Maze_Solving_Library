package model.nodes;

/**
 * MazeNode contains all the information regarding cost and position for a single
 * node in a maze
 */
public class MazeNode {
	private final boolean walkable; // Whether the given maze node is a wall or not
	private final Position position;

	public MazeNode(boolean walkable, Position position) {
		this.walkable = walkable;
		this.position = position;
	}

	/**
	 * This method only checks if the positions are equal because given a maze,
	 * only one node should be at any given location
	 * @param o potential other MazeNode that we are testing for equality
	 * @return true if the positions are the same
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MazeNode mazeNode = (MazeNode) o;

		return position.equals(mazeNode.getPosition());
	}

	/**
	 * Takes a hashcode of the object from its Position, walkable boolean and costs.
	 * The hashcode method expects the cost fields to be defined before being called
	 * @return hash of all fields but parent (hashing parent would cause infinite recursion)
	 */
	@Override
	public int hashCode() {
		int result = (walkable ? 1 : 0);
		result = 31 * result + (position != null ? position.hashCode() : 0);
		return result;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public Position getPosition() {
		return this.position;
	}

}
