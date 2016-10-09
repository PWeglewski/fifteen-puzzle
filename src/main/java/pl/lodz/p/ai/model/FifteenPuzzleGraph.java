package pl.lodz.p.ai.model;

import java.util.logging.Logger;

import pl.lodz.p.ai.utility.file.QuizReader;
import pl.lodz.p.ai.utility.puzzle.FifteenPuzzleChecker;

public class FifteenPuzzleGraph {
	static Logger log = Logger.getLogger(FifteenPuzzleGraph.class.getName());
	private GraphNode root = null;
	private int graphDepth = 0;
	private int nodeCount = 1;
	
	public FifteenPuzzleGraph(String rootPuzzleStatePath)
	{
		root = new GraphNode(null, QuizReader.read(rootPuzzleStatePath),0);
	}
	
	public GraphNode getRoot()
	{
		return root;
	}
	
	public void setRoot(GraphNode root)
	{
		if(root == null)
		{
			log.info("Setting root value to null");			
		}
		this.root = root;
	}
	
	public void createGraph(int graphDepth)
	{
		this.graphDepth = graphDepth;
		this.nodeCount = 1;
		addNodesToGraph(root);
	}
	
	private void addNodesToGraph(GraphNode node)
	{
		if(node.getDepth() == graphDepth)
		{
			return;
		}
		//TODO jesli mozna przesunac, metodke dodac co sprawdza, utilowa
		//i metodke co zamienia, w sumie to chyba ta sama
		if(FifteenPuzzleChecker.createNextState(node.getPuzzleState(),0) != null)
		{
			nodeCount++;
			node.addChild(new GraphNode(node, FifteenPuzzleChecker.createNextState(node.getPuzzleState(),0),node.getDepth() + 1));
		}
		if(FifteenPuzzleChecker.createNextState(node.getPuzzleState(),1) != null)
		{
			nodeCount++;
			node.addChild(new GraphNode(node, FifteenPuzzleChecker.createNextState(node.getPuzzleState(),1),node.getDepth() + 1));
		}
		if(FifteenPuzzleChecker.createNextState(node.getPuzzleState(),2) != null)
		{
			nodeCount++;
			node.addChild(new GraphNode(node, FifteenPuzzleChecker.createNextState(node.getPuzzleState(),2),node.getDepth() + 1));
		}
		if(FifteenPuzzleChecker.createNextState(node.getPuzzleState(),3) != null)
		{
			nodeCount++;
			node.addChild(new GraphNode(node, FifteenPuzzleChecker.createNextState(node.getPuzzleState(),3),node.getDepth() + 1));
		}
		//log.info("count" + nodeCount);
				
		for(GraphNode child : node.getChildren())
		{
			this.addNodesToGraph(child);
		}		
	}
}
