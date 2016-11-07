package pl.lodz.p.ai.graph;

import pl.lodz.p.ai.heuristic.ManhattanDistance;
import pl.lodz.p.ai.optimisation.ClosedList;
import pl.lodz.p.ai.optimisation.OpenList;
import pl.lodz.p.ai.optimisation.PuzzleStateRememberer;
import pl.lodz.p.ai.puzzle.Direction;
import pl.lodz.p.ai.puzzle.PuzzleState;
import pl.lodz.p.ai.utility.puzzle.FifteenPuzzleChecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Piotr on 2016-10-29.
 * <p>
 * source http://heyes-jones.com/astar.php
 * https://en.wikipedia.org/wiki/A*_search_algorithm
 * https://www.youtube.com/watch?v=b19e0_NlD-U
 */
public class AStarSearchAlgorithm {
    public static final int G_SCORE = 1;

    List<Direction> orientation = Arrays.asList(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT);
    PuzzleStateRememberer knownStates = new PuzzleStateRememberer();
    ManhattanDistance manhattanDistance = new ManhattanDistance();

    public Node search(PuzzleState initialState) {
        // Open list is a priority queue, because it does not need to be sorted
        OpenList openList = new OpenList();
        ClosedList closedList = new ClosedList();

        Node startNode = new Node(null, initialState, 0, 0);
        openList.add(startNode);
        knownStates.addNewState(startNode.getPuzzleState());

        while (!openList.isEmpty()) {
            // Retrieves and removes the node with the best heuristic value from open list
            Node currentNode = openList.poll();

            // Add to the closed list
            closedList.add(currentNode);

            // Check if the current state is solution
            if (FifteenPuzzleChecker.isSoultion(currentNode)) {
                return currentNode;
            }

            // Get all possible children of the current state
            List<Node> children = getChildren(currentNode);
            for (Node child : children) {
                // Assign heuristic value to a child
                child.setCost(child.getDepth() + manhattanDistance.calculateValueForState(child.getPuzzleState()));

                // If child's state is already on open list
                if (openList.contains(child)) {
                    // if the child was reached by a shorter path
                    if (child.getCost() < openList.getNodeCost(child)) {
                        // then give the state on open shorter path
                        openList.replaceNode(child);
                    } else {
                        continue;
                    }
                }

                // If child's state is already on closed list
                if (closedList.contains(child)) {
                    // if the child was reached by a shorter path
                    if (child.getCost() < closedList.getNodeCost(child)) {
                        // then remove the state from closed list
                        closedList.remove(child);
                        // and add new child to open list
                        openList.add(child);
                    } else {
                        continue;
                    }
                }

                openList.add(child);
            }
        }

        return null;
    }

    private List<Node> getChildren(Node parent) {
        List<Node> children = new ArrayList<>();

        for (Direction direction : orientation) {
            PuzzleState childPuzzleState = parent.getPuzzleState().move(direction);
//            if (childPuzzleState != null && (!knownStates.isStateKnown(childPuzzleState))) {
            if (childPuzzleState != null) {
                children.add(new Node(parent, childPuzzleState, parent.getDepth() + 1));
//                knownStates.addNewState(childPuzzleState);
            }
        }
        return children;
    }
}
