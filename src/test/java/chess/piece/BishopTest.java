package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class BishopTest {
    @DisplayName("경로에 문제가 없을 시, 경로를 반환한다.")
    @Test
    void findPathByBishop() {
        Piece piece = new Bishop(Team.WHITE);
        Position start = new Position(1,1);
        Position target = new Position(5,5);
        List<Position> path = new ArrayList<>();
        path.add(new Position(2,2));
        path.add(new Position(3,3));
        path.add(new Position(4,4));

        assertThat(piece.findPath(start, target)).isEqualTo(path);
    }
}
