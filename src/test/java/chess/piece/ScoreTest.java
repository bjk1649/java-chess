package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.board.Board;
import chess.position.Position;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {
    @DisplayName("각 기물이 점수를 가지고 있는지 확인한다.")
    @Test
    void pieceScore() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(Score.findScoreByPiece(new Pawn(Team.WHITE))).isEqualTo(Score.PAWN_SCORE);
        softly.assertThat(Score.findScoreByPiece(new Rook(Team.BLACK))).isEqualTo(Score.ROOK_SCORE);

        softly.assertAll();
    }

    @DisplayName("초기화된 체스판의 점수를 계산한다.")
    @Test
    void calculateStatusScore() {
        final double correctScore = 38.0;
        Board board = new Board();
        assertThat(Score.calculateScore(board, Team.WHITE)).isEqualTo(correctScore);
        assertThat(Score.calculateScore(board, Team.BLACK)).isEqualTo(correctScore);
    }

    @DisplayName("Pawn이 세로 줄에 두개 있을 때 점수 계산에 반영되는지 확인한다")
    @Test
    void calculateScoreMultiplePawnOnOneFIle() {
        final double correctScore = 37.0;
        Board board = new Board();
        board.movePiece(new Position(2,2), new Position(3,3));
        assertThat(Score.calculateScore(board, Team.WHITE)).isEqualTo(correctScore);
    }

    @DisplayName("검은색 룩(5점)이 잡히면 점수 계산에 반영되는지 확인한다.")
    @Test
    void calculateScoreAfterAttackPiece() {
        final double correctScore = 33.0;
        Board board = new Board();
        Position whiteRookPosition = new Position(1,1);
        Position blackRookPosition = new Position(1,8);
        board.movePiece(whiteRookPosition, blackRookPosition);
        assertThat(Score.calculateScore(board, Team.BLACK)).isEqualTo(correctScore);
    }
}
