package chess;

import chess.board.Board;
import chess.ui.InputView;
import chess.ui.OutputView;
import chess.position.Position;
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
            moveprocess(board, InputView.extractMovePath(command));
            OutputView.printBoard(board);
            proceedGame(board);
        }
        if (command.equals(END_COMMAND)) {
            System.out.println("게임 종료");
        }
    }

    public void moveprocess(Board board, List<String> locationList) {
        Position start = searchPosition(locationList.get(0));
        Position target = searchPosition(locationList.get(1));

        board.movePiece(start, target);
    }

    public Position searchPosition(String location) {
        int file = location.charAt(0) - 'a' + 1;
        int rank = location.charAt(1) - '0';

        return new Position(file, rank);
    }
}
