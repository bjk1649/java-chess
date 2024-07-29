package chess.controller;

import chess.controller.command.CommandFactory;
import chess.controller.command.commands.Command;
import chess.domain.game.ChessGame;
import chess.domain.game.State;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;
import java.sql.SQLException;

public class ChessController {

  private final ChessGameService chessGameService;

  public ChessController(ChessGameService chessGameService) {
    this.chessGameService = chessGameService;
  }

  public void run() throws SQLException {
    ChessGame chessGame = executeInitialCommandAndFetchChessGame();

    while (isRunnable(chessGame)) {
      executeCommand(chessGame);
    }
  }

  private void executeCommand(ChessGame chessGame) {
    try {
      Command command = CommandFactory.createCommand(chessGame, InputView.receiveCommand());
      command.execute(chessGameService);
    } catch (IllegalArgumentException e) {
      OutputView.printErrorMessage(e);
      executeCommand(chessGame);
    }
  }

  private ChessGame executeInitialCommandAndFetchChessGame() throws SQLException {
    try {
      Command command = CommandFactory.createInitialCommand(InputView.receiveInitialCommand());
      return command.initializeChessGame(chessGameService);
    } catch (IllegalArgumentException e) {
      OutputView.printErrorMessage(e);
      return executeInitialCommandAndFetchChessGame();
    }
  }

  private boolean isRunnable(ChessGame chessGame) {
    return chessGame.getState().equals(State.RUNNING)
        || chessGame.getState().equals(State.WAITING);
  }
}
