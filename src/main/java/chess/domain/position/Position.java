package chess.domain.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {
    private final Rank rank;
    private final File file;

    private static final Map<String, Position> CACHE;

    static {
        CACHE = new HashMap<>();
        for (File file : File.values()) {
            cacheEachFile(file);
        }
    }

    private static void cacheEachFile(final File file) {
        for (Rank rank : Rank.values()) {
            CACHE.put(rank.name() + file.name(), new Position(rank, file));
        }
    }

    private Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Position of(Rank rank, File file) {
        return CACHE.get(rank.name() + file.name());
    }

    public int calculateFileDistance(final Position startPosition) {
        return file.calculateDistance(startPosition.file);
    }

    public int calculateRankDistance(final Position startPosition) {
        return rank.calculateDistance(startPosition.rank);
    }

    public Position move(final int rankDirection, final int fileDirection) {
        return Position.of(rank.plus(rankDirection), file.plus(fileDirection));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
