package pl.lodz.p.ai.model;

import java.util.ArrayList;
import java.util.logging.Logger;

import pl.lodz.p.ai.utility.file.QuizReader;

public class GraphNode {
	static Logger log = Logger.getLogger(GraphNode.class.getName());
	private int[][] puzzleState = null;
	private GraphNode parent = null;
	private int depth = -1;
	//Moge zamienic z listy na tabele jak chcesz, ale nie zawsze bedzie 3, czyli
	//jakas stala wiec dalem liste - no i łatwo mi sie dodaje elementy
	//jak coś wystaw mi ticket w Kayako
	//ja go zanalizuje, odpisze Ci, Ty mi wystawisz wtedy go poprawnie i po trzech
	//dniach naprawie.
	private ArrayList<GraphNode> children = new ArrayList<GraphNode>();
	
	public GraphNode(GraphNode parent, int[][] puzzleState, int depth)
	{
		if(puzzleState== null || puzzleState.length != 4 || puzzleState[0].length != 4)
		{
			log.severe("Wrong puzzle size or null!");
			return;
		}
		this.puzzleState = puzzleState;
		this.depth = depth;
		this.parent = parent;
	}
	
	public void setParent(GraphNode parent)
	{
		if(parent == null)
		{
			log.severe("Trying to set null as a Parent");
			return;
		}
		this.parent = parent;
	}
	
	public GraphNode getParent()
	{
		if(parent == null)
		{
			log.info("This is root or parent is not set");
		}
		return parent;
	}
	
	public void addChild(GraphNode child)
	{
		if(child == null)
		{
			log.severe("Trying to add null as a child");
			return;
		}
		children.add(child);
	}
	
	public ArrayList<GraphNode> getChildren()
	{
		if(children == null)
		{
			log.info("Children are not set");
		}
		return children;
	}
	
	public void setPuzzleState(int[][] puzzleState)
	{
		if(puzzleState.length != 4 || puzzleState[0].length != 4)
		{
			log.severe("Wrong puzzle size!");
			return;
		}
		this.puzzleState = puzzleState;
	}
	
	public int[][] getPuzzleState()
	{
		if(puzzleState == null)
		{
			log.severe("Trying to get puzzle state from node which does not have it");
		}
		return puzzleState;
	}
	
	public int getDepth()
	{
		return depth;
	}
}
