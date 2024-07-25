package chess;

import chess.game.ChessGameManager;

public class Application {
    public static void main(String[] args) {
        ChessGameManager chessGameManager = new ChessGameManager();
        chessGameManager.startNewGame();
    }
}
