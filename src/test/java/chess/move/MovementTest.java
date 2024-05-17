package chess.move;

import chess.piece.Knight;
import chess.piece.Piece;
import chess.piece.Team;
import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.move.Movement.*;
import static chess.move.Movement.LEFT_LEFT_DOWN;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MovementTest {
    private static final List<Movement> MOVABLE_DIRECTION = List.of(
            UP_UP_RIGHT, UP_UP_LEFT, RIGHT_RIGHT_UP, RIGHT_RIGHT_DOWN,
            DOWN_DOWN_RIGHT, DOWN_DOWN_LEFT, LEFT_LEFT_UP, LEFT_LEFT_DOWN);

    @DisplayName("존재하지 않는 이동 규칙일 경우 예외처리")
    @Test
    void checkMovementIsPossible() {
        Piece piece = new Knight(Team.WHITE);
        Position start = new Position(2,1);
        Position target = new Position(4,8);
        assertThatThrownBy(() -> Movement.findMovement(piece, MOVABLE_DIRECTION, start, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 이동 규칙입니다.");
    }

    @DisplayName("이동 규칙은 존재하지만 해당 기물의 이동 규칙와 일치하지 않을 경우 예외처리")
    @Test
    void checkMovementIsPossible2() {
        Piece piece = new Knight(Team.WHITE);
        Position start = new Position(1,1);
        Position target = new Position(1,3);
        assertThatThrownBy(() -> Movement.findMovement(piece, MOVABLE_DIRECTION, start, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 기물이 이동할 수 있는 범위를 벗어났습니다.");
    }
}
