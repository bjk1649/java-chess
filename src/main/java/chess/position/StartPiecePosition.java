package chess.position;

public enum StartPiecePosition {
    BLACK_ROOK_LEFT(1, 8),
    BLACK_KNIGHT_LEFT(2, 8),
    BLACK_BISHOP_LEFT(3, 8),
    BLACK_QUEEN(4, 8),
    BLACK_KING(5, 8),
    BLACK_BISHOP_RIGHT(6, 8),
    BLACK_KNIGHT_RIGHT(7, 8),
    BLACK_ROOK_RIGHT(8, 8),
    WHITE_ROOK_LEFT(1, 1),
    WHITE_KNIGHT_LEFT(2, 1),
    WHITE_BISHOP_LEFT(3, 1),
    WHITE_QUEEN(4, 1),
    WHITE_KING(5, 1),
    WHITE_BISHOP_RIGHT(6, 1),
    WHITE_KNIGHT_RIGHT(7, 1),
    WHITE_ROOK_RIGHT(8, 1);

    private final int file;
    private final int rank;

    StartPiecePosition(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position getPosition() {
        return new Position(file, rank);
    }
}
