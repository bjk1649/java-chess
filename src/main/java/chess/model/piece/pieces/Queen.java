package chess.model.piece.pieces;

import static chess.model.movement.MovementConverter.convertMovement;

import chess.model.movement.Movement;
import chess.model.movement.Path;
import chess.model.piece.Piece;
import chess.model.piece.PieceInfo;
import chess.model.position.Color;
import chess.model.position.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen extends Piece {

  private static final List<Movement> availableMovements = Arrays.asList(
      Movement.UP, Movement.DOWN, Movement.LEFT, Movement.RIGHT,
      Movement.UP_LEFT, Movement.UP_RIGHT, Movement.DOWN_LEFT, Movement.DOWN_RIGHT
  );

  public Queen(final Color color) {
    super(color);
  }

  @Override
  public Path findPath(final Position from, final Position to) {
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
