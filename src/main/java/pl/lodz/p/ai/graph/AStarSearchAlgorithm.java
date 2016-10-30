package pl.lodz.p.ai.graph;

import pl.lodz.p.ai.heuristic.ManhattanDistance;
import pl.lodz.p.ai.optimisation.PuzzleStateRememberer;
import pl.lodz.p.ai.puzzle.Direction;
import pl.lodz.p.ai.puzzle.PuzzleState;
import pl.lodz.p.ai.utility.puzzle.FifteenPuzzleChecker;

import java.util.*;

/**
 * Created by Piotr on 2016-10-29.
 * <p>
 * source http://heyes-jones.com/astar.php
 * https://en.wikipedia.org/wiki/A*_search_algorithm
 */
public class AStarSearchAlgorithm {
    public static final int G_SCORE = 1;

    List<Direction> orientation = Arrays.asList(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT);
    PuzzleStateRememberer knownStates = new PuzzleStateRememberer();
    ManhattanDistance manhattanDistance = new ManhattanDistance();

    public Node search(PuzzleState initialState) {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        Set<Node> closedList = new HashSet<>();

        Node startNode = new Node(null, initialState, 0, 0);
        openList.add(startNode);
        knownStates.addNewState(startNode.getPuzzleState());

        while (!openList.isEmpty()) {
            Node currentNode = openList.peek();
            if (FifteenPuzzleChecker.isSoultion(currentNode)) {
                return currentNode;
            }

            openList.remove(currentNode);
            closedList.add(currentNode);

            List<Node> children = getChildren(currentNode);
            for (Node child : children) {
                child.setCost(child.getDepth() + manhattanDistance.calculateValueForState(child.getPuzzleState()));
                openList.add(child);
            }
        }

        return null;
    }

    private List<Node> getChildren(Node parent) {
        List<Node> children = new ArrayList<>();

        for (Direction direction : orientation) {
            PuzzleState childPuzzleState = parent.getPuzzleState().move(direction);
            if (childPuzzleState != null && (!knownStates.isStateKnown(childPuzzleState))) {
                children.add(new Node(parent, childPuzzleState, parent.getDepth() + 1));
                knownStates.addNewState(childPuzzleState);
            }
        }
        return children;
    }
}
