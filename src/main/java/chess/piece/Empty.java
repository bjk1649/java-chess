package chess.piece;

import chess.position.Position;

import java.util.List;

public class Empty extends Piece {
    public Empty(final Team team) {
        super(team);
    }

    @Override
    public List<Position> findPath(Position start, Position target) {
        throw new IllegalStateException("경로를 찾을 기물이 존재하지 않습니다.");
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
