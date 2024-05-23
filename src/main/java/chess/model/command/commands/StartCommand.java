package chess.model.command.commands;

import chess.controller.ChessController;
import chess.model.command.CommandLauncher;

public class StartCommand implements CommandLauncher {
  @Override
  public void execute(ChessController controller) {
    controller.startGame();
  }
}
