package chess.domain.piece.pieces;

import static chess.domain.movement.MovementConverter.convertMovement;

import chess.domain.movement.Movement;
import chess.domain.movement.Path;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceInfo;
import chess.domain.position.Color;
import chess.domain.position.Position;
import chess.domain.ErrorMessage;
import java.util.List;
import java.util.Map;

public class King extends Piece {

  private static final List<Movement> availableMovements = List.of(
      Movement.UP, Movement.DOWN, Movement.LEFT, Movement.RIGHT,
      Movement.UP_LEFT, Movement.UP_RIGHT, Movement.DOWN_LEFT, Movement.DOWN_RIGHT);

  public King(final Color color) {
    super(color);
  }

  @Override
  public Path findPath(Position from, Position to, final Map<Position, Piece> board) {
    Movement movement = convertMovement(from, to);
    validateMovement(movement, availableMovements);

    validateAvailableDestination(from, to, movement);

    return new Path(List.of(to));
  }

  @Override
  public PieceInfo pieceType() {
    return PieceInfo.KING;
  }

  private void validateAvailableDestination(final Position from, final Position to, final Movement movement) {
    if (!from.calculateNextPosition(movement).equals(to)) { // 한 칸 씩만 움직이기 때문에 목적지만 판단
      throw new IllegalStateException(ErrorMessage.INVALID_DIRECTION.getMessage());
    }
  }
}
