package chess.domain.command.commands;

import chess.controller.ChessController;
import chess.domain.command.CommandLauncher;

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
