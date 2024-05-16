package chess;

import chess.board.Board;
import chess.ui.InputView;
import chess.ui.OutputView;
import chess.position.Position;
import chess.piece.Team;
import java.util.List;

public class ChessGameManager {
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private Team turn = Team.WHITE;

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
            moveProcess(board, command);
            OutputView.printBoard(board);
            proceedGame(board);
        }
        if (command.equals(END_COMMAND)) {
            System.out.println("게임 종료");
        }
    }

    public void moveProcess(Board board, String command) {
        String startPosition = InputView.extractStartPosition(command);
        String targetPosition = InputView.extractTargetPosition(command);

        Position start = searchPosition(startPosition);
        Position target = searchPosition(targetPosition);

        verifyTurn(board.findPiece(start).getTeam());
        board.verifyPath(start, target);
        board.movePiece(start, target);
        turn = turn.changeTurn();
    }

    public Position searchPosition(String location) {
        int file = location.charAt(0) - 'a' + 1;
        int rank = location.charAt(1) - '0';

        return new Position(file, rank);
    }

    public void verifyTurn(Team team) {
        if (!team.isSameTeam(turn)) {
            throw new IllegalArgumentException("상대방 기물을 이동시킬 수 없습니다.");
        }
    }
}
