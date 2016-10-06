package pl.lodz.p.ai.model;

import java.util.logging.Logger;

import pl.lodz.p.ai.utility.file.QuizReader;

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
		node.addChild(new GraphNode(node, createNewState(node.getPuzzleState(),0),node.getDepth() + 1));
		node.addChild(new GraphNode(node, createNewState(node.getPuzzleState(),1),node.getDepth() + 1));
		node.addChild(new GraphNode(node, createNewState(node.getPuzzleState(),2),node.getDepth() + 1));
		node.addChild(new GraphNode(node, createNewState(node.getPuzzleState(),3),node.getDepth() + 1));
		nodeCount += 4;
		//log.info("count" + nodeCount);
				
		for(GraphNode child : node.getChildren())
		{
			this.addNodesToGraph(child);
		}		
	}
	//to nie bedzie uzywane, jest etoda w utilach do tego, podmienie potem
	private int[][] createNewState(int[][] currentState, int flag)
	{
		return root.getPuzzleState();
	}
}
