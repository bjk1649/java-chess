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

class KingTest {

  @Test
  @DisplayName("킹_초기화_테스트")
  void 킹_초기화_테스트() {
    //given
    Color color = Color.WHITE;

    //when
    King king = new King(color);

    //then
    assertThat(king.getColor()).isEqualTo(color);
  }

  @Test
  @DisplayName("킹은_두_칸_이상_움직일_수_없다")
  void 킹은_두_칸_이상_움직일_수_없다() {
    // given
    King king = new King(Color.WHITE);
    Position from = new Position(4, 4);
    Position to = new Position(6, 6);
    Map<Position, Piece> board = BoardFactory.createInitialBoard().getMap();

    // when & then
    assertThatThrownBy(() -> king.findPath(from, to, board))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(ErrorMessage.INVALID_DIRECTION.getMessage());
  }
}
