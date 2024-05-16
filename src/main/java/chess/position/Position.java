package chess.position;

import chess.move.Movement;
import chess.piece.Team;
import java.util.Objects;

public class Position {
    public static final int FIRST_FILE = 1;
    public static final int LAST_FILE = 8;
    public static final int FIRST_RANK = 1;
    public static final int LAST_RANK = 8;
    public static final int BLACK_PAWN_INITIAL_RANK = 7;
    public static final int WHITE_PAWN_INITIAL_RANK = 2;

    private final File file;
    private final Rank rank;

    public Position(int file, int rank) {
        checkPositionRange(file, rank);
        this.file = File.findByValue(file);
        this.rank = Rank.findByValue(rank);
    }

    public void checkPositionRange(int file, int rank) {
        if (file < FIRST_FILE || file > LAST_FILE) {
            throw new IllegalArgumentException("File 범위를 벗어난 위치입니다.(1~8)");
        }
        if (rank < FIRST_RANK || rank > LAST_RANK) {
            throw new IllegalArgumentException("Rank 범위를 벗어난 위치입니다.(1~8)");
        }
    }

    public static int fileGap(Position start, Position target) {
        return File.calculateFileGap(start.file, target.file);
    }

    public static int rankGap(Position start, Position target) {
        return Rank.calculateRankGap(start.rank, target.rank);
    }

    public Position findNextPosition(Movement movement) {
        int file = this.file.value();
        int rank = this.rank.value();
        return movement.nextPosition(file, rank);
    }

    public boolean onInitialWhitePawnRank(Team team) {
        if (team.isSameTeam(Team.WHITE)) {
            return this.rank.value() == WHITE_PAWN_INITIAL_RANK;
        }
        return false;
    }

    public boolean onInitialBlackPawnRank(Team team) {
        if (team.isSameTeam(Team.BLACK)) {
            return this.rank.value() == BLACK_PAWN_INITIAL_RANK;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rank == position.rank &&
                file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
