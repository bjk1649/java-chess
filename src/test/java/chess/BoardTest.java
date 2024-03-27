package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    void createNewBoardTest() {
        Board testBoard = new Board();
        assertThat(testBoard).isNotNull();;
    }
}
