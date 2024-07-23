package chess.domain.position;

import chess.domain.movement.Movement;
import java.util.Objects;

public class Position {

  private final File file;
  private final Rank rank;

  public Position(final int file, final int rank) {
    this.file = File.from(file);
    this.rank = Rank.from(rank);
  }

  public Position calculateNextPosition(Movement movement) {
    return new Position(file.getValue() + movement.getFileChange(),
        rank.getValue() + movement.getRankChange());
  }

  public int calculateFileDiff(Position other) {
    return this.file.calculateDiff(other.file);
  }

  public int calculateRankDiff(Position other) {
    return this.rank.calculateDiff(other.rank);
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
    return file == position.file && rank == position.rank;
  }

  @Override
  public int hashCode() {
    return Objects.hash(file, rank);
  }

  public File getFile() {
    return file;
  }

  public Rank getRank() {
    return rank;
  }
}
