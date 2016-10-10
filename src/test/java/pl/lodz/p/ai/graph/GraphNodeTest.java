package pl.lodz.p.ai.graph;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class GraphNodeTest {

	@Test
	public void test() {
		//dodam jakies lepsze testy, tutaj na razie takie na szybko czy 
		//dziala to co chcialem sprawdzic
//		TODO Node node = new Node(null, QuizReader.read("testSample.json"),0);
		Node node = null;
		node.setParent(node);
		node.addChild(node);
		node.addChild(node);
		
		assertThat(node.getPuzzleState()).isEqualTo(node.getParent().getPuzzleState());
		assertThat(node.getPuzzleState()).isEqualTo(node.getChildren().get(0).getPuzzleState());
	}

}
