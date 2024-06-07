package chess.piece;

import chess.board.Board;
import chess.position.File;
import chess.position.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Score {
    KING_SCORE(King.class, 0),
    QUEEN_SCORE(Queen.class, 9),
    ROOK_SCORE(Rook.class, 5),
    BISHOP_SCORE(Bishop.class, 3),
    KNIGHT_SCORE(Knight.class, 2.5),
    PAWN_SCORE(Pawn.class, 1),
    MULTIPLE_PAWN_SCORE(Pawn.class, -0.5),
    EMPTY_PIECE_SCORE(Empty.class, 0);

    private final Class<? extends Piece> pieceType;
    private final double value;

    private Score(Class<? extends Piece> pieceType, final double value) {
        this.pieceType = pieceType;
        this.value = value;
    }

    public static double calculateScore(Board board, Team team) {
        double resultScore = 0;
        List<Score> scores = new ArrayList<>(collectPieceScore(board, team));
        for (Score score : scores) {
            resultScore += score.value;
        }
        return resultScore;
    }

    public static List<Score> collectPieceScore(Board board, Team team) {
        List<Score> scores = new ArrayList<>();
        for (int file = Position.FIRST_FILE; file <= Position.LAST_FILE; file++) {
            scores.addAll(Score.collectPieceByPosition(board, File.findByValue(file), team));
        }
        return scores;
    }

    public static List<Score> collectPieceByPosition(Board board, File file, Team team) {
        List<Score> scores = new ArrayList<>();
        for (int rank = Position.FIRST_RANK; rank <= Position.LAST_RANK; rank++) {
            Position position = new Position(file.value(), rank);
            if (board.findPiece(position).isSameTeam(team)) {
                scores.add(findScoreByPiece(board.findPiece(position)));
            }
        }
        return checkMultiplePawnInOneFile(scores);
    }

    public static List<Score> checkMultiplePawnInOneFile(List<Score> scores) {
        int pawnCount = 0;
        for (Score score : scores) {
            if (score == PAWN_SCORE) {
                pawnCount++;
            }
        }
        if (pawnCount > 1) {
            for (int i = 0; i < pawnCount; i++) {
                scores.add(MULTIPLE_PAWN_SCORE);
            }
        }
        return scores;
    }

    public static Score findScoreByPiece(Piece piece) {
        return Arrays.stream(values())
                .filter(score -> score.pieceType.equals(piece.getClass()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못 된 기물 형식입니다."));
    }
}
