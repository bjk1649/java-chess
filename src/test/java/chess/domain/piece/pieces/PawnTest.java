package chess.domain.piece.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.BoardFactory;
import chess.domain.movement.Path;
import chess.domain.piece.Piece;
import chess.domain.position.Color;
import chess.domain.position.Position;
import chess.domain.ErrorMessage;
import java.util.Map;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {
  @Test
  @DisplayName("폰_초기화_테스트")
  void 폰_초기화_테스트() {
    //given
    Color color = Color.WHITE;

    //when
    Pawn pawn = new Pawn(color);

    //then
    assertThat(pawn.getColor()).isEqualTo(color);
  }

  @ParameterizedTest
  @CsvSource({
      "2, 2, 2, 3, WHITE",
      "2, 7, 2, 6, BLACK"
  })
  @DisplayName("폰은_앞으로_한_칸_움직일_수_있다")
  void 폰은_앞으로_한_칸_움직일_수_있다(int fromFile, int fromRank, int toFile, int toRank, Color color) {
    // given
    Pawn pawn = new Pawn(color);
    Position from = new Position(fromFile, fromRank);
    Position to = new Position(toFile, toRank);
    Map<Position, Piece> board = BoardFactory.createInitialBoard().getMap();

    // when
    Path path = pawn.findPath(from, to, board);

    // then
    assertThat(path)
        .extracting("positions", InstanceOfAssertFactories.list(Position.class))
        .containsExactly(to);
  }

  @ParameterizedTest
  @CsvSource({
      "2, 2, 2, 4, WHITE",
      "2, 7, 2, 5, BLACK"
  })
  @DisplayName("폰은_초기_위치에서_두_칸_움직일_수_있다")
  void 폰은_초기_위치에서_두_칸_움직일_수_있다(int fromFile, int fromRank, int toFile, int toRank, Color color) {
    // given
    Pawn pawn = new Pawn(color);
    Position from = new Position(fromFile, fromRank);
    Position to = new Position(toFile, toRank);
    Map<Position, Piece> board = BoardFactory.createInitialBoard().getMap();

    // when
    Path path = pawn.findPath(from, to, board);

    // then
    assertThat(path)
        .extracting("positions", InstanceOfAssertFactories.list(Position.class))
        .containsExactly(
            new Position(fromFile, fromRank + (color == Color.WHITE ? 1 : -1)),
            to);
  }

  @ParameterizedTest
  @CsvSource({
      "2, 2, 3, 3, WHITE",
      "2, 2, 1, 3, WHITE",
      "2, 7, 3, 6, BLACK",
      "2, 7, 1, 6, BLACK"
  })
  @DisplayName("폰은_대각선으로_한_칸_움직여_적을_공격할_수_있다")
  void 폰은_대각선으로_한_칸_움직여_적을_공격할_수_있다(int fromFile, int fromRank, int toFile, int toRank, Color color) {
    // given
    Pawn pawn = new Pawn(color);
    Position from = new Position(fromFile, fromRank);
    Position to = new Position(toFile, toRank);
    Map<Position, Piece> board = BoardFactory.createInitialBoard().getMap();

    // when
    Path path = pawn.findPath(from, to, board);

    // then
    assertThat(path)
        .extracting("positions", InstanceOfAssertFactories.list(Position.class))
        .containsExactly(to);
  }

  @Test
  @DisplayName("폰이_뒤로_움직인다면_예외_처리한다")
  void 폰이_뒤로_움직인다면_예외_처리한다() {
    // given
    Pawn pawn = new Pawn(Color.WHITE);
    Position from = new Position(2, 3);
    Position to = new Position(2, 2);
    Map<Position, Piece> board = BoardFactory.createInitialBoard().getMap();

    // when & then
    assertThatThrownBy(() -> pawn.findPath(from, to, board))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(ErrorMessage.INVALID_DIRECTION.getMessage());
  }

  @Test
  @DisplayName("폰이_초기_위치가_아닐_때_두_칸_움직인다면_예외_처리한다")
  void 폰이_초기_위치가_아닐_때_두_칸_움직인다면_예외_처리한다() {
    // given
    Pawn pawn = new Pawn(Color.WHITE);
    Position from = new Position(2, 3);
    Position to = new Position(2, 5);
    Map<Position, Piece> board = BoardFactory.createInitialBoard().getMap();

    // when & then
    assertThatThrownBy(() -> pawn.findPath(from, to, board))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(ErrorMessage.INVALID_DIRECTION.getMessage());
  }
}
