package chess.controller.command.commands;

import chess.domain.game.ChessGame;
import chess.domain.position.Color;
import chess.service.ChessGameService;
import chess.view.OutputView;

public class StatusCommand implements Command {

  private final ChessGame chessGame;

  public StatusCommand(ChessGame chessGame) {
    this.chessGame = chessGame;
  }

  @Override
  public void execute(final ChessGameService chessGameService) {
    final Double whiteScore = chessGame.calculateScore(Color.WHITE);
    final Double blackScore = chessGame.calculateScore(Color.BLACK);
    printScoreAndWinningColor(whiteScore, blackScore);
  }

  private void printScoreAndWinningColor(Double whiteScore, Double blackScore) {
    OutputView.printScore(whiteScore, blackScore);
    if (whiteScore > blackScore) {
      OutputView.printWinningColor(Color.WHITE);
    }
    if (whiteScore < blackScore) {
      OutputView.printWinningColor(Color.BLACK);
    }
    if (whiteScore.equals(blackScore)) {
      OutputView.printDraw();
    }
  }

  @Override
  public ChessGame initializeChessGame(final ChessGameService chessGameService) {
    throw new UnsupportedOperationException();
  }
}
