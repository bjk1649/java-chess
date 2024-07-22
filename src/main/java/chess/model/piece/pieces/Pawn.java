package chess.model.piece.pieces;

import chess.model.movement.Movement;
import chess.model.movement.Path;
import chess.model.piece.Piece;
import chess.model.piece.PieceInfo;
import chess.model.position.Color;
import chess.model.position.Position;

import chess.model.ErrorMessage;
import java.util.List;
import java.util.Map;

import static chess.model.movement.Movement.*;
import static chess.model.movement.MovementConverter.convertMovement;

public class Pawn extends Piece {

  private static final Map<Color, Movement> AVAILABLE_MOVEMENTS = Map.of(
      Color.BLACK, DOWN,
      Color.WHITE, UP
  ); // 폰은 앞으로만 움직일 수 있다

  private static final Map<Color, List<Movement>> AVAILABLE_ATTACK_MOVEMENTS = Map.of(
      Color.BLACK, List.of(DOWN_RIGHT, DOWN_LEFT),
      Color.WHITE, List.of(UP_RIGHT, UP_LEFT)
  ); // 적을 공격할 때는 대각선으로 움직일 수 있다

  private static final Map<Color, Integer> INITIAL_POSITION_RANK = Map.of(
      Color.BLACK, 7,
      Color.WHITE, 2
  );

  private static final Map<Color, Integer> INITIAL_RANK_DIFFERENCE = Map.of(
      Color.BLACK, -2,
      Color.WHITE, 2
  ); // 처음에는 두 칸씩 움직인다

  public Pawn(final Color color) {
    super(color);
  }

  @Override
  public Path findPath(final Position from, final Position to, final Map<Position, Piece> board) {
    Movement movement = convertMovement(from, to);
    if (isAttackMovement(movement)) {
      return findPathForAttackMovement(to, movement, board);
    }
    return findPathForEmptyPosition(from, to, movement);
  }

  private boolean isAttackMovement(final Movement movement) {
    return AVAILABLE_ATTACK_MOVEMENTS.get(getColor()).contains(movement);
  }

  private Path findPathForAttackMovement(final Position to, final Movement movement, final Map<Position, Piece> board) {
    validateMovement(movement, AVAILABLE_ATTACK_MOVEMENTS.get(getColor()));
    validateAttackMovement(to, board);
    return new Path(List.of(to));
  }
  private void validateAttackMovement(final Position to, final Map<Position, Piece> board) {
    Piece targetPiece = board.get(to);
    if (targetPiece == null || targetPiece.getColor() == this.getColor()) {
      throw new IllegalArgumentException(ErrorMessage.INVALID_DIRECTION.getMessage());
    }
  }

  private Path findPathForEmptyPosition(final Position from, final Position to, final Movement movement) {
    validateMovement(movement, List.of(AVAILABLE_MOVEMENTS.get(getColor())));

    if (isOneDiff(from, to)) {
      return new Path(List.of(to));
    }

    if (isInInitialPosition(from) && isTwoDiff(from, to)) {
      final Position wayPoint = from.calculateNextPosition(AVAILABLE_MOVEMENTS.get(getColor()));
      return new Path(List.of(wayPoint, to));
    }

    throw new IllegalArgumentException(ErrorMessage.INVALID_DIRECTION.getMessage());
  }

  private boolean isOneDiff(final Position from, final Position to) {
    return Math.abs(to.getRank().getValue() - from.getRank().getValue()) == 1;
  }

  private boolean isTwoDiff(final Position from, final Position to) {
    return to.getRank().getValue() - from.getRank().getValue() == INITIAL_RANK_DIFFERENCE.get(getColor());
  } // 처음에는 두 칸씩 움직인다

  private boolean isInInitialPosition(final Position from) {
    return from.getRank().getValue() == INITIAL_POSITION_RANK.get(getColor());
  }

  @Override
  public PieceInfo pieceType() {
    return PieceInfo.PAWN;
  }
}
