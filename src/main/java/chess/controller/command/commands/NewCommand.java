package chess.controller.command.commands;

import chess.domain.game.ChessGame;
import chess.service.ChessGameService;
import chess.view.OutputView;

public class NewCommand implements Command {

  public NewCommand() {
  }

  @Override
  public ChessGame initializeChessGame(final ChessGameService chessGameService) {
    OutputView.printNewGameMessage();
    chessGameService.deleteChessGame();
    return chessGameService.initializeChessGame();
  }

  @Override
  public void execute(ChessGameService chessGameService) {}
}
