package pl.lodz.p.ai.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import pl.lodz.p.ai.graph.Graph.GraphBuilder;
import pl.lodz.p.ai.puzzle.Direction;
import pl.lodz.p.ai.puzzle.PuzzleState;
import pl.lodz.p.ai.utility.puzzle.FifteenPuzzleChecker;

public class DFSGraphBuilderAndSolver extends Graph {
	
	public DFSGraphBuilderAndSolver(Node root)
	{
		super(root);
		
	}
	
	 public static class GraphBuilderAndSolver {
	        Graph graph = null;
	        Node root;
	        int depth = 1;

	        List<Direction> orientation = Arrays.asList(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT);

	        public GraphBuilderAndSolver(PuzzleState rootPuzzleState) {
	            this.root = new Node(null, rootPuzzleState, 0);
	        }

	        public GraphBuilderAndSolver counterClockwise() {
	            orientation = Arrays.asList(Direction.UP, Direction.LEFT, Direction.DOWN, Direction.RIGHT);
	            return this;
	        }

	        public GraphBuilderAndSolver depth(int depth) {
	            this.depth = depth;
	            return this;
	        }

	        public Node buildAndSolve() {
	            graph = new Graph(root);
	            Node solution = null;
	            solution = createChildrenAndSolveStackImpl(root);
	            return solution;
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

	        private Node createChildrenAndSolveStackImpl(Node root) {
	            Deque<Node> stack = new ArrayDeque<>();
	            stack.add(root);

	            graph.knownStates.put(root.getPuzzleState().toString(), "");

	            while (!stack.isEmpty()) {
	                Node node = stack.pop();
//	                System.out.println("DFS depth:" + node.getDepth());
//	                try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
	                if(FifteenPuzzleChecker.isSoultion(node))
	                {
	                	return node;
	                }	                	
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
	                //Here we push so it is DFS does not work as it wont find optimal solution
	                for (Node child : node.getChildren()) {
	                    stack.push(child);
	                }
	            }
	            return null;
	        }
	    }
}
