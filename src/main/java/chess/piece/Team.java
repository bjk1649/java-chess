package chess.piece;

public enum Team {
    BLACK,
    WHITE;

    public boolean isSameTeam(Team team) {
        return this == team;
    }

    public Team changeTurn() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
