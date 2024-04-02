package chess.position;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(int file, int rank) {
        this.file = File.findByValue(file);
        this.rank = Rank.findByValue(rank);
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
