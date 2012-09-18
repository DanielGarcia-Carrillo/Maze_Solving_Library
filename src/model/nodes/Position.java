package model.nodes;

/**
 * Simple class used to indicate the x, y coordinates of a node. Additionally, carries information
 * regarding whether this is a start/endpoint or not.
 * User: Daniel
 */
public class Position {
	private final int x;
	private final int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position(Position position) {
		this.x = position.getX();
		this.y = position.getY();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	/**
	 * Two positions are considered equal if they have the same x and y
	 * coordinates
	 * @param o object that we are comparing equality with
	 * @return true if the objects are Positions with the same coordinates
	 */
	@Override
	public boolean equals(Object o) {
		// The same object reference is obviously equal
		if (o == this) {
			return true;
		}

		if (o == null || !(o instanceof Position)) {
			return false;
		}

		Position otherPosition = (Position) o;
		return otherPosition.x == this.x && otherPosition.y == this.y;
	}

	/**
	 * A position is uniquely defined by its x/y coordinates
	 * @return a hash of the x/y coordinates
	 */
	@Override
	public int hashCode() {
		int result = x+y;
		result = 31 * result + x;
		result = 31 * result + y;
		return result;
	}
}
