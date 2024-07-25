package chess.domain.position;

import static chess.domain.movement.MovementConverter.convertMovement;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.movement.Movement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

  @Test
  @DisplayName("포지션_객체_생성_시_파일과_랭크가_올바르게_설정되어야_한다")
  void 포지션_객체_생성_시_파일과_랭크가_올바르게_설정되어야_한다() {
    //given
    int file = 3;
    int rankNumber = 4;

    //when
    Position position = new Position(file, rankNumber);

    //then
    assertThat(position.getFile()).isEqualTo(File.C);
    assertThat(position.getRank()).isEqualTo(Rank.RANK_4);
  }

  @Test
  @DisplayName("동일한_위치로_이동한다면_예외_처리한다")
  void 동일한_위치로_이동한다면_예외_처리한다() {
    // given
    Position from = new Position(3, 4);
    Position to = new Position(3, 4);

    // when & then
    assertThatThrownBy(() -> convertMovement(from, to))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("유효하지_않은_방향으로_이동한다면_예외_처리한다")
  void 유효하지_않은_방향으로_이동한다면_예외_처리한다() {
    // given
    Position from = new Position(3, 4);
    Position to = new Position(8, 7);

    // when & then
    assertThatThrownBy(() -> convertMovement(from, to))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("올바르게_이동_방향을_계산하고_찾는지_테스트")
  void 올바르게_이동_방향을_계산하고_찾는지_테스트() {
    //given
    Position from = new Position(1, 1);
    Position to = new Position(2, 3);
    //when
    Movement movement = convertMovement(from, to);
    //then
    assertThat(movement).isEqualTo(Movement.UP_UP_RIGHT);
  }

  @Test
  @DisplayName("올바르게_이동_방향을_계산하고_찾는지_테스트2")
  void 올바르게_이동_방향을_계산하고_찾는지_테스트2() {
    //given
    Position from = new Position(1, 1);
    Position to = new Position(1, 7);
    //when
    Movement movement = convertMovement(from,to);
    //then
    assertThat(movement).isEqualTo(Movement.UP);
  }

  @Test
  @DisplayName("올바르게_이동_방향을_계산하고_찾는지_테스트3")
  void 올바르게_이동_방향을_계산하고_찾는지_테스트3() {
    //given
    Position from = new Position(1, 1);
    Position to = new Position(7, 1);
    //when
    Movement movement = convertMovement(from, to);
    //then
    assertThat(movement).isEqualTo(Movement.RIGHT);
  }
}
