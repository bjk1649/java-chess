package chess.piece;

public enum Score {
    KING_SCORE(King.class, 0),
    QUEEN_SCORE(Queen.class, 9),  // 예시로 추가
    ROOK_SCORE(Rook.class, 5),
    BISHOP_SCORE(Bishop.class, 3),
    KNIGHT_SCORE(Knight.class, 2.5),
    PAWN_SCORE(Pawn.class, 1);


    private final Class<? extends Piece> pieceType;
    private final double value;

    private Score(Class<? extends Piece> pieceType, final double value) {
        this.pieceType = pieceType;
        this.value = value;
    }


}
