package pl.lodz.p.ai.search;

import java.util.LinkedList;
import java.util.Queue;

import pl.lodz.p.ai.graph.Graph;
import pl.lodz.p.ai.graph.Node;
import pl.lodz.p.ai.utility.puzzle.FifteenPuzzleChecker;

public class BFSSearch implements ISearch{
	private Graph graph;
	private Node Solution = null;
	
	public BFSSearch(Graph graph)
	{
		this.graph = graph;
	}
	
	public Node SearchSolution()
	{
		Queue<Node> queue = new LinkedList<Node>();
		if(graph.getRoot() == null)
		{
			return null;
		}
		
		queue.add(graph.getRoot());
		
		while(!queue.isEmpty())
		{
			//remove from front
            Node r = queue.remove();
            
    		if(FifteenPuzzleChecker.isSoultion(r))
    		{
    			return r;
    		}

            for(Node n: r.getChildren())
            {
            	queue.add(n);
            }
		}
		return null;
		
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
				if(FifteenPuzzleChecker.isSoultion(a))
				{
					return a;
				}
			}
			for (Node a : node.getChildren())
			{
				return Search(a);
			}
		}
		return null;
	}
}
