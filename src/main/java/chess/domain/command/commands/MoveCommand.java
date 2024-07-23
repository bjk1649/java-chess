package chess.domain.command.commands;

import chess.controller.ChessController;
import chess.domain.command.CommandLauncher;
import chess.domain.position.Position;

public class MoveCommand implements CommandLauncher {

  private final Position source;
  private final Position target;

  public MoveCommand(Position source, Position target) {
    this.source = source;
    this.target = target;
  }

  @Override
  public void execute(ChessController controller) {
    controller.movePiece(source, target);
  }

  @Override
  public boolean validateInitialCommandType() {
    return false;
  }

  @Override
  public boolean validateStatusCommandType() {
    return false;
  }
}
