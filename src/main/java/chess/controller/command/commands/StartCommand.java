package chess.controller.command.commands;

import chess.domain.game.ChessGame;
import chess.service.ChessGameService;
import chess.view.OutputView;

public class StartCommand implements Command {

  private final ChessGame chessGame;

  public StartCommand(ChessGame chessGame) {
    this.chessGame = chessGame;
  }

  @Override
  public void execute(final ChessGameService chessGameService) {
    chessGame.start();
    OutputView.printBoard(chessGame.getBoard().getMap());
  }

  @Override
  public ChessGame initializeChessGame(final ChessGameService chessGameService) {
    throw new UnsupportedOperationException();
  }
}
