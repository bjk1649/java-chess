package chess.piece;

import static chess.move.Movement.*;

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
}
