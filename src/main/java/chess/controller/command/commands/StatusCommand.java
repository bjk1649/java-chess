package chess.controller.command.commands;

import chess.controller.ChessController;

public class StatusCommand implements CommandLauncher {

  @Override
  public void execute(ChessController controller) {
    // calculate
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
