package chess.domain.position;

public enum Color {
  BLACK,
  WHITE;

  public boolean isSameColor(Color color) {
    return this == color;
  }

  public Color changeTurn() {
    if (this == WHITE) {
      return BLACK;
    }
    return WHITE;
  }
}
