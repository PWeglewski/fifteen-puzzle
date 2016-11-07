package pl.lodz.p.ai.puzzle;

import pl.lodz.p.ai.array.Array2D;
import pl.lodz.p.ai.array.Position;
import pl.lodz.p.ai.utility.array.ArrayUtils;

/**
 * Created by piotr on 10.10.16.
 */
public class PuzzleState {
    final Array2D state;

    final Position zeroPosition;

    Direction lastMove;

    String toStringCachedValue = null;

    public PuzzleState(Array2D state, Position zeroPosition, Direction lastMove) {
        this.state = state;
        this.zeroPosition = zeroPosition;
        this.lastMove = lastMove;
    }

    public Array2D getState() {
        return state;
    }

    public Position getZeroPosition() {
        return zeroPosition;
    }

    public void setLastMoveToNull() {
        lastMove = null;
    }

    public PuzzleState move(Direction direction) {
        if (checkLastMove(lastMove, direction)) return null;
        PuzzleState newPuzzleState = null;
        Array2D newState = getState().clone();
        Position newZeroPosition = null;
        switch (direction) {
            case UP:
                newZeroPosition = zeroPosition.translatePosition(0, -1);
                break;
            case DOWN:
                newZeroPosition = zeroPosition.translatePosition(0, 1);
                break;
            case LEFT:
                newZeroPosition = zeroPosition.translatePosition(-1, 0);
                break;
            case RIGHT:
                newZeroPosition = zeroPosition.translatePosition(1, 0);
                break;
        }
        if (!newState.swap(zeroPosition, newZeroPosition)) {
            return null;
        }
        newPuzzleState = new PuzzleStateBuilder(newState)
                .withZeroAt(newZeroPosition)
                .withLastMove(direction)
                .build();

        return newPuzzleState;
    }

    private boolean checkLastMove(Direction lastMove, Direction currentMove) {
        if (lastMove == Direction.DOWN && currentMove == Direction.UP) return true;
        else if (lastMove == Direction.UP && currentMove == Direction.DOWN) return true;
        else if (lastMove == Direction.LEFT && currentMove == Direction.RIGHT) return true;
        else if (lastMove == Direction.RIGHT && currentMove == Direction.LEFT) return true;
        else return false;
    }

    @Override
    public String toString() {
        if (toStringCachedValue == null) {
            StringBuilder stringBuilder = new StringBuilder();
            int[] stateArray = state.getSourceArray();
            for (int i = 0; i < stateArray.length; i++) {
                stringBuilder.append(stateArray[i])
                        .append(":");
            }
            toStringCachedValue = stringBuilder.toString();
        }
        return toStringCachedValue;
    }

    public String displayAsArray() {
        StringBuilder stringBuilder = new StringBuilder();
        int[] stateArray = state.getSourceArray();
        for (int i = 0; i < stateArray.length; i++) {
            stringBuilder.append(stateArray[i])
                    .append(" ");
            if ((i + 1) % 4 == 0) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }

    public static class PuzzleStateBuilder {
        Array2D sourceArray;
        Position zeroPosition;
        Direction lastMove = Direction.NONE;

        public PuzzleStateBuilder(Array2D sourceArray) {
            this.sourceArray = sourceArray;
        }

        public PuzzleStateBuilder withZeroAt(Position zeroPosition) {
            this.zeroPosition = zeroPosition;
            return this;
        }

        public PuzzleStateBuilder withLastMove(Direction lastMove) {
            this.lastMove = lastMove;
            return this;
        }

        public PuzzleState build() {
            if (this.zeroPosition == null) {
                this.zeroPosition = ArrayUtils.findZeroPosition(sourceArray);
            }
            return new PuzzleState(sourceArray, zeroPosition, lastMove);
        }
    }
}
