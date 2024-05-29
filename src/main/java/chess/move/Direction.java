package chess.move;

import chess.position.Position;

public enum Direction {
    POSITIVE(1),
    NEGATIVE(-1),
    STATIONARY(0);

    private final int value;

    Direction(final int value) {
        this.value = value;
    }

    public static int normalizeGapToDirection(int gap) {
        if (gap > 0) {
            return POSITIVE.value;
        } else if (gap < 0) {
            return NEGATIVE.value;
        }
        return STATIONARY.value;
    }

    public static boolean isStationary(Position start, Position target) {
        return start.fileGap(target) == STATIONARY.value;
    }
}
