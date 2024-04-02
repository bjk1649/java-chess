package chess.piece;

public class Piece {
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
