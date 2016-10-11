package pl.lodz.p.ai.utility.array;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ArrayUtilsTest {

	@Test
	public void testDeepCopy() {
		int[][] solution ={{1, 2, 3, 4},
				  		  {5, 6, 7, 8},
				  		  {9, 10, 11, 12},
				  		  {13, 14, 15, 0}};
		
		int[][] solution2 = solution;
		int[][] solution3 = ArrayUtils.deepCopy(solution);
		
		//to juz elegancko sprawdza wartosciami a nie referencjami!
		assertThat(solution).isEqualTo(solution2);
		assertThat(solution).isEqualTo(solution3);
		
		solution[0][0] = 20;
		
		assertThat(solution).isEqualTo(solution2);
		assertThat(solution).isNotEqualTo(solution3);
		
	}

	@Test
	public void testSwap() {
		int[][] state1 ={{1, 2, 3, 4},
		  		  		  {5, 6, 7, 8},
		  		  		  {9, 10, 11, 12},
		  		  		  {13, 14, 15, 0}};
		
		int[][] state2 ={{10, 2, 3, 4},
		  		  		{5, 6, 7, 8},
		  		  		{9, 1, 11, 12},
		  		  		{13, 14, 15, 0}};
		
		ArrayUtils.swap(state1, 0, 0, 2, 1);
		
		assertThat(state1).isEqualTo(state2);
	}

}
