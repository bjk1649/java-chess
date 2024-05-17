package chess.piece;

import static chess.move.Movement.*;

import chess.move.Movement;
import chess.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    private static final List<Movement> MOVABLE_DIRECTION = List.of(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public List<Position> findPath(Position start, Position target) {
        Movement movement = Movement.findMovementByDirection(this, MOVABLE_DIRECTION, start, target);

        Position next = start;
        List<Position> positions = new ArrayList<>();

        while (true) {
            next = next.findNextPosition(movement);
            if (next.equals(target)) {
                break;
            }
            positions.add(next);
        }
        return positions;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
