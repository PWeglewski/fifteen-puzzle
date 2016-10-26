package pl.lodz.p.ai.puzzle;

import pl.lodz.p.ai.array.Array2D;
import pl.lodz.p.ai.utility.array.ArrayUtils;
import pl.lodz.p.ai.utility.file.QuizReader;

/**
 * Created by Piotr on 2016-10-26.
 */
public class RandomPuzzleStateGenerator {

    public static final String SOLUTION_JSON = "solution.json";

    public static PuzzleState generateRandomQuiz(int movesToSolve) {
        PuzzleState state = getSolution();

        return state;
    }

    private static PuzzleState getSolution() {
        Array2D solutionArray = QuizReader.read(SOLUTION_JSON);
        return wrapArrayToPuzzleState(solutionArray);
    }

    private static PuzzleState wrapArrayToPuzzleState(Array2D stateArray) {
        return new PuzzleState(stateArray, ArrayUtils.findZeroPosition(stateArray), Direction.NONE);
    }
}
