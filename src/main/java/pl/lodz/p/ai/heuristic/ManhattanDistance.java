package pl.lodz.p.ai.heuristic;

import pl.lodz.p.ai.array.Array2D;
import pl.lodz.p.ai.array.Position;
import pl.lodz.p.ai.puzzle.PuzzleState;
import pl.lodz.p.ai.utility.file.QuizReader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Piotr on 2016-10-29.
 * <p>
 * source https://heuristicswiki.wikispaces.com/Manhattan+Distance
 */
public class ManhattanDistance {
    Array2D solution;
    Map<Integer, Position> solutionTilesPositions = new HashMap<>();

    public ManhattanDistance() {
        solution = QuizReader.getSolutionArray();
        buildSolutionsTilesPositionsMap();
    }

    private void buildSolutionsTilesPositionsMap() {
        for (int x = 0; x < solution.getSize(); x++) {
            for (int y = 0; y < solution.getSize(); y++) {
                int currentValue = solution.getValue(x, y);
                solutionTilesPositions.put(currentValue, solution.findValue(currentValue));
            }
        }
    }

    public int calculateValueForState(PuzzleState state) {
        int result = 0;

        Array2D array = state.getState();

        for (int x = 0; x < solution.getSize(); x++) {
            for (int y = 0; y < solution.getSize(); y++) {
                int value = array.getValue(x, y);
                Position solution = solutionTilesPositions.get(value);
                result += Math.abs(x - solution.getX()) + Math.abs(y - solution.getY());
            }
        }

        return result;
    }
}
