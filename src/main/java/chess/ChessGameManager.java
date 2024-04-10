package chess;

import chess.board.Board;
import chess.ui.InputView;
import chess.ui.OutputView;

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
        while (!InputView.inputCommand().equals(END_COMMAND)) {

        }
    }
}
