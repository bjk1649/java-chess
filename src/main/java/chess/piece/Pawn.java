package chess.piece;

import static chess.move.Movement.*;

import chess.move.Movement;
import chess.position.Position;
import java.util.ArrayList;
import java.util.Collections;
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
        if (this.getTeam().equals(Team.WHITE)) {
            movements.addAll(updateWhitePawnMovableDirection(start));
            Movement movement = findMovement(this, movements, start, target);
            positions = findPawnPath(start, movement);
        }
        if (this.getTeam().equals(Team.BLACK)) {
            movements.addAll(updateBlackPawnMovableDirection(start));
            Movement movement = findMovement(this, movements, start, target);
            positions = findPawnPath(start, movement);
        }
        return positions;
    }

    private List<Movement> updateWhitePawnMovableDirection(Position start) {
        List<Movement> movements = new ArrayList<>();

        if (start.onInitialWhitePawnRank(this.getTeam())) {
            movements.addAll(WHITE_PAWN_FIRST_MOVABLE_DIRECTION);
            return movements;
        }
        movements.addAll(WHITE_PAWN_MOVABLE_DIRECTION);
        return movements;
    }

    private List<Movement> updateBlackPawnMovableDirection(Position start) {
        List<Movement> movements = new ArrayList<>();

        if (start.onInitialBlackPawnRank(this.getTeam())) {
            movements.addAll(BLACK_PAWN_FIRST_MOVABLE_DIRECTION);
            return movements;
        }
        movements.addAll(BLACK_PAWN_MOVABLE_DIRECTION);
        return movements;
    }

    private List<Position> findPawnPath(Position start, Movement movement) {
        List<Position> positions = new ArrayList<>();
        Position next = start.findNextPosition(movement);
        positions.add(next);
        return positions;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
