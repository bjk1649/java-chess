package chess.piece;

import static chess.move.Movement.*;

import chess.move.Movement;
import chess.position.Position;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private static final List<Movement> MOVABLE_DIRECTION = List.of(UP, DOWN, RIGHT, LEFT, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);

    public King(final Team team) {
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
}
