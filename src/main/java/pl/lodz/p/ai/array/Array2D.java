package pl.lodz.p.ai.array;

import java.util.Arrays;

/**
 * Created by piotr on 09.10.16.
 */
public class Array2D {
    private int[] sourceArray;

    private int size;

    public Array2D(int size) {
        initialize(size);
    }

    public Array2D(int[][] twoDimArray) {
        initialize(twoDimArray.length);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                setValue(x, y, twoDimArray[y][x]);
            }
        }
    }

    private Array2D(int[] sourceArray, int size) {
        this.sourceArray = sourceArray;
        this.size = size;
    }

    public int[] getSourceArray() {
        return sourceArray;
    }

    public int getSize() {
        return size;
    }

    public int getValue(int x, int y) {
        return sourceArray[y * size + x];
    }

    public void setValue(int x, int y, int value) {
        sourceArray[y * size + x] = value;
    }

    public int getValue(Position p) {
        return getValue(p.getX(), p.getY());
    }

    public void setValue(Position p, int value) {
        setValue(p.getX(), p.getY(), value);
    }

    private void initialize(int size) {
        this.size = size;
        sourceArray = new int[size * size];
    }

    public int[][] getTwoDimArray() {
        int[][] resultArray = new int[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                resultArray[y][x] = getValue(x, y);
            }
        }
        return resultArray;
    }

    public Array2D clone() {
        int[] target = new int[sourceArray.length];
        System.arraycopy(sourceArray, 0, target, 0, sourceArray.length);
        return new Array2D(target, size);
    }

    @Override
    public boolean equals(Object obj) {
        return Arrays.equals(((Array2D) obj).getSourceArray(), sourceArray);
    }

    public void swap(Position p1, Position p2) throws InvalidPositionException {
        if (!(isPositionValid(p1) && isPositionValid(p2))) {
            throw new InvalidPositionException();
        }
        int temp = getValue(p1);
        setValue(p1, getValue(p2));
        setValue(p2, temp);
    }

    private boolean isIndexValid(int index) {
        return (index >= 0 && index < getSize());
    }

    private boolean isPositionValid(Position position) {
        return (isIndexValid(position.getX()) && isIndexValid(position.getY()));
    }
}
