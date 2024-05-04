package chess;

import chess.board.Board;
import chess.ui.InputView;
import chess.ui.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ChessGameManager {
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";

    public void startNewGame() {
        OutputView.printStartMessage();
        if (InputView.inputCommand().equals(START_COMMAND)) {
            Board board = new Board();
            OutputView.printBoard(board);
            proceedGame(board);
        }
    }

    public void proceedGame(Board board) {
        String command = InputView.inputCommand();
        if (!command.equals(END_COMMAND)) {
            InputView.extractMovePath(command);
            OutputView.printBoard(board);
            proceedGame(board);
        }
        if (command.equals(END_COMMAND)) {
            System.out.println("게임 종료");
        }
    }
}
