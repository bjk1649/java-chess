package chess;

import chess.ui.InputView;
import chess.ui.OutputView;

public class ChessGameManager {
    public void startNewGame() {
        OutputView.printStartMessage();
        if (InputView.inputCommand().equals("start")) {
            Board board = new Board();
            board.initializeBoard();
            OutputView.printBoard(board);
            proceedGame(board);
        }
    }


    public void proceedGame(Board board) {
        while (!InputView.inputCommand().equals("end")) {

        }
    }
}
