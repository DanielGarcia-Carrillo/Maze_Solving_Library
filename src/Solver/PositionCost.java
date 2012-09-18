package Solver;

import model.nodes.Position;

/**
 * Basically just represents some vertex in a graph, having
 * both the position and the associated costs
 */
public class PositionCost implements Comparable<PositionCost> {
	private final Position position;
	private int accumulatedCost;
	private int heuristicCost;

	/**
	 * Constructor
	 * @param position
	 */
	public PositionCost(Position position) {
		this.position = position;
	}

	/**
	 * Required for Comparable interface. Returns negative, zero, or positive integer
	 * based on relation to given object
	 * @param other
	 * @return negative, zero, positive for following situations respectively
	 * lower total cost, equal, higher total cost than given object
	 */
	@Override
	public int compareTo(PositionCost other) {
		return this.getTotalCost() - other.getTotalCost();
	}

	/**
	 * @return accumulatedCost
	 */
	public int getAccumulatedCost() {
		return accumulatedCost;
	}

	/**
	 * @return heuristicCost
	 */
	public int getHeuristicCost() {
		return heuristicCost;
	}

	/**
	 * @return position represented by this object
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Plainly sets accumulatedCost
	 * @param accumulatedCost
	 */
	public void setAccumulatedCost(int accumulatedCost) {
		this.accumulatedCost = accumulatedCost;
	}

	/**
	 * Sets this objects heuristic cost
	 * @param heuristicCost
	 */
	public void setHeuristicCost(int heuristicCost) {
		this.heuristicCost = heuristicCost;
	}

	/**
	 * @return the sum of the heuristic and accumulated costs
	 */
	public int getTotalCost() {
		return getHeuristicCost() + getAccumulatedCost();
	}

	/**
	 * The only information that is guaranteed to remain static is the position
	 * so we want to use only position to create a stable hashcode
	 * @return hashcode of this PositionCost instance
	 */
	@Override
	public int hashCode() {
		return position != null ? position.hashCode() : 0;
	}

	/**
	 * I consider to PositionCosts to be equal if they have the same position
	 * @param object some object that is potentially a PositionCost
	 * @return a boolean indicating that these two PositionCosts are equal
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;

		PositionCost that = (PositionCost) object;

		if (position != null ? !position.equals(that.position) : that.position != null) return false;

		return true;
	}
}
