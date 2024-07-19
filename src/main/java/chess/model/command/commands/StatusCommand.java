package chess.model.command.commands;

import chess.controller.ChessController;
import chess.model.command.CommandLauncher;

public class StatusCommand implements CommandLauncher {
  @Override
  public void execute(ChessController controller) {
    controller.calculateAndPrintCurrentTurnScore();
  }

  @Override
  public boolean validateInitialCommandType() {
    return false;
  }

  @Override
  public boolean validateStatusCommandType() {
    return true;
  }
}
