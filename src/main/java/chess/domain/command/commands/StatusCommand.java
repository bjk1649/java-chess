package chess.domain.command.commands;

import chess.controller.ChessController;
import chess.domain.command.CommandLauncher;

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
