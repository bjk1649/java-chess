package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Queen extends Piece {

    private static final int SCORE = 9;

    public Queen(Color color) {
        super(SCORE, color);
    }

    @Override
    public boolean isMovablePosition(Position source, Position target, Map<Position, Piece> board) {
        if (source.gapTwoPositionRow(target) == source.gapTwoPositionColumn(target)) {
            return isPossibleMoveDiagonal(source, target, board);
        }
        return isPossibleMoveStraightLine(source, target, board);
    }

    private boolean isPossibleMoveDiagonal(Position source, Position target, Map<Position, Piece> board) {
        List<Direction> possibleDot = Direction.diagonal();
        Map<String, Position> positions = new HashMap<>();
        positions.put(SOURCE, source);
        positions.put(TARGET, target);
        return isMovableLine(positions, possibleDot, board);
    }

    private boolean isPossibleMoveStraightLine(Position source, Position target, Map<Position, Piece> board) {
        List<Direction> possibleDot = Direction.straightLine();
        Map<String, Position> positions = new HashMap<>();
        positions.put(SOURCE, source);
        positions.put(TARGET, target);
        return isMovableLine(positions, possibleDot, board);
    }
}
