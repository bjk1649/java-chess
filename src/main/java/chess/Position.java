package chess;

import java.io.IOException;

public class Position {
    private final char row;
    private final int column;
    public Position(char row, int column) {
        this.row = row;
        this.column = column;
    }

    public char getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
