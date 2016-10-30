package pl.lodz.p.ai;

import pl.lodz.p.ai.array.Array2D;
import pl.lodz.p.ai.graph.AStarSearchAlgorithm;
import pl.lodz.p.ai.graph.BFSGraphBuilderAndSolver;
import pl.lodz.p.ai.graph.BestFirstSearchAlgorithm;
import pl.lodz.p.ai.graph.Node;
import pl.lodz.p.ai.puzzle.PuzzleState;
import pl.lodz.p.ai.utility.file.QuizReader;
import pl.lodz.p.ai.utility.puzzle.FifteenPuzzleStateGenerator;

/**
 * BFS:
 * 1. Create graph first(stack,iterative), then search - creating graph of a given depth
 * using BFS and then search using BFS(stack, iterative)
 * 2. During creation of a graph we search for the solution - BFS both cases (stack,iterative)
 * DFS:
 * 1. Create graph first(stack, iterative), then search - creating graph of a given depth
 * using BFS and then search using DFS - recursive
 * 2. During creation of a graph we search for the solution - DFS both cases - can look
 * for worse solution as we are using optimisation - iterative
 * Best-first strategy:
 * A* strategy:
 * SMA*:
 */
public class FifteenPuzzleApp {
    public static void displayPath(Node node) {
        System.out.println("Solution path");
        Node nod = node;
        System.out.println(nod.getPuzzleState().displayAsArray());
        while (nod.getParent() != null) {
            nod = nod.getParent();
            System.out.println(nod.getPuzzleState().displayAsArray());
        }
    }

    public static void main(String[] args) {
        //#######################################################
        //##############    A* Test    ##########################
        //#######################################################
        Array2D array2DAStar = QuizReader.read("sample48steps.json");
        //create puzzle state - with solution
        PuzzleState puzzleStateAStar = new PuzzleState.PuzzleStateBuilder(array2DAStar).build();
//        PuzzleState puzzleStateShuffled = FifteenPuzzleStateGenerator.generateRandomState(puzzleStateAStar, 10);
//        System.out.println("Random state to be solved: " + puzzleStateShuffled.toString());

        AStarSearchAlgorithm aStarSearchAlgorithm = new AStarSearchAlgorithm();

        long startTime = System.currentTimeMillis();
        Node solutionAStar = aStarSearchAlgorithm.search(puzzleStateAStar);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        if (solutionAStar != null) {
            System.out.println("Solution found");
            System.out.println("Elapsed time: " + elapsedTime + "ms");
            System.out.println("Steps: " + solutionAStar.getDepth());
        } else {
            System.out.println("Solution not found!");
        }
        //#######################################################

        if (true) return;

        //read json file - which is solution
        Array2D array2D = QuizReader.read("solution.json");
        //create puzzle state - with solution
        PuzzleState puzzleStateJanusz = new PuzzleState.PuzzleStateBuilder(array2D).build();
        //generate random unsolved(probably) state
        PuzzleState puzzleState = FifteenPuzzleStateGenerator.generateRandomState(puzzleStateJanusz, 17);
        puzzleState.setLastMoveToNull();
        System.out.println("Random state:");
        System.out.println(puzzleState.toString());
   /*   
      //create graph(using BFS strategy) used by DFS and BFS alghorithms
      Graph grafJanusz = new Graph.GraphBuilder(puzzleState).depth(17).build();
      
      ISearch searchAlg = new DFSSearch(grafJanusz);
      Node node = searchAlg.SearchSolution();
      
      if(node!= null)
      {
      	System.out.println("DFSSearch solution found:");
      	System.out.println(node.getDepth());
      	System.out.println(node.getPuzzleState().toString());
      }
      
      searchAlg = new BFSSearch(grafJanusz);
      node = searchAlg.SearchSolution();
      
      if(node!= null)
      {
      	System.out.println("BFSSearch solution found:");
      	System.out.println(node.getDepth());
      	System.out.println(node.getPuzzleState().toString());
      }
        
        
        Node dfsSolution = new DFSGraphBuilderAndSolver.GraphBuilderAndSolver(puzzleState).depth(17).buildAndSolve();
        if(dfsSolution != null)
        {
        	System.out.println("DFS iterative:");
        	System.out.println(dfsSolution.getDepth());
        	System.out.println(dfsSolution.getPuzzleState().toString());   	
        }
        
        */
        Node bfsSolution = new BFSGraphBuilderAndSolver.GraphBuilderAndSolver(puzzleState).depth(17).buildAndSolve();
        if (bfsSolution != null) {
            System.out.println("BFS optimized:");
            System.out.println(bfsSolution.getDepth());
            System.out.println(bfsSolution.getPuzzleState().toString());
        }

        System.out.println("done");


        Node bestSolution = new BestFirstSearchAlgorithm.GraphBuilderAndSolver(puzzleState).depth(17).buildAndSolve(1);
        if (bestSolution != null) {
            System.out.println("Best First Search:");
            System.out.println(bestSolution.getDepth());
            System.out.println(bestSolution.getPuzzleState().toString());
        }
        displayPath(bestSolution);

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
