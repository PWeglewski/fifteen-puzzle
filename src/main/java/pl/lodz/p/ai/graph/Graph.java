package pl.lodz.p.ai.graph;

import pl.lodz.p.ai.puzzle.Direction;
import pl.lodz.p.ai.puzzle.PuzzleState;

import java.util.*;

public class Graph {
    //    static Logger log = Logger.getLogger(Graph.class.getName());
    protected Node root;
    protected int graphDepth = 0;
    protected int nodeCount = 0;
    protected Map<String, String> knownStates = new HashMap<>();

    protected Graph(Node root) {
        this.root = root;
        nodeCount = 1;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static class GraphBuilder {
        Graph graph = null;
        Node root;
        int depth = 1;

        List<Direction> orientation = Arrays.asList(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT);

        public GraphBuilder(PuzzleState rootPuzzleState) {
            this.root = new Node(null, rootPuzzleState, 0);
        }

        public GraphBuilder counterClockwise() {
            orientation = Arrays.asList(Direction.UP, Direction.LEFT, Direction.DOWN, Direction.RIGHT);
            return this;
        }

        public GraphBuilder depth(int depth) {
            this.depth = depth;
            return this;
        }

        public Graph build() {
            graph = new Graph(root);
            createChildrenStackImpl(root);
            return graph;
        }

        private void createChildren(Node node) {
            if (node.getDepth() == depth) {
                return;
            }

            List<Node> children = new ArrayList<>();
            for (Direction direction : orientation) {
                PuzzleState childPuzzleState = node.getPuzzleState().move(direction);
                if (childPuzzleState != null) {
                    node.addChild(new Node(node, childPuzzleState, node.getDepth() + 1));
                    graph.nodeCount++;
                }
            }
            for (Node child : node.getChildren()) {
                createChildren(child);
            }
        }

        private void createChildrenStackImpl(Node root) {
            Deque<Node> stack = new ArrayDeque<>();
            stack.add(root);

            graph.knownStates.put(root.getPuzzleState().toString(), "");

            while (!stack.isEmpty()) {
                Node node = stack.pop();
                if (node.getDepth() == depth) {
                    continue;
                }

                List<Node> children = new ArrayList<>();
                for (Direction direction : orientation) {
                    PuzzleState childPuzzleState = node.getPuzzleState().move(direction);
                    if (childPuzzleState != null && (!graph.knownStates.containsKey(childPuzzleState.toString()))) {
                        node.addChild(new Node(node, childPuzzleState, node.getDepth() + 1));
                        graph.nodeCount++;
                        graph.knownStates.put(childPuzzleState.toString(), "");
                    }
                }
                for (Node child : node.getChildren()) {
                    stack.push(child);
                }
            }
        }
    }

}
