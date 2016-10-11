package pl.lodz.p.ai;

import pl.lodz.p.ai.array.Array2D;
import pl.lodz.p.ai.graph.Graph;
import pl.lodz.p.ai.graph.Node;
import pl.lodz.p.ai.puzzle.PuzzleState;
import pl.lodz.p.ai.search.BFSSearch;
import pl.lodz.p.ai.utility.file.QuizReader;

/**
 * Hello world!
 */
public class FifteenPuzzleApp {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Array2D array2D = QuizReader.read("easysample.json");

        PuzzleState puzzleStateJanusz = new PuzzleState.PuzzleStateBuilder(array2D).build();

        Graph grafJanusz = new Graph.GraphBuilder(puzzleStateJanusz).depth(13).build();
        
        BFSSearch bfs = new BFSSearch(grafJanusz);
        
        Node node = bfs.SearchSolution();
        
        if(node!= null)
        {
        	System.out.println("EKSTRA");
        	System.out.println(node.getDepth());
        	System.out.println(node.getPuzzleState().getState().toString());
        }

        System.out.println("porobione");
    }
}
