package chess;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row &&
                column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
