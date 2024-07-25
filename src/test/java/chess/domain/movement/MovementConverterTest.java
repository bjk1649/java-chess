package chess.domain.movement;

import chess.domain.position.Position;
import chess.domain.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MovementConverterTest {

  @Test
  @DisplayName("대각선_이동_변환_테스트")
  void 대각선_이동_변환_테스트() {
    Position from = new Position(1, 1);
    Position to = new Position(3, 3);

    Movement result = MovementConverter.convertMovement(from, to);
    assertThat(result).isEqualTo(Movement.UP_RIGHT);
  }

  @Test
  @DisplayName("대각선_이동_변환_테스트2")
  void 대각선_이동_변환_테스트2() {
    Position from = new Position(3, 3);
    Position to = new Position(1, 1);

    Movement result = MovementConverter.convertMovement(from, to);
    assertThat(result).isEqualTo(Movement.DOWN_LEFT);
  }

  @Test
  @DisplayName("같은_위치로_이동하는_경우_예외_처리한다")
  void 같은_위치로_이동하는_경우_예외_처리한다() {
    Position from = new Position(4, 4);
    Position to = new Position(4, 4); // 동일한 위치로 이동 시도

    assertThatThrownBy(() -> MovementConverter.convertMovement(from, to))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(ErrorMessage.SAME_POSITION.getMessage());
  }
}
