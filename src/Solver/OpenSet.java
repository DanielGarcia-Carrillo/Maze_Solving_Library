package Solver;


import java.util.Comparator;
import java.util.PriorityQueue;

public class OpenSet<Node> {
	private final PriorityQueue<Node> set;

	public OpenSet() {
		set = new PriorityQueue<Node>();
	}

	public OpenSet(Comparator<Node> comp) {
		set = new PriorityQueue<Node>(32, comp);
	}

	/**
	 * takes the best Node from the set and removes it from
	 * the set
	 */
	public Node removeBest() {
		return set.poll();
	}

	/**
	 * Adds a node to the set, will overwrite if duplicated
	 * @param node
	 */
	public void add(Node node) {
		set.add(node);
	}

	/**
	 * Checks if there are no nodes in the set
	 * @return
	 */
	public boolean isEmpty() {
		return set.isEmpty();
	}

	/**
	 * Indicates that the given node is in the set
	 * @param node
	 * @return boolean value
	 */
	public boolean contains(Node node) {
		return set.contains(node);
	}

	/**
	 * Removes node and indicates whether it existed in the set
	 * @param node
	 * @return boolean value
	 */
	public boolean remove(Node node) {
		return set.remove(node);
	}
}
