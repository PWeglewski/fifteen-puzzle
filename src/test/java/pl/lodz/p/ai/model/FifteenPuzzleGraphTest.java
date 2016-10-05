package pl.lodz.p.ai.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class FifteenPuzzleGraphTest {

	@Test
	public void testFifteenPuzzleGraph() {
		
		FifteenPuzzleGraph graph = new FifteenPuzzleGraph("testSample.json");
		graph.createGraph(3);
		
		assertThat(4).isEqualTo(4);
		
	}

//	@Test
//	public void testGetRoot() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetRoot() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCreateGraph() {
//		fail("Not yet implemented");
//	}

}
