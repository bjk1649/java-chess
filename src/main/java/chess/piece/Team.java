package chess.piece;

public enum Team {
    BLACK,
    WHITE;

    public boolean isSameTeam(Team team) {
        return this == team;
    }
}
