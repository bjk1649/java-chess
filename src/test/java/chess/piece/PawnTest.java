package chess.piece;

import static chess.move.Movement.UP;
import static chess.move.Movement.UP_UP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.move.Movement;
import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;


public class PawnTest {
    private static final List<Movement> WHITE_PAWN_FIRST_MOVABLE_DIRECTION = List.of(UP, UP_UP);

    @DisplayName("폰이 초기 위치에서 움직일 때, 두 칸 움직일 수 있다.")
    @Test
    void moveTwice() {
        Piece pawn = new Pawn(Team.WHITE);
        Position start = new Position(2,2);
        Position target = new Position(2,4);
        List<Position> path = new ArrayList<>();
        path.add(target);
        assertThat(pawn.findPath(start, target)).isEqualTo(path);
    }

    @DisplayName("폰이 초기 위치가 아닌 곳에서 두 칸 움직이면 예외처리.")
    @Test
    void moveTwiceException() {
        Piece pawn = new Pawn(Team.WHITE);
        Position start = new Position(2,3);
        Position target = new Position(2,5);
        assertThatThrownBy(() -> pawn.findPath(start, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 기물이 이동할 수 있는 범위를 벗어났습니다.");
    }

    @DisplayName("폰 대각선 방향에 적이 있으면 대각선으로 이동할 수 있다.")
    @Test
    void attackEnemy() {
        Piece pawn = new Pawn(Team.WHITE);
        Position start = new Position(6,6);
        Position target = new Position(7,7);
        List<Position> path = new ArrayList<>();
        path.add(target);
        assertThat(pawn.findPath(start, target)).isEqualTo(path);
    }

    @DisplayName("폰 대각선 방향에 같은 팀 기물이 있을 때 대각선으로 이동하면 예외처리.")
    @Test
    void attackSameTeam() {
        Piece pawn = new Pawn(Team.WHITE);
        Piece allyPawn = new Pawn(Team.WHITE);
        Position start = new Position(6,6);
        Position target = new Position(7,7);
        assertThatThrownBy(() -> pawn.checkTargetPosition(allyPawn, start, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("같은 팀 기물이 있는 위치로 이동 할 수 없습니다.");
    }

    @DisplayName("폰은 정면에 적이 있으면 정면으로 이동할 수 없다.")
    @Test
    void moveForwardException() {
        Piece pawn = new Pawn(Team.WHITE);
        Piece enemyPawn = new Pawn(Team.BLACK);
        Position start = new Position(6,6);
        Position target = new Position(6,7);
        assertThatThrownBy(() -> pawn.checkTargetPosition(enemyPawn, start, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰은 정면에 있는 상대 기물을 공격할 수 없습니다.");
    }
}
