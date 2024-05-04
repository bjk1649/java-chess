package chess.piece;

public class Piece {
    private final Team team;

    public Piece(final Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }
}
