package chess.domain;

import chess.domain.position.Position;

public class ChessGame {

    private final ChessBoard chessBoard;
    private GameState state;

    public ChessGame(ChessBoard chessBoard) {
        this.state = GameState.RUNNING;
        this.chessBoard = chessBoard;
    }

    public void initialize() {
        state = GameState.RUNNING;
    }

    public boolean isNotEnd() {
        return state != GameState.FINISHED;
    }

    public void executeMove(Position source, Position destination) {
        if (state != GameState.RUNNING) {
            throw new IllegalStateException("게임이 실행중이지 않습니다.");
        }
        chessBoard.move(source, destination);
        checkGameNotFinished();
    }

    private void checkGameNotFinished() {
        if (chessBoard.isKingDead()) {
            state = GameState.FINISHED;
        }
    }

    public void end() {
        state = GameState.FINISHED;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
