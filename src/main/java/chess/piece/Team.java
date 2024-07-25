package chess.piece;

public enum Team {
    BLACK,
    WHITE,
    NONE;

    public boolean isSameTeam(Team team) {
        return this == team;
    }

    public Team changeTurn() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public Team getTurnByBinary(int n) {
        if (n == 0) return Team.BLACK;
        return Team.WHITE;
    }
}
