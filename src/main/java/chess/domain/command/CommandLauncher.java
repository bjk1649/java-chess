package chess.domain.command;

import chess.controller.ChessController;

public interface CommandLauncher {
  void execute(ChessController controller);
  boolean validateInitialCommandType();
  boolean validateStatusCommandType();
}
