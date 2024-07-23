package chess.domain.board;

import chess.domain.movement.Path;
import chess.domain.piece.Piece;
import chess.domain.position.Color;
import chess.domain.position.Position;
import chess.domain.ErrorMessage;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {

  private final Map<Position, Piece> board;

  public Board(final Map<Position, Piece> board) {
    this.board = board;
  }

  public Piece moveAndReturnPiece(final Position from, final Position to, final Color turn) {
    validateMove(from, to, turn);
    Piece piece = board.remove(from);
    return board.put(to, piece);
  }

  private void validateMove(final Position from, final Position to, final Color turn) {
    validateNotEmpty(from);
    validateTurn(from, turn);
    validatePath(from, to);
  }

  private void validateNotEmpty(final Position from) {
    if (!board.containsKey(from)) {
      throw new IllegalArgumentException(ErrorMessage.NO_PIECE_AT_SOURCE.getMessage());
    }
  }

  private void validateTurn(final Position from, final Color turn) {
    board.get(from).validateTurn(turn);
  }

  private void validatePath(Position from, Position to) {
    Piece source = board.get(from);
    Piece destination = board.getOrDefault(to, null);
    source.validateSameColor(destination);

    Set<Position> positionsWithOutTarget = new HashSet<>(board.keySet());
    positionsWithOutTarget.remove(to);

    Path path = source.findPath(from, to, board);
    path.validateObstacle(positionsWithOutTarget);
  }

  public Map<Position, Piece> getMap() {
    return Map.copyOf(board);
  }
}
