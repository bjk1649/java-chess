package chess.piece;

public enum Type {
    KING,
    QUEEN,
    BISHOP,
    KNIGHT,
    ROOK,
    PAWN;

    public String extractFirstLetter() {
        return this.name().substring(0, 1);
    }
}
