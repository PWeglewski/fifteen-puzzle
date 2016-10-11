package pl.lodz.p.ai;

import pl.lodz.p.ai.array.Array2D;
import pl.lodz.p.ai.graph.Graph;
import pl.lodz.p.ai.puzzle.PuzzleState;
import pl.lodz.p.ai.utility.file.QuizReader;

/**
 * Hello world!
 */
public class FifteenPuzzleApp {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Array2D array2D = QuizReader.read("sample.json");

        PuzzleState puzzleStateJanusz = new PuzzleState.PuzzleStateBuilder(array2D).build();

        Graph grafJanusz = new Graph.GraphBuilder(puzzleStateJanusz).depth(25).build();

        System.out.println("porobione");
    }
}
