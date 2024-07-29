package chess.controller.command.commands;

import chess.domain.game.ChessGame;
import chess.domain.game.State;
import chess.domain.position.Color;
import chess.domain.position.Position;
import chess.service.ChessGameService;
import chess.view.OutputView;
import java.util.List;

public class MoveCommand implements Command {

  private static final int SOURCE_POSITION_INDEX = 1;
  private static final int TARGET_POSITION_INDEX = 2;

  private final ChessGame chessGame;
  private final Position source;
  private final Position target;

  public MoveCommand(ChessGame chessGame, List<String> commandParts) {
    this.chessGame = chessGame;
    this.source = parsePosition(commandParts.get(SOURCE_POSITION_INDEX));
    this.target = parsePosition(commandParts.get(TARGET_POSITION_INDEX));
  }

  @Override
  public void execute(final ChessGameService chessGameService) {
    chessGame.movePiece(source, target);
    chessGameService.updatePiece(chessGame, source, target);
    OutputView.printBoard(chessGame.getBoard().getMap());
    processIfKingCaptured(chessGame);
  }

  private static Position parsePosition(String position) {
    int file = position.charAt(0) - 'a' + 1;
    int rank = position.charAt(1) - '0';
    return new Position(file, rank);
  }

  private void processIfKingCaptured(final ChessGame chessGame) {
    if (chessGame.getState().equals(State.KING_IS_CAPTURED)) {
      final Color winner = chessGame.getTurn();
      OutputView.printWinningColor(winner);
    }
  }

  @Override
  public ChessGame initializeChessGame(final ChessGameService chessGameService) {
    throw new UnsupportedOperationException();
  }
}
