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
            while (true) {
                if (proceedGame(board).equals(END_COMMAND)) {
                    break;
                }
            }
        }
    }

    public String proceedGame(Board board) {
        String command = InputView.inputCommand();
        if (!command.equals(END_COMMAND)) {
            moveProcess(board, InputView.extractMovePath(command));
            OutputView.printBoard(board);
            proceedGame(board);
        }
        if (command.equals(END_COMMAND)) {
            System.out.println("게임 종료");
        }
        return command;
    }

    public void moveProcess(Board board, List<String> locations) {
        Position start = searchPosition(locations.get(0));
        Position target = searchPosition(locations.get(1));

        verifyTurn(board.findPiece(start).getTeam());
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
