package chess.model.piece.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.position.Color;
import chess.model.position.Position;
import chess.view.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

  @Test
  @DisplayName("퀸_초기화_테스트")
  void 퀸_초기화_테스트() {
    //given
    Color color = Color.WHITE;

    //when
    Queen queen = new Queen(color);

    //then
    assertThat(queen.getColor()).isEqualTo(color);
  }

  @Test
  @DisplayName("올바르지_않은_경로인_경우_예외_처리한다")
  void 올바르지_않은_경로인_경우_예외_처리한다() {
    //given
    Queen queen = new Queen(Color.WHITE);
    Position from = new Position(4, 4);
    Position to = new Position(6, 5);
    // when & then
    assertThatThrownBy(() -> queen.findPath(from, to))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(ErrorMessage.INVALID_DIRECTION.getMessage());
  }
}
