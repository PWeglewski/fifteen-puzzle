package pl.lodz.p.ai.search;

import pl.lodz.p.ai.graph.Graph;
import pl.lodz.p.ai.graph.Node;
import pl.lodz.p.ai.utility.puzzle.FifteenPuzzleChecker;

public class DFSSearch {
	private Graph graph;
	private Node solution = null;
	
	public DFSSearch(Graph graph)
	{
		this.graph = graph;
	}
	
	public Node SearchSolution()
	{
		if(FifteenPuzzleChecker.isSoultion(graph.getRoot()))
		{
			return graph.getRoot();
		}
		return Search(graph.getRoot());
	}
	
	
	private Node Search(Node node)
	{
		for (Node a : node.getChildren())
		{
			if(FifteenPuzzleChecker.isSoultion(a))
			{
				return a;
			}
			else
			{
				solution = Search(a);
				if(solution != null)
				{
					return solution;
				}	
			}			
		}
		return null;
	}
}
