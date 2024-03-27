package chess;

public class Position {
    private final char row;
    private final int column;
    private Position(char row, int column) {
        this.row = row;
        this.column = column;
    }
}
