package pl.lodz.p.ai.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import pl.lodz.p.ai.graph.BFSGraphBuilderAndSolver.GraphBuilderAndSolver;
import pl.lodz.p.ai.puzzle.Direction;
import pl.lodz.p.ai.puzzle.PuzzleState;
import pl.lodz.p.ai.utility.puzzle.FifteenPuzzleChecker;

public class BestFirstSearchAlgorithm extends Graph {

	public BestFirstSearchAlgorithm(Node root) {
		super(root);
	}
	
	 public static class GraphBuilderAndSolver {
			private static int[][] twoDimSolution ={{1, 2, 3, 4},
					  {5, 6, 7, 8},
					  {9, 10, 11, 12},
					  {13, 14, 15, 0}};

	        Graph graph = null;
	        Node root;
	        int depth = 1;

	        List<Direction> orientation = Arrays.asList(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT);

	        public GraphBuilderAndSolver(PuzzleState rootPuzzleState) {
	            this.root = new Node(null, rootPuzzleState, 0);
	        }
	        
	        //three types of heurustics, 1,2,3
	        public void calculateHeuristicsForNode(Node node, int type)
	        {
	        	switch(type)
	        	{
	        		case 0:
	        			this.calculateHeuristicsMisplacedTiles(node);
	        			break;
	        		case 1:
	        			this.calculateHeuristicsManhattanDistance(node);
	        			break;
	        		case 2:
	        			this.calculateHeuristicsEuclideanDistance(node);
	        			break;
	        	}
	        }
	        
	        private void calculateHeuristicsMisplacedTiles(Node node)
	        {
	        	
	        }
	        
	        private void calculateHeuristicsManhattanDistance(Node node)
	        {
	        	
	        }
	        
	        
	        private void calculateHeuristicsEuclideanDistance(Node node)
	        {
	        	
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
	            this.calculateHeuristicsForNode(root, 0);
	            stack.add(root);

	            graph.knownStates.put(root.getPuzzleState().toString(), "");

	            while (!stack.isEmpty()) {
	            	//inaczej wyciągać, po H
	                Node node = stack.pop();
	                
	                //to mozna sprawdzać po h, jak zero to rozwiazanie
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
	                    //na razie hardodowane, mozna zmienic
	                    if (childPuzzleState != null && (!graph.knownStates.containsKey(childPuzzleState.toString()))) {
	                        Node childNode = new Node(node, childPuzzleState, node.getDepth() + 1);
	                        this.calculateHeuristicsForNode(childNode, 0);
	                    	node.addChild(childNode);
	                        graph.nodeCount++;
	                        graph.knownStates.put(childPuzzleState.toString(), "");
	                    }
	                }
	                //here we have to add at the end so it is BFS not DFS
	                for (Node child : node.getChildren()) {
	                    stack.add(child);
	                }
	            }
	            return null;
	        }
	    }
}
