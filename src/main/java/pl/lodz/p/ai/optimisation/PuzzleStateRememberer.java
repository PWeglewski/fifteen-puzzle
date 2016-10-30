package pl.lodz.p.ai.optimisation;

import pl.lodz.p.ai.puzzle.PuzzleState;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by piotr on 26.10.16.
 */
public class PuzzleStateRememberer {
    private Map<String, String> knownStates = new HashMap<>();

    public void addNewState(PuzzleState puzzleState) {
        knownStates.put(puzzleState.toString(), "");
    }

    public boolean isStateKnown(PuzzleState state) {
        return knownStates.containsKey(state.toString());
    }
}