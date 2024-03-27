package chess;

public class Piece {
    public enum Team {
        BLACK,
        WHITE;
    }

    public enum Type {
        KING("K"),
        QUEEN("Q"),
        BISHOP("B"),
        KNIGHT("N"),
        ROOK("R"),
        PAWN("P");

        private final String symbol;

        Type(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    private final Type type;
    private final Team team;

    public Piece(Type type, Team team) {
        this.type = type;
        this.team = team;
    }

    public Type getType() {
        return type;
    }

    public Team getTeam() {
        return team;
    }
}
