package chess.model.movement;

import chess.model.position.Position;
import chess.model.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Path {

  private final List<Position> positions;

  public Path(final List<Position> positions) {
    this.positions = positions;
  }

  public void validatePath(final Set<Position> keySet, final Position target) {
    final List<Position> positions = new ArrayList<>(this.positions);
    positions.remove(target);
    positions.retainAll(keySet);
    if (!positions.isEmpty()) {
      throw new IllegalArgumentException(ErrorMessage.HAS_OBSTACLE.getMessage());
    }
  }
}
