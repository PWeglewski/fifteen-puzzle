package pl.lodz.p.ai.utility.puzzle;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;
import org.junit.Test;

import pl.lodz.p.ai.graph.Node;

public class FifteenPuzzleCheckerTest {

//	@Test
//	public void testIsSoultion() {
//		int[][] solution ={{1, 2, 3, 4},
//						  {5, 6, 7, 8},
//						  {9, 10, 11, 12},
//						  {13, 14, 15, 0}};
//
//		int[][] notSolution ={{1, 2, 3, 4},
//							  {5, 6, 7, 8},
//							  {9, 10, 11, 12},
//							  {13, 14, 15, 16}};
//
//		Node finalNode = new Node(null, solution,0);
//		Node notFinalNode = new Node(null, notSolution,0);
//
//		Assert.assertFalse(FifteenPuzzleChecker.isSoultion(notFinalNode));
//		Assert.assertTrue(FifteenPuzzleChecker.isSoultion(finalNode));
//
//	}
//
//	@Test
//	public void testCreateNextState() {
//		int[][] currentState ={{1, 2, 3, 4},
//							  {5, 6, 7, 8},
//							  {9, 10, 11, 0},
//							  {13, 14, 15, 12}};
//		Node notFinalNode = new Node(null, currentState,0);
//		Node finalNode = new Node(notFinalNode, FifteenPuzzleChecker.createNextState(currentState, 2),1);
//
//		//To check if i copied values not reference and it is still not solution
//		Assert.assertFalse(FifteenPuzzleChecker.isSoultion(notFinalNode));
//		//to check if swap works correctly
//		Assert.assertTrue(FifteenPuzzleChecker.isSoultion(finalNode));
//
//		int[][] error = FifteenPuzzleChecker.createNextState(currentState, 1);
//
//		assertThat(error).isEqualTo(null);
//	}

}
