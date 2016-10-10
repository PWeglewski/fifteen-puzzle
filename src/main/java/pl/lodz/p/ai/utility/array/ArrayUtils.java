package pl.lodz.p.ai.utility.array;

import pl.lodz.p.ai.array.Array2D;
import pl.lodz.p.ai.array.Position;
import pl.lodz.p.ai.puzzle.Constants;

import java.util.Arrays;

public class ArrayUtils {
    public static int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

    public static final void swap(int[][] a, int i, int j, int i2, int j2) {
        int t = a[i][j];
        a[i][j] = a[i2][j2];
        a[i2][j2] = t;
    }

    public static Position findZeroPosition(Array2D sourceArray) {
        Position zeroPosition = null;

        int[] flatArray = sourceArray.getSourceArray();
        for (int i = 0; i < flatArray.length; i++) {
            if (flatArray[i] == Constants.EMPTY_FIELD) {
                zeroPosition = determine2DCoordinates(i, sourceArray.getSize());
            }
        }

        return zeroPosition;
    }

    private static Position determine2DCoordinates(int i, int arraySize) {
        return new Position(i % arraySize, i / arraySize);
    }
}
