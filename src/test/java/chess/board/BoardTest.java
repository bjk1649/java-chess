package chess.board;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @DisplayName("기물을 이동시킬 수 있다.")
    @Test
    void movePiece() {
        Board testBoard = new Board();
        Position start = new Position(1, 2);
        Position target = new Position(1, 3);

        assertFalse(testBoard.findPiece(start).isEmpty());
        assertTrue(testBoard.findPiece(target).isEmpty());
        testBoard.movePiece(start, target);
        assertTrue(testBoard.findPiece(start).isEmpty());
        assertFalse(testBoard.findPiece(target).isEmpty());
    }

    @DisplayName("경로가 다른 기물로 막혀있지 않은 지 확인한다.")
    @Test
    void searchPathIsEmpty() {
        Board testBoard = new Board();
        Position start = new Position(1, 1);
        Position target = new Position(1,5);
        assertThatThrownBy(() -> testBoard.verifyPath(start, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("다른 기물이 존재해서 지나갈 수 없습니다.");
    }
}
