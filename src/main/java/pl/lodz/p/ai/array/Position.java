package pl.lodz.p.ai.array;

/**
 * Created by piotr on 10.10.16.
 */
public class Position {
    final int x;
    final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Position(x, y);
    }

    public Position translatePosition(int xAxisShift, int yAxisShift) {
        return new Position(getX() + xAxisShift, getY() + yAxisShift);
    }
}
