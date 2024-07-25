package chess.domain.piece.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.position.Color;
import chess.domain.position.Position;
import chess.domain.ErrorMessage;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

  @Test
  @DisplayName("나이트_초기화_테스트")
  void 나이트_초기화_테스트() {
    //given
    Color color = Color.WHITE;

    //when
    Knight knight = new Knight(color);

    //then
    assertThat(knight.getColor()).isEqualTo(color);
  }

  @Test
  @DisplayName("나이트는_L자_형태_외의_방식으로는_움직일_수_없다")
  void 나이트는_L자_형태_외의_방식으로는_움직일_수_없다() {
    // given
    Knight knight = new Knight(Color.WHITE);
    Position from = new Position(4, 4);
    Position to = new Position(6, 6);
    Map<Position, Piece> board = BoardFactory.createInitialBoard().getMap();

    // when & then
    assertThatThrownBy(() -> knight.findPath(from, to, board))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(ErrorMessage.INVALID_DIRECTION.getMessage());
  }
}
