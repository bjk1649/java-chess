package chess.piece;

import chess.move.Movement;
import chess.position.Position;
import java.util.List;

public abstract class Piece {
    private final Team team;

    public Piece(final Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public abstract List<Position> findPath(Position start, Position target, int fileGap, int rankGap);

    public int changeGapToDirection(int gap) {
        if (gap > 0) {
            return 1;
        } else if (gap < 0) {
            return -1;
        }
        return 0;
    }

    public void checkTargetPosition(Piece targetPiece) {
        if (targetPiece.getTeam().equals(this.team)) {
            throw new IllegalStateException("같은 팀 기물이 있는 위치로 이동 할 수 없습니다.");
        }
    }

    public void checkTargetPositionByPawn(Piece targetPiece, int fileGap) {
        if (targetPiece == null && fileGap != 0) {
            throw new IllegalArgumentException("폰은 상대 기물을 공격할 때만 대각선으로 이동할 수 있습니다.");
        }
        if (targetPiece != null && fileGap == 0) {
            throw new IllegalArgumentException("폰은 정면에 있는 상대 기물을 공격할 수 없습니다.");
        }
    }
}
