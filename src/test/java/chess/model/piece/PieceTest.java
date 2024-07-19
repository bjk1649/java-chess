package chess.model.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.model.movement.Movement;
import chess.model.movement.Path;
import chess.model.position.Color;
import chess.model.position.Position;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

  @Test
  @DisplayName("피스_객체_생성_시_색이_올바르게_설정되어야_한다")
  void 피스_객체_생성_시_색이_올바르게_설정되어야_한다() {
    //given
    Color color = Color.WHITE;

    //when
    Piece piece = new TestPiece(color);

    //then
    assertThat(piece.getColor()).isEqualTo(color);
  }

  @Test
  @DisplayName("유효하지_않은_이동이라면_예외_처리한다")
  void 유효하지_않은_이동이라면_예외_처리한다() {
    // given
    Piece piece = new TestPiece(Color.WHITE);
    Movement movement = Movement.LEFT;
    List<Movement> availableMovements = Arrays.asList(Movement.UP, Movement.DOWN);

    // when & then
    assertThatThrownBy(() -> piece.validateMovement(movement, availableMovements))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("같은_색의_피스라면_예외_처리한다")
  void 같은_색의_피스라면_예외_처리한다() {
    // given
    Piece piece1 = new TestPiece(Color.WHITE);
    Piece piece2 = new TestPiece(Color.WHITE);

    // when & then
    assertThatThrownBy(() -> piece1.validateSameColor(piece2))
        .isInstanceOf(IllegalArgumentException.class);
  }

  private static class TestPiece extends Piece {
    public TestPiece(Color color) {
      super(color);
    }

    @Override
    public Path findPath(Position from, Position to) {
      // 테스트 목적으로 간단히 작성
      return new Path(List.of(to));
    }

    @Override
    public PieceInfo pieceType() {
      return PieceInfo.KING;
    }
  }
}
