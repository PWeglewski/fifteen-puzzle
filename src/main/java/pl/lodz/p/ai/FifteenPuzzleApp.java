package pl.lodz.p.ai;

import pl.lodz.p.ai.array.Array2D;
import pl.lodz.p.ai.graph.BFSGraphBuilderAndSolver;
import pl.lodz.p.ai.graph.DFSGraphBuilderAndSolver;
import pl.lodz.p.ai.graph.Graph;
import pl.lodz.p.ai.graph.Node;
import pl.lodz.p.ai.puzzle.PuzzleState;
import pl.lodz.p.ai.search.BFSSearch;
import pl.lodz.p.ai.search.DFSSearch;
import pl.lodz.p.ai.search.ISearch;
import pl.lodz.p.ai.utility.file.QuizReader;

/**
 * Hello world!
 */
public class FifteenPuzzleApp {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Array2D array2D = QuizReader.read("easysample.json");

        PuzzleState puzzleStateJanusz = new PuzzleState.PuzzleStateBuilder(array2D).build();

//        Graph grafJanusz = new Graph.GraphBuilder(puzzleStateJanusz).depth(13).build();
//        
//        ISearch searchAlg = new DFSSearch(grafJanusz);
//        Node node = searchAlg.SearchSolution();
//        
//        if(node!= null)
//        {
//        	System.out.println("EKSTRA");
//        	System.out.println(node.getDepth());
//        	System.out.println(node.getPuzzleState().toString());
//        }
//        
//        searchAlg = new BFSSearch(grafJanusz);
//        node = searchAlg.SearchSolution();
//        
//        if(node!= null)
//        {
//        	System.out.println("EKSTRA2");
//        	System.out.println(node.getDepth());
//        	System.out.println(node.getPuzzleState().toString());
//        }

        
        Node dfsSolution = new DFSGraphBuilderAndSolver.GraphBuilderAndSolver(puzzleStateJanusz).depth(13).buildAndSolve();
        if(dfsSolution != null)
        {
        	System.out.println("EKSTRA3");
        	System.out.println(dfsSolution.getDepth());
        	System.out.println(dfsSolution.getPuzzleState().toString());   	
        }
        
        Node bfsSolution = new BFSGraphBuilderAndSolver.GraphBuilderAndSolver(puzzleStateJanusz).depth(13).buildAndSolve();
        if(bfsSolution != null)
        {
        	System.out.println("EKSTRA4");
        	System.out.println(bfsSolution.getDepth());
        	System.out.println(bfsSolution.getPuzzleState().toString());   	
        }
        
        System.out.println("porobione");
    }
}
