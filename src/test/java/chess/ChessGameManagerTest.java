package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.board.Board;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


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

    @DisplayName("검은색 킹이 잡혔을 때 게임이 종료 메세지가 출력되는지 확인한다")
    @Test
    void blackKingDead() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Board board = new Board();
        ChessGameManager manager = new ChessGameManager();

        manager.moveProcess(board, "move e2 e4");
        manager.moveProcess(board, "move f7 f5");
        manager.moveProcess(board, "move d1 h5");
        manager.moveProcess(board, "move e7 e5");
        manager.moveProcess(board, "move h5 e8");

        String output = outContent.toString();
        assertThat(output).contains("WHITE 팀의 승리입니다.");
    }
}
