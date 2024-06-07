package chess;

import chess.board.Board;
import chess.piece.Piece;
import chess.ui.InputView;
import chess.ui.OutputView;
import chess.position.Position;
import chess.piece.Team;

public class ChessGameManager {
    private static final String START_COMMAND = "start";
    private static final String STATUS_COMMAND = "status";
    private Team turn = Team.WHITE;
    private boolean isGameOver = false;

    public void startNewGame() {
        OutputView.printStartMessage();
        if (InputView.inputCommand().equals(START_COMMAND)) {
            Board board = new Board();
            OutputView.printBoard(board);
            proceedGame(board);
        }
    }

    public void proceedGame(Board board) {
        if (isGameOver) {
            return;
        }
        String command = InputView.inputCommand();
        if (!command.equals(STATUS_COMMAND)) {
            moveProcess(board, command);
            proceedGame(board);
        }
        if (command.equals(STATUS_COMMAND)) {
            OutputView.printEndGameByStatusMessage(board);
        }
    }

    public void moveProcess(Board board, String command) {
        String startPosition = InputView.extractStartPosition(command);
        String targetPosition = InputView.extractTargetPosition(command);

        Position start = searchPosition(startPosition);
        Position target = searchPosition(targetPosition);

        verifyTurn(board.findPiece(start));
        board.verifyPath(start, target);
        if (board.findPiece(target).isKing()) {
            board.movePiece(start, target);
            OutputView.printBoard(board);
            OutputView.printEndGameMessage(turn);
            isGameOver = true;
            return;
        }
        board.movePiece(start, target);
        OutputView.printBoard(board);
        turn = turn.changeTurn();
    }

    public Position searchPosition(String location) {
        int file = location.charAt(0) - 'a' + 1;
        int rank = location.charAt(1) - '0';

        return new Position(file, rank);
    }

    public void verifyTurn(Piece piece) {
        if (!piece.isSameTeam(turn)) {
            throw new IllegalStateException("상대방 기물을 이동시킬 수 없습니다.");
        }
    }
}
