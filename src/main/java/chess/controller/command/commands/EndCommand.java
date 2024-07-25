package chess.controller.command.commands;

import chess.controller.ChessController;

public class EndCommand implements CommandLauncher {

  @Override
  public void execute(ChessController controller) {
    // end
  }

  @Override
  public boolean validateInitialCommandType() {
    return true;
  }

  @Override
  public boolean validateStatusCommandType() {
    return false;
  }
}
