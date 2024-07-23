package chess.domain.piece.pieces;

import static chess.domain.movement.MovementConverter.convertMovement;

import chess.domain.movement.Movement;
import chess.domain.movement.Path;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceInfo;
import chess.domain.position.Color;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Queen extends Piece {

  private static final List<Movement> availableMovements = Arrays.asList(
      Movement.UP, Movement.DOWN, Movement.LEFT, Movement.RIGHT,
      Movement.UP_LEFT, Movement.UP_RIGHT, Movement.DOWN_LEFT, Movement.DOWN_RIGHT
  );

  public Queen(final Color color) {
    super(color);
  }

  @Override
  public Path findPath(final Position from, final Position to, final Map<Position, Piece> board) {
    Movement movement = convertMovement(from, to);
    validateMovement(movement, availableMovements);

    return calculatePath(from, to, movement);
  }

  private Path calculatePath(final Position from, final Position to, final Movement movement) {
    List<Position> positions = new ArrayList<>();

    Position current = from;
    positions.add(current);

    while (!current.equals(to)) {
      current = current.calculateNextPosition(movement);
      positions.add(current);
    }

    return new Path(positions);
  }

  @Override
  public PieceInfo pieceType() {
    return PieceInfo.QUEEN;
  }
}
