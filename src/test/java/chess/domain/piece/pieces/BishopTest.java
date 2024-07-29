package chess.domain.piece.pieces;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.position.Color;
import chess.domain.position.Position;
import chess.domain.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

  @Test
  @DisplayName("비숍_초기화_테스트")
  void 비숍_초기화_테스트() {
    //given
    Color color = Color.WHITE;

    //when
    Bishop bishop = new Bishop(color);

    //then
    assertThat(bishop.getColor()).isEqualTo(color);
  }

  @Test
  @DisplayName("비숍은_상하좌우로는_움직일_수_없다")
  void 비숍은_상하좌우로는_움직일_수_없다() {
    // given
    Bishop bishop = new Bishop(Color.WHITE);
    Position from = new Position(4, 4);
    Position to = new Position(4, 6);
    Board board = BoardFactory.createInitialBoard();

    // when & then
    assertThatThrownBy(() -> bishop.findPath(from, to, board.getMap()))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(ErrorMessage.INVALID_DIRECTION.getMessage());
  }
}
