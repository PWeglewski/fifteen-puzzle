package pl.lodz.p.ai.heuristic;

import org.junit.Test;
import pl.lodz.p.ai.array.Array2D;
import pl.lodz.p.ai.puzzle.Direction;
import pl.lodz.p.ai.puzzle.PuzzleState;
import pl.lodz.p.ai.utility.array.ArrayUtils;
import pl.lodz.p.ai.utility.file.QuizReader;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Piotr on 2016-10-29.
 */
public class ManhattanDistanceTest {
    @Test
    public void heuristicShouldBeZeroForSolutionState() {
        Array2D solutionArray = QuizReader.getSolutionArray();
        PuzzleState solution = new PuzzleState(solutionArray, ArrayUtils.findZeroPosition(solutionArray), Direction.NONE);
        ManhattanDistance manhattanDistance = new ManhattanDistance();

        int actualValue = manhattanDistance.calculateValueForState(solution);

        int expectedValue = 0;

        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    public void heuristicShouldBeCalculatedForGivenState() {
        int[][] stateArray = {
                {0, 2, 3, 13},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {4, 14, 15, 1}
        };

        Array2D array2D = new Array2D(stateArray);
        PuzzleState state = new PuzzleState(array2D, ArrayUtils.findZeroPosition(array2D), Direction.NONE);
        ManhattanDistance manhattanDistance = new ManhattanDistance();

        int actualValue = manhattanDistance.calculateValueForState(state);

        int expectedValue = 18;

        assertThat(actualValue).isEqualTo(expectedValue);
    }
}