package chess.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.board.Board;
import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EmptyTest {
    @DisplayName("빈 칸에서 이동을 수행할 시 예외처리")
    @Test
    void moveEmptyPiece() {
        Board testBoard = new Board();
        Position start = new Position(3,3);
        Position target = new Position(4,4);
        Piece selectedPiece = testBoard.findPiece(start);

        assertThatThrownBy(() -> selectedPiece.findPath(start, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("경로를 찾을 기물이 존재하지 않습니다.");
    }
}
