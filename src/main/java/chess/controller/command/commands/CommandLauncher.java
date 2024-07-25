package chess.controller.command.commands;

import chess.controller.ChessController;

public interface CommandLauncher {
  void execute(ChessController controller);
  boolean validateInitialCommandType();
  boolean validateStatusCommandType();
}
