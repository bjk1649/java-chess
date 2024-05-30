package chess;

import chess.board.Board;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Team;
import chess.position.Position;
import chess.position.StartPiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static chess.position.StartPiecePosition.BLACK_KING;
import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName("킹이 죽은 경우 게임을 종료하고 승리팀을 출력합니다")
    @Test
    void kingDead() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ChessGameManager manager = new ChessGameManager();

        Team turn = Team.WHITE;
        manager.endGame(turn);
        String output = outContent.toString();
        assertThat(output).contains("WHITE 팀의 승리입니다.");

        turn = Team.BLACK;
        manager.endGame(turn);
        output = outContent.toString();
        assertThat(output).contains("BLACK 팀의 승리입니다.");
    }
}
