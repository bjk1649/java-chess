package chess.move;

public enum Direction {
    POSITIVE(1),
    NEGATIVE(-1),
    STATIONARY(0);

    private final int value;

    Direction(final int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
