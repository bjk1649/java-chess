package chess.piece;

import static chess.move.Movement.*;
import static chess.move.Movement.LEFT_LEFT_DOWN;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.move.Movement;
import chess.piece.*;
import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PieceTest {
    @DisplayName("새로운 기물을 생성할 수 있다.")
    @Test
    void createNewPiece() {
        Piece newPiece = new Rook(Team.WHITE);

    }

    @DisplayName("빈 칸을 나타내는 Piece인지 확인할 수 있다.")
    @Test
    void isEmptyPiece() {
        Piece empty = new Empty(Team.NONE);
        Piece pawn = new Pawn(Team.WHITE);
        assertTrue(empty.isEmpty());
        assertFalse(pawn.isEmpty());
    }

    @DisplayName("같은 팀 기물이 있는 위치로 이동할 경우 예외처리")
    @Test
    void checkTargetPieceIsSameTeam() {
        Piece selectedPiece = new Rook(Team.WHITE);
        Piece targetPiece = new Knight(Team.WHITE);
        Position start = new Position(1,1);
        Position target = new Position(2,1);
        assertThatThrownBy(() -> selectedPiece.checkTargetPosition(targetPiece, start, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("같은 팀 기물이 있는 위치로 이동 할 수 없습니다.");
    }
}
