package pl.lodz.p.ai.utility.array;

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
	
	public static final void swap (int[][] a, int i, int j, int i2, int j2) {
		  int t = a[i][j];
		  a[i][j] = a[i2][j2];
		  a[i2][j2] = t;
		}

}
