package pl.lodz.p.ai.puzzle;

import java.util.Random;

/**
 * Created by piotr on 10.10.16.
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT, NONE;

    public static Direction getRandomDirection(){
        Random random = new Random();
        switch (random.nextInt(4)){
            case 0: return UP;
            case 1: return DOWN;
            case 2: return LEFT;
            case 3: return RIGHT;
            default: return NONE; // Should not happen :)
        }
    }
}
