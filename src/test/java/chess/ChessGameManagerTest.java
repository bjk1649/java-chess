package chess;

import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessGameManagerTest {
    @DisplayName("현재 턴이 아닌 팀의 기물을 움직이려고 하면 예외처리")
    @Test
    void verifyTurn() {
        ChessGameManager manager = new ChessGameManager();
        Piece selectedPiece = new Pawn(Team.BLACK);

        assertThatThrownBy(() -> manager.verifyTurn(selectedPiece))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("상대방 기물을 이동시킬 수 없습니다.");
    }
}
