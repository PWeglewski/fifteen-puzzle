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
import pl.lodz.p.ai.utility.puzzle.FifteenPuzzleStateGenerator;

/**
 * Hello world!
 */
public class FifteenPuzzleApp {
    public static void main(String[] args) {
	    System.out.println("Hello World!");
	
	    Array2D array2D = QuizReader.read("solution.json");
	
	    PuzzleState puzzleStateJanusz = new PuzzleState.PuzzleStateBuilder(array2D).build();
	
	    PuzzleState puzzleState = FifteenPuzzleStateGenerator.generateRandomState(puzzleStateJanusz, 17);
	    puzzleState.setLastMoveToNull();
	    System.out.println(puzzleState.toString());
        
      Graph grafJanusz = new Graph.GraphBuilder(puzzleState).depth(17).build();
      
      ISearch searchAlg = new DFSSearch(grafJanusz);
      Node node = searchAlg.SearchSolution();
      
      if(node!= null)
      {
      	System.out.println("EKSTRA");
      	System.out.println(node.getDepth());
      	System.out.println(node.getPuzzleState().toString());
      }
      //1:2:3:4:5:6:7:8:13:0:10:11:14:9:15:12:
      
      searchAlg = new BFSSearch(grafJanusz);
      node = searchAlg.SearchSolution();
      
      if(node!= null)
      {
      	System.out.println("EKSTRA2");
      	System.out.println(node.getDepth());
      	System.out.println(node.getPuzzleState().toString());
      }
        
        
        Node dfsSolution = new DFSGraphBuilderAndSolver.GraphBuilderAndSolver(puzzleState).depth(17).buildAndSolve();
        if(dfsSolution != null)
        {
        	System.out.println("EKSTRA3");
        	System.out.println(dfsSolution.getDepth());
        	System.out.println(dfsSolution.getPuzzleState().toString());   	
        }
        
	    System.out.println("UWAGA, BFS");
        Node bfsSolution = new BFSGraphBuilderAndSolver.GraphBuilderAndSolver(puzzleState).depth(17).buildAndSolve();
        if(bfsSolution != null)
        {
        	System.out.println("EKSTRA4");
        	System.out.println(bfsSolution.getDepth());
        	System.out.println(bfsSolution.getPuzzleState().toString());   	
        }
        
        System.out.println("porobione");
        
        //BFS zoptymalizowany - jest podczas budowy szuka od razu - lepiej sie hcyba nie
        //da, zawsze znajdzie najszybsze rozwiazanie
        //Te osobne DFS i BFS co dzialaja na grafie - tez znajda najszybsze - go graf robiony 
        //jest ptymalnie z niepowtarzajacymi sie stanami level po levelu - ale nieoptymalne jest to
        //ze tworzy sie graf caly najpierw
        
        //DFS zoptymalizowany - slaby bo moze nam zaglebic rozwiazanie glebiej i go nie znajdziemy
        //mozna go ogarnac usuwajac sprawdzenia czy juz stan wystapil - ale bedziemy
        //w stanie robic duze mniejsze grafy
    }
}
