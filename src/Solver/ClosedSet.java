package Solver;

import java.util.HashSet;
import java.util.Set;

/**
 * Used for indicating if we've evaluated a given node
 * @param <Node> type we'd like to store
 */
public class ClosedSet<Node> {
	private final Set<Node> set;

	public ClosedSet() {
		set = new HashSet<Node>();
	}

	/**
	 * Adds given node to structure, will overwrite if added again
	 * @param node
	 */
	public void add(Node node) {
		set.add(node);
	}

	/**
	 * Indicates if the given node is in this set
	 * @param node
	 * @return
	 */
	public boolean contains(Node node) {
		return set.contains(node);
	}
}
