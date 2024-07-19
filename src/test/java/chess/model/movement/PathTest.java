package chess.model.movement;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.model.position.Position;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PathTest {

  @Test
  @DisplayName("경로에_다른_체스_말이_있다면_예외_처리한다")
  void 경로에_다른_체스_말이_있다면_예외_처리한다() {
    // given
    Position start = new Position(1, 1);
    Position obstacle = new Position(1, 2);
    Position target = new Position(1, 3);

    // when
    Path path = new Path(List.of(start, obstacle, target));

    // then
    assertThatThrownBy(() -> path.validateObstacle(Set.of(obstacle)))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("경로에_장애물이_없다면_예외_처리하지_않는다")
  void 경로에_장애물이_없다면_예외_처리하지_않는다() {
    // given
    Position start = new Position(1, 1);
    Position target = new Position(1, 3);

    // when
    Path path = new Path(List.of(start, target));

    // then
    path.validateObstacle(Set.of()); // 장애물이 없는 경우 예외가 발생하지 않음
  }
}
