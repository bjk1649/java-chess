package chess.controller.command.commands;

import chess.controller.ChessController;

public class StartCommand implements CommandLauncher {

  @Override
  public void execute(ChessController controller) {
    // start
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
