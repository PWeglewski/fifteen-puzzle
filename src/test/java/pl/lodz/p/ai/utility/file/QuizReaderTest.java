package pl.lodz.p.ai.utility.file;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by piotr on 05.10.16.
 */
public class QuizReaderTest {
    @Test
    public void read() throws Exception {
        //given
        String fileName = "sample.json";

        //when
        int[][] result = QuizReader.read("testSample.json");

        //then
        int[][] expectedArray = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}};
        assertThat(result).isEqualTo(expectedArray);
        assertThat(result.length).isEqualTo(4);
    }
}