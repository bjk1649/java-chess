package chess.model.piece.pieces;

import static chess.model.movement.MovementConverter.convertMovement;

import chess.model.movement.Movement;
import chess.model.movement.Path;
import chess.model.piece.Piece;
import chess.model.piece.PieceInfo;
import chess.model.position.Color;
import chess.model.position.Position;
import chess.model.ErrorMessage;

import java.util.List;

public class Knight extends Piece {

  private static final List<Movement> availableMovements = List.of(
      Movement.UP_UP_LEFT, Movement.UP_UP_RIGHT,
      Movement.DOWN_DOWN_LEFT, Movement.DOWN_DOWN_RIGHT,
      Movement.LEFT_LEFT_UP, Movement.LEFT_LEFT_DOWN,
      Movement.RIGHT_RIGHT_UP, Movement.RIGHT_RIGHT_DOWN);

  public Knight(final Color color) {
    super(color);
  }

  @Override
  public Path findPath(Position from, Position to) {
    Movement movement = convertMovement(from, to);
    validateMovement(movement, availableMovements);

    validateAvailableDestination(from, to, movement);

    return new Path(List.of(to));
  }

  private void validateAvailableDestination(final Position from, final Position to, final Movement movement) {
    if (!from.calculateNextPosition(movement).equals(to)) { // 뛰어 넘을 수 있어 목적지만 판단
      throw new IllegalStateException(ErrorMessage.INVALID_DIRECTION.getMessage());
    }
  }

  @Override
  public PieceInfo pieceType() {
    return PieceInfo.KNIGHT;
  }
}
