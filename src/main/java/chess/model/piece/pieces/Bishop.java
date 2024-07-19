package chess.model.piece.pieces;

import static chess.model.movement.MovementConverter.convertMovement;

import chess.model.movement.Movement;
import chess.model.movement.Path;
import chess.model.piece.Piece;
import chess.model.piece.PieceInfo;
import chess.model.position.Color;
import chess.model.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

  private static final List<Movement> availableMovements = List.of(Movement.UP_LEFT, Movement.UP_RIGHT,
      Movement.DOWN_LEFT, Movement.DOWN_RIGHT);

  public Bishop(final Color color) {
    super(color);
  }

  @Override
  public Path findPath(Position from, Position to) {
    Movement movement = convertMovement(from, to);
    validateMovement(movement, availableMovements);

    return calculatePath(from, to, movement);
  }

  private Path calculatePath(final Position from, final Position to, final Movement movement) {
    Position current = from;
    List<Position> positions = new ArrayList<>();

    while (!current.equals(to)) { // 경로에는 목적지가 포함되지 않음
      current = current.calculateNextPosition(movement);
      positions.add(current);
    }

    return new Path(positions);
  }

  @Override
  public PieceInfo pieceType() {
    return PieceInfo.BISHOP;
  }
}
