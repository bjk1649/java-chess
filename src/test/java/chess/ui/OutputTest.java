package chess.ui;

import static org.assertj.core.api.Assertions.assertThat;

import chess.ChessGameManager;
import chess.board.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class OutputTest {
    @DisplayName("킹이 죽은 경우 게임을 종료하고 승리팀을 출력합니다")
    @Test
    void kingDead() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ChessGameManager manager = new ChessGameManager();
        Board board = new Board();

        manager.endGame();
        String output = outContent.toString();
        assertThat(output).contains("WHITE 팀의 승리입니다.");

        manager.moveProcess(board, "move d2 d4");

        manager.endGame();
        output = outContent.toString();
        assertThat(output).contains("BLACK 팀의 승리입니다.");
    }
}
