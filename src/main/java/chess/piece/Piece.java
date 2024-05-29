package chess.piece;

import chess.position.Position;
import java.util.List;

public abstract class Piece {
    private final Team team;

    public Piece(final Team team) {
        this.team = team;
    }

    public abstract List<Position> findPath(Position start, Position target);

    public abstract boolean isEmpty();

    public void checkTargetPosition(Piece targetPiece, Position start, Position target) {
        if (targetPiece.team.equals(this.team)) {
            throw new IllegalStateException("같은 팀 기물이 있는 위치로 이동 할 수 없습니다.");
        }
    }

    public boolean isSameTeam(Piece targetPiece) {
        return this.team.isSameTeam(targetPiece.team);
    }

    public boolean isSameTeam(Team team) { return this.team.isSameTeam(team);}
}
