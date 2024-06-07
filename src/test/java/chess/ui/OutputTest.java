package chess.ui;

import static org.assertj.core.api.Assertions.assertThat;

import chess.ChessGameManager;
import chess.board.Board;
import chess.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class OutputTest {
    @DisplayName("게임이 종료된 턴에 따라 승리 팀을 출력한다.")
    @Test
    void printEndGameMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Team turn = Team.WHITE;

        OutputView.printEndGameMessage(turn);
        String output = outContent.toString();
        assertThat(output).contains("WHITE 팀의 승리입니다.");

        turn = turn.changeTurn();

        OutputView.printEndGameMessage(turn);
        output = outContent.toString();
        assertThat(output).contains("BLACK 팀의 승리입니다.");
    }
}
