package chess.piece;

public class Piece {
    private final Type type;
    private final Team team;

    public Piece(final Type type, final Team team) {
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
