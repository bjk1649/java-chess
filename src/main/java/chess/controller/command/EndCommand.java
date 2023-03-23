package chess.controller.command;

import chess.domain.ChessGame;

public class EndCommand implements Command{

    public EndCommand() {
    }

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.end();
    }
}
