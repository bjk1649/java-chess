package chess.piece;

public class Piece {
    private final Type type;
    private final Team team;

    public Piece(final Type type, final Team team) {
        this.type = type;
        this.team = team;
    }

    public String convertTypeToSymbol(Piece piece) {
        if (piece.team.equals(Team.WHITE)) {
            return convertToLowercase(piece.type.extractFirstLetter());
        }
        return piece.type.extractFirstLetter();
    }

    public String convertToLowercase(String symbol) {
        return symbol.toLowerCase();
    }
}
