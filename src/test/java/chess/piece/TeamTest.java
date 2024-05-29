package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TeamTest {
    @DisplayName("각기 다른 기물이 같은 팀인지 확인할 수 있다.")
    @Test
    void checkSameTeam() {
        Piece piece1 = new Pawn(Team.WHITE);
        Piece piece2 = new Knight(Team.WHITE);
        Piece piece3 = new Pawn(Team.BLACK);

        assertTrue(piece1.isSameTeam(piece2));
        assertFalse(piece1.isSameTeam(piece3));
    }

    @DisplayName("현재 턴을 변경할 수 있다.")
    @Test
    void changeTurn() {
        Team turn = Team.WHITE;
        assertThat(turn.changeTurn()).isEqualTo(Team.BLACK);
    }
}
