package pl.lodz.p.ai.utility.puzzle;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import pl.lodz.p.ai.model.GraphNode;

public class FifteenPuzzleCheckerTest {

	@Test
	public void testIsSoultion() {
		int[][] solution ={{1, 2, 3, 4},
						  {5, 6, 7, 8},
						  {9, 10, 11, 12},
						  {13, 14, 15, 0}};
		
		int[][] notSolution ={{1, 2, 3, 4},
							  {5, 6, 7, 8},
							  {9, 10, 11, 12},
							  {13, 14, 15, 16}};
		
		GraphNode finalNode = new GraphNode(null, solution,0);
		GraphNode notFinalNode = new GraphNode(null, notSolution,0);
		
		Assert.assertFalse(FifteenPuzzleChecker.isSoultion(notFinalNode));
		Assert.assertTrue(FifteenPuzzleChecker.isSoultion(finalNode));
		
	}

//	@Test
//	public void testCreateNextState() {
//		fail("Not yet implemented");
//	}

}
