package pl.lodz.p.ai.graph;

import pl.lodz.p.ai.puzzle.Direction;
import pl.lodz.p.ai.puzzle.PuzzleState;

import java.util.*;
import java.util.logging.Logger;

public class Graph {
//    static Logger log = Logger.getLogger(Graph.class.getName());
    private Node root;
    private int graphDepth = 0;
    private int nodeCount = 0;

    private Graph(Node root) {
        this.root = root;
        nodeCount = 1;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

//    public void createGraph(int graphDepth) {
//        this.graphDepth = graphDepth;
//        addNodesToGraph(root);
//    }

//    private void addNodesToGraph(Node node) {
//        if (node.getDepth() == graphDepth) {
//            return;
//        }
//        //TODO jesli mozna przesunac, metodke dodac co sprawdza, utilowa
//        //i metodke co zamienia, w sumie to chyba ta sama
//        if (FifteenPuzzleChecker.createNextState(node.getPuzzleState(), 0) != null) {
//            nodeCount++;
//            node.addChild(new Node(node, FifteenPuzzleChecker.createNextState(node.getPuzzleState(), 0), node.getDepth() + 1));
//        }
//        if (FifteenPuzzleChecker.createNextState(node.getPuzzleState(), 1) != null) {
//            nodeCount++;
//            node.addChild(new Node(node, FifteenPuzzleChecker.createNextState(node.getPuzzleState(), 1), node.getDepth() + 1));
//        }
//        if (FifteenPuzzleChecker.createNextState(node.getPuzzleState(), 2) != null) {
//            nodeCount++;
//            node.addChild(new Node(node, FifteenPuzzleChecker.createNextState(node.getPuzzleState(), 2), node.getDepth() + 1));
//        }
//        if (FifteenPuzzleChecker.createNextState(node.getPuzzleState(), 3) != null) {
//            nodeCount++;
//            node.addChild(new Node(node, FifteenPuzzleChecker.createNextState(node.getPuzzleState(), 3), node.getDepth() + 1));
//        }
//        //log.info("count" + nodeCount);
//
//        for (Node child : node.getChildren()) {
//            this.addNodesToGraph(child);
//        }
//    }

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

        private void createChildrenStackImpl(Node root){
            Deque<Node> stack = new ArrayDeque<>();
            stack.add(root);

            while(!stack.isEmpty()){
                Node node = stack.pop();
                if (node.getDepth() == depth) {
                    continue;
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
                    stack.push(child);
                }
            }
        }
    }

}
