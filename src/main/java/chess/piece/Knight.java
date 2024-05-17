package chess.piece;

import static chess.move.Movement.*;

import chess.move.Movement;
import chess.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    private static final List<Movement> MOVABLE_DIRECTION = List.of(
            UP_UP_RIGHT, UP_UP_LEFT, RIGHT_RIGHT_UP, RIGHT_RIGHT_DOWN,
            DOWN_DOWN_RIGHT, DOWN_DOWN_LEFT, LEFT_LEFT_UP, LEFT_LEFT_DOWN);

    public Knight(final Team team) {
        super(team);
    }

    @Override
    public List<Position> findPath(Position start, Position target) {
        Movement movement = Movement.findMovement(this, MOVABLE_DIRECTION, start, target);

        List<Position> positions = new ArrayList<>();
        Position next = start.findNextPosition(movement);
        positions.add(next);
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
