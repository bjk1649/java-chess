package chess.controller.command.commands;

import chess.domain.game.ChessGame;
import chess.service.ChessGameService;
import chess.view.OutputView;
import java.sql.SQLException;

public class ContinueCommand implements Command {

  @Override
  public ChessGame initializeChessGame(final ChessGameService chessGameService)
      throws SQLException {
    ChessGame chessGame = chessGameService.findChessGame();
    if (chessGame == null) {
      OutputView.printNoExistsRunningGameMessage();
    }
    OutputView.printContinueMessage();
    return chessGame;
  }

  @Override
  public void execute(ChessGameService chessGameService) {
    throw new UnsupportedOperationException();
  }
}
