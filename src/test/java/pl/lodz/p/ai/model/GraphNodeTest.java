package pl.lodz.p.ai.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import pl.lodz.p.ai.utility.file.QuizReader;

public class GraphNodeTest {

	@Test
	public void test() {
		//dodam jakies lepsze testy, tutaj na razie takie na szybko czy 
		//dziala to co chcialem sprawdzic
		GraphNode node = new GraphNode(QuizReader.read("testSample.json"));
		node.setParent(node);
		node.addChild(node);
		node.addChild(node);
		
		assertThat(node.getPuzzleState()).isEqualTo(node.getParent().getPuzzleState());
		assertThat(node.getPuzzleState()).isEqualTo(node.getChildren().get(0).getPuzzleState());
	}

}
