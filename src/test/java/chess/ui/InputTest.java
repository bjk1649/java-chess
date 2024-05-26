package chess.ui;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import chess.ui.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class InputTest {
    @DisplayName("잘못된 move 명령어를 입력 받을 시 예외처리")
    @Test
    public void inputMoveExceptionTest() {
        assertThatThrownBy(() -> InputView.separateCommand("movve b1 b2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("올바른 move 명령을 입력해 주세요.");
    }
}
