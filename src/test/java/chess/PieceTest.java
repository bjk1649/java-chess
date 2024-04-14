package chess;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {
    @Test
    void createNewPiece() {
        Piece testPiece = new Piece(Piece.Type.BISHOP, Piece.Team.WHITE);
        assertThat(testPiece.getType()).isEqualTo(Piece.Type.BISHOP);
        assertThat(testPiece.getTeam()).isEqualTo(Piece.Team.WHITE);
    }
}
