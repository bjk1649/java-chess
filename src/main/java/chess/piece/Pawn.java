package chess.piece;

import static chess.move.Movement.*;

import chess.move.Direction;
import chess.move.Movement;
import chess.position.Position;
import java.util.ArrayList;
import java.util.List;


public class Pawn extends Piece {
    private static final List<Movement> BLACK_PAWN_MOVABLE_DIRECTION = List.of(DOWN, DOWN_RIGHT, DOWN_LEFT);
    private static final List<Movement> BLACK_PAWN_FIRST_MOVABLE_DIRECTION = List.of(DOWN, DOWN_DOWN);
    private static final List<Movement> WHITE_PAWN_MOVABLE_DIRECTION = List.of(UP, UP_RIGHT, UP_LEFT);
    private static final List<Movement> WHITE_PAWN_FIRST_MOVABLE_DIRECTION = List.of(UP, UP_UP);

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public List<Position> findPath(Position start, Position target) {
        List<Position> positions = new ArrayList<>();
        List<Movement> movements = new ArrayList<>();
        if (this.isSameTeam(Team.WHITE)) {
            movements.addAll(updateWhitePawnMovableDirection(start));
            Movement movement = findMovement(movements, start, target);
            Position next = start.findNextPosition(movement);
            positions.add(next);
        }
        if (this.isSameTeam(Team.BLACK)) {
            movements.addAll(updateBlackPawnMovableDirection(start));
            Movement movement = findMovement(movements, start, target);
            Position next = start.findNextPosition(movement);
            positions.add(next);
        }
        return positions;
    }

    private List<Movement> updateWhitePawnMovableDirection(Position start) {
        List<Movement> movements = new ArrayList<>();
        if (start.onInitialRankWhitePawn()) {
            movements.addAll(WHITE_PAWN_FIRST_MOVABLE_DIRECTION);
            return movements;
        }
        movements.addAll(WHITE_PAWN_MOVABLE_DIRECTION);
        return movements;
    }

    private List<Movement> updateBlackPawnMovableDirection(Position start) {
        List<Movement> movements = new ArrayList<>();
        if (start.onInitialRankBlackPawn()) {
            movements.addAll(BLACK_PAWN_FIRST_MOVABLE_DIRECTION);
            return movements;
        }
        movements.addAll(BLACK_PAWN_MOVABLE_DIRECTION);
        return movements;
    }

    @Override
    public void checkTargetPosition(Piece targetPiece, Position start, Position target) {
        if (this.isSameTeam(targetPiece)) {
            throw new IllegalStateException("같은 팀 기물이 있는 위치로 이동 할 수 없습니다.");
        } else if (!this.isSameTeam(targetPiece)) {
            checkEnemyPieceByPawn(targetPiece, start, target);
        }
    }

    private void checkEnemyPieceByPawn(Piece targetPiece, Position start, Position target) {
        if (targetPiece.isEmpty() && !Direction.isStationary(start, target)) {
            throw new IllegalArgumentException("폰은 상대 기물을 공격할 때만 대각선으로 이동할 수 있습니다.");
        }
        if (!targetPiece.isEmpty() && Direction.isStationary(start, target)) {
            throw new IllegalArgumentException("폰은 정면에 있는 상대 기물을 공격할 수 없습니다.");
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
