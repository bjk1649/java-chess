package chess.position;

import java.util.Objects;

public class Position {
    public static final int FIRST_FILE = 1;
    public static final int LAST_FILE = 8;
    public static final int FIRST_RANK = 1;
    public static final int LAST_RANK = 8;
    public static final int BLACK_PAWN_RANK = 7;
    public static final int WHITE_PAWN_RANK = 2;

    private final File file;
    private final Rank rank;

    public Position(int file, int rank) {
        checkPositionRange(file, rank);
        this.file = File.findByValue(file);
        this.rank = Rank.findByValue(rank);
    }

    public void checkPositionRange(int file, int rank) {
        if (file < FIRST_FILE || file > LAST_FILE || rank < FIRST_RANK || rank > LAST_RANK) {
            throw new IllegalArgumentException("범위를 벗어난 위치입니다.(1~8)");
        }
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
