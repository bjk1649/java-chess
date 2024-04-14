package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    void initializeBoard() {
        Board testBoard = new Board();
        testBoard.initializeBoard();
        assertThat(testBoard).isNotNull();
    }
}
