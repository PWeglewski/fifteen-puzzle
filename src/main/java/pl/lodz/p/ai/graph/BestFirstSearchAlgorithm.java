package pl.lodz.p.ai.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import pl.lodz.p.ai.array.Position;
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
	        	int diff = 0;
	        	for(int i = 0; i < 4; i++)
	        	{
	        		for(int j=0; j<4;j++)
	        		{
	        			if(twoDimSolution[i][j] != node.getPuzzleState().getState().getValue(new Position(j, i)))
	        			{
	        				diff++;
	        			}
	        		}
	        		
	        	}
	        	
	        	node.setValue(diff);
	        }
	        
	        private void calculateHeuristicsManhattanDistance(Node node)
	        {
	        	int diff = 0;
	        	int val;
	        	int properR;
	        	int properC;
	        	for(int i = 0; i < 4; i++)
	        	{
	        		for(int j = 0; j < 4; j++)
	        		{
	        			if(twoDimSolution[i][j] != node.getPuzzleState().getState().getValue(new Position(j, i)))
	        			{
	        				val = node.getPuzzleState().getState().getValue(new Position(j, i));
	        				if(val!= 0)
	        				{
		        				properR = (val-1) / 4;
		        				properC = (val-1) % 4;	        					
	        				}
	        				else
	        				{
	        					properR = 3;
	        					properC = 3;
	        				}
	        				diff += Math.abs(i-properR) + Math.abs(j-properC);
	        			}
	        		}
	        	}
	        	node.setValue(diff);
	        }
	        
	        
	        private void calculateHeuristicsEuclideanDistance(Node node)
	        {
	           	int diff = 0;
	        	int val;
	        	int properR;
	        	int properC;
	        	for(int i = 0; i < 4; i++)
	        	{
	        		for(int j = 0; j < 4; j++)
	        		{
	        			if(twoDimSolution[i][j] != node.getPuzzleState().getState().getValue(new Position(j, i)))
	        			{
	        				val = node.getPuzzleState().getState().getValue(new Position(j, i));
	        				if(val!= 0)
	        				{
		        				properR = (val-1) / 4;
		        				properC = (val-1) % 4;	        					
	        				}
	        				else
	        				{
	        					properR = 3;
	        					properC = 3;
	        				}
	        				diff += Math.sqrt((i-properR)*(i-properR) + (j-properC)*(j-properC));
	        			}
	        		}
	        	}
	        	node.setValue(diff);	
	        }

	        public GraphBuilderAndSolver counterClockwise() {
	            orientation = Arrays.asList(Direction.UP, Direction.LEFT, Direction.DOWN, Direction.RIGHT);
	            return this;
	        }

	        public GraphBuilderAndSolver depth(int depth) {
	            this.depth = depth;
	            return this;
	        }

	        public Node buildAndSolve(int heuristic) {
	            graph = new Graph(root);
	            Node solution = null;
	            solution = createChildrenAndSolveStackImpl(root, heuristic);
	            return solution;
	        }

	        private Node createChildrenAndSolveStackImpl(Node root, int heur) {
	            Deque<Node> stack = new ArrayDeque<>();
	            int heuristic = heur;
	            this.calculateHeuristicsForNode(root, heuristic);
	            stack.add(root);

	            graph.knownStates.put(root.getPuzzleState().toString(), "");
	            
	            int min = 0;
	            Node bestNode = null;

	            while (!stack.isEmpty()) {
	            	//inaczej wyciągać, po H
	            	min = 0;
	            	for(Node nod: stack)
	            	{
	            		if(min == 0 || nod.getValue() < min)
	            		{
	            			min = nod.getValue();
	            			bestNode = nod;
	            		}
	            	}
	            	

        			stack.remove(bestNode);
	                Node node = bestNode;
	                
	                //to mozna sprawdzać po h, jak zero to rozwiazanie
	                if(node.getValue() == 0)
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
	                        this.calculateHeuristicsForNode(childNode, heuristic);
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
