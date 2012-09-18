package Solver;

import Solver.heuristics.Heuristic;
import model.Maze;
import model.nodes.Position;

import java.util.*;

/**
 *
 */
public class AStarMazeSolver implements MazeSolver{
	private Maze maze;
	private OpenSet<PositionCost> openSet;
	private ClosedSet<Position> closedSet;
	// This is just a record for storing data about all the nodes, it allows
	// me to mutate the positions and keep track of the best costs without worrying about
	// affecting order of the structure
	private Map<Position, PositionCost> seenPositions;
	private Map<Position, Position> parents;
	private Heuristic heuristic;
	private Heuristic accumulated;

	private void initializeStructures() {
		openSet = new OpenSet<PositionCost>();
		closedSet = new ClosedSet<Position>();
		seenPositions = new HashMap<Position, PositionCost>();
		parents = new HashMap<Position, Position>();

		// The openSet starts with only the starting node with the initial cost set
		Position startPosition = maze.getStartPosition();
		int heuristicCost = heuristic.calculateCost(startPosition, maze.getEndPosition());

		PositionCost start = new PositionCost(startPosition);
		start.setAccumulatedCost(0);
		start.setHeuristicCost(heuristicCost);

		seenPositions.put(startPosition, start);
		openSet.add(start);
	}

	/**
	 *
	 * @param maze
	 * @param heuristic
	 * @return
	 */
	@Override
	public List<Position> solve(Maze maze, Heuristic heuristic) {
		this.maze = maze;
		this.heuristic = heuristic;
		initializeStructures();

		PositionCost current;
		Position endPosition = maze.getEndPosition();
		// Iterate until there are no more maze positions to evaluate
		while (!openSet.isEmpty()) {
			// Take position with lowest cost in unevaluated set
			current = openSet.removeBest();

			// We found the end, we can quit searching now
			if (current.getPosition().equals(endPosition)) {
				return constructPath();
			}

			closedSet.add(current.getPosition());

			evaluateNeighbors(current);
		}

		// Return with nothing because we couldn't find a path
		return new ArrayList<Position>();
	}

	/**
	 *  This is a special method for cases when the the accumulated cost calculation
	 *  should be different from the heuristic calculation. An example would be for Dijkstra's
	 *  which has a null heuristic, or having manhattan distance but wanting a diagonal neighbor
	 *  metric
	 * @param maze
	 * @param heuristic
	 * @param accumulated
	 * @return
	 */
	@Override
	public List<Position> solve(Maze maze, Heuristic heuristic, Heuristic accumulated) {
		this.accumulated = accumulated;

		return solve(maze, heuristic);
	}

	/**
	 * Gets all the walkable neighbors to the current position and decides if it is closer than
	 * what we've seen before or if we've never seen it before. Adds these neighbors to the openset
	 */
	private void evaluateNeighbors(PositionCost current) {
		for (Position neighborPosition: maze.getNeighbors(current.getPosition())) {
			// Don't bother looking at a node that's been evaluated
			if (closedSet.contains(neighborPosition)) {
				continue;
			}

			Position currentPosition = current.getPosition();
			int neighborDistance = calculateNeighborDistance(currentPosition, neighborPosition);
			int potentialAccumulatedCost = current.getAccumulatedCost() + neighborDistance;

			PositionCost neighbor = new PositionCost(neighborPosition);

			// We want to compare the accumulated cost of going to this neighbor vs what we have seen before
			PositionCost priorNeighborInfo = seenPositions.get(neighborPosition);
			int neighborAccumulatedCost = priorNeighborInfo != null ? priorNeighborInfo.getAccumulatedCost() : Integer.MAX_VALUE;

			if (!openSet.contains(neighbor)|| potentialAccumulatedCost < neighborAccumulatedCost) {
				parents.put(neighborPosition, currentPosition);
				neighbor.setAccumulatedCost(potentialAccumulatedCost);
				int cost = heuristic.calculateCost(neighborPosition, currentPosition);
				neighbor.setHeuristicCost(cost);

				if (openSet.contains(neighbor)) {
					openSet.remove(neighbor);
				}
				openSet.add(neighbor);
			}
		}
	}

	/**
	 * Constructs the path through the maze from the end to the start
	 * @return a List of Positions from the start to end point (inclusive)
	 */
	private List<Position> constructPath() {
		// We will traverse backwards so a stack is perfect for pushing quickly to the top
		Stack<Position> reversedPath = new Stack<Position>();

		Position currentPosition = maze.getEndPosition();
		Position startPosition = maze.getStartPosition();

		// Traverse maze until we find the start position
		while (!(currentPosition.equals(startPosition))) {
			reversedPath.push(currentPosition);
			currentPosition = parents.get(currentPosition);
		}

		// The loop termination condition was that the currentNode wasn't the start,
		// therefore the start was never pushed to the stack
		reversedPath.push(currentPosition);

		List<Position> path = new ArrayList<Position>(reversedPath.size());
		// Reverse stack to get path from start -> finish
		while (!reversedPath.empty()) {
			path.add(reversedPath.pop());
		}

		return path;
	}

	private int calculateNeighborDistance(Position current, Position neighbor) {
		return accumulated.calculateCost(current, neighbor);
	}
}
