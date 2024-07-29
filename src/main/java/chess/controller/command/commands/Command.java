package chess.controller.command.commands;

import chess.domain.game.ChessGame;
import chess.service.ChessGameService;
import java.sql.SQLException;

public interface Command {
  void execute(final ChessGameService chessGameService);
  ChessGame initializeChessGame(final ChessGameService chessGameService) throws SQLException;
}
