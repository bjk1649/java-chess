package chess.model.command.commands;

import chess.controller.ChessController;
import chess.model.command.CommandLauncher;

public class EndCommand implements CommandLauncher {
  @Override
  public void execute(ChessController controller) {
    controller.printScoreAndWinningColor();
    controller.endGame();
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
