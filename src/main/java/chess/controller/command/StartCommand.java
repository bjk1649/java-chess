package chess.controller.command;

import chess.domain.ChessGame;

public class StartCommand implements Command{

    public StartCommand() {
    }

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.initialize();
    }
}
