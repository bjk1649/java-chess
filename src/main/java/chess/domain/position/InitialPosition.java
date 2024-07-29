package chess.domain.position;

public enum InitialPosition {
  WHITE_ROOK_LEFT(1, 1),
  WHITE_KNIGHT_LEFT(2, 1),
  WHITE_BISHOP_LEFT(3, 1),
  WHITE_QUEEN(4, 1),
  WHITE_KING(5, 1),
  WHITE_BISHOP_RIGHT(6, 1),
  WHITE_KNIGHT_RIGHT(7, 1),
  WHITE_ROOK_RIGHT(8, 1),
  WHITE_PAWN_A(1, 2),
  WHITE_PAWN_B(2, 2),
  WHITE_PAWN_C(3, 2),
  WHITE_PAWN_D(4, 2),
  WHITE_PAWN_E(5, 2),
  WHITE_PAWN_F(6, 2),
  WHITE_PAWN_G(7, 2),
  WHITE_PAWN_H(8, 2),
  BLACK_ROOK_LEFT(1, 8),
  BLACK_KNIGHT_LEFT(2, 8),
  BLACK_BISHOP_LEFT(3, 8),
  BLACK_QUEEN(4, 8),
  BLACK_KING(5, 8),
  BLACK_BISHOP_RIGHT(6, 8),
  BLACK_KNIGHT_RIGHT(7, 8),
  BLACK_ROOK_RIGHT(8, 8),
  BLACK_PAWN_A(1, 7),
  BLACK_PAWN_B(2, 7),
  BLACK_PAWN_C(3, 7),
  BLACK_PAWN_D(4, 7),
  BLACK_PAWN_E(5, 7),
  BLACK_PAWN_F(6, 7),
  BLACK_PAWN_G(7, 7),
  BLACK_PAWN_H(8, 7);

  private final int file;
  private final int rank;

  InitialPosition(int file, int rank) {
    this.file = file;
    this.rank = rank;
  }

  public Position getPosition() {
    return new Position(file, rank);
  }
}
