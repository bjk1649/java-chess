package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {

  @ParameterizedTest
  @ValueSource(ints = {0, 9}) // 경계 값
  @DisplayName("규격이_올바르지_않은_경우_예외_처리한다")
  void 규격이_올바르지_않은_경우_예외_처리한다(int input) {
    assertThatThrownBy(() -> File.from(input))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("ERROR");
  }
}
