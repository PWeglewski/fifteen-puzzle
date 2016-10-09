package pl.lodz.p.ai.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class FifteenPuzzleGraphTest {

	@Test
	public void testFifteenPuzzleGraph() {
		int[][] expectedArray = {{0, 1, 2, 3},
								{4, 5, 6, 7},
								{8, 9, 10, 11},
								{12, 13, 14, 15}};
		
		int[][] expectedArray2 = {{1, 0, 2, 3},
								 {4, 5, 6, 7},
								 {8, 9, 10, 11},
								 {12, 13, 14, 15}};
		
		FifteenPuzzleGraph graph = new FifteenPuzzleGraph("testSample.json");
		graph.createGraph(3);
		
		assertThat(graph.getRoot().getPuzzleState()).isEqualTo(expectedArray);
		
		//knowing in which order children are added
		assertThat(graph.getRoot().getChildren().get(0).getPuzzleState()).isEqualTo(expectedArray2);
		
	}
}
