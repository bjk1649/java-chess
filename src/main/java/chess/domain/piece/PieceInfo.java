package chess.domain.piece;

public enum PieceInfo {
  KING(0),
  QUEEN(9),
  ROOK(5),
  BISHOP(3),
  KNIGHT(2.5),
  PAWN(1);

  private final double baseScore;

  PieceInfo(double baseScore) {
    this.baseScore = baseScore;
  }

  public double getBaseScore() {
    return baseScore;
  }
}
