package pl.lodz.p.ai.search;

import pl.lodz.p.ai.graph.Graph;
import pl.lodz.p.ai.graph.Node;
import pl.lodz.p.ai.utility.puzzle.FifteenPuzzleChecker;

public class BFSSearch {
	private Graph graph;
	private Node Solution = null;
	
	public BFSSearch(Graph graph)
	{
		this.graph = graph;
	}
	
	public Node SearchSolution()
	{
		return Search(graph.getRoot());
	}
	
	
	private Node Search(Node node)
	{
		if(FifteenPuzzleChecker.isSoultion(node))
		{
			return node;
		}
		else
		{
			for (Node a : node.getChildren())
			{
				return Search(a);
			}
		}
		return null;
	}
}
