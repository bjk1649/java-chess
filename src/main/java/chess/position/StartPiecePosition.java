package chess.position;

public enum StartPiecePosition {
    BLACK_ROOK_LEFT(1,8),
    BLACK_KNIGHT_LEFT(2,8),
    BLACK_BISHOP_LEFT(3,8),
    BLACK_KING(4,8),
    BLACK_QUEEN(5,8),
    BLACK_BISHOP_RIGHT(6,8),
    BLACK_KNIGHT_RIGHT(7,8),
    BLACK_ROOK_RIGHT(8,8),
    BLACK_PAWN_1(1,7),
    BLACK_PAWN_2(2,7),
    BLACK_PAWN_3(3,7),
    BLACK_PAWN_4(4,7),
    BLACK_PAWN_5(5,7),
    BLACK_PAWN_6(6,7),
    BLACK_PAWN_7(7,7),
    BLACK_PAWN_8(8,7),
    WHITE_ROOK_LEFT(1,1),
    WHITE_KNIGHT_LEFT(2,1),
    WHITE_BISHOP_LEFT(3,1),
    WHITE_KING(4,1),
    WHITE_QUEEN(5,1),
    WHITE_BISHIP_RIGHT(6,1),
    WHITE_KNIGT_RIGHT(7,1),
    WHITE_ROOK_RIGHT(8,1),
    WHITE_PAWN_1(1,2),
    WHITE_PAWN_2(2,2),
    WHITE_PAWN_3(3,2),
    WHITE_PANW_4(4,2),
    WHITE_PAWN_5(5,2),
    WHITE_PAWN_6(6,2),
    WHITE_PAWN_7(7,2),
    WHITE_PAWN_8(8,2);

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
