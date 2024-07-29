package chess.controller.command.commands;

import chess.domain.game.ChessGame;
import chess.service.ChessGameService;

public class EndCommand implements Command {

  private final ChessGame chessGame;

  public EndCommand(ChessGame chessGame) {
    this.chessGame = chessGame;
  }

  @Override
  public void execute(final ChessGameService chessGameService) {
    chessGame.end();
  }

  @Override
  public ChessGame initializeChessGame(final ChessGameService chessGameService) {
    return null;
  }
}
