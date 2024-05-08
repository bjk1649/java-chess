package chess.piece;

import static chess.move.Movement.*;

import chess.position.Position;
import chess.move.Movement;
import java.util.List;
import java.util.ArrayList;

public class Rook extends Piece {
    private static final List<Movement> MOVABLE_DIRECTION = List.of(UP, DOWN, RIGHT, LEFT);

    public Rook(final Team team) {
        super(team);
    }

    @Override
    public List<Position> findPath(Position start, Position target, int fileGap, int rankGap) {
        int file = changeGapToDirection(fileGap);
        int rank = changeGapToDirection(rankGap);
        Movement movement = Movement.findMovementDirection(file, rank);

        verifyMovement(MOVABLE_DIRECTION, movement);

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
}
