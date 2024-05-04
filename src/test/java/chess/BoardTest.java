package chess;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import chess.board.Board;
import chess.position.Position;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    void movePiece() {
        Board testBoard = new Board();

        Position start = new Position(1, 2);
        Position target = new Position(1, 3);

        assertNotNull(testBoard.findPiece(start));
        assertNull(testBoard.findPiece(target));

        testBoard.movePiece(start, target);

        assertNull(testBoard.findPiece(start));
        assertNotNull(testBoard.findPiece(target));
    }
}
