package chess.domain;

import chess.domain.position.Rank;

public enum Team {
    BLACK,
    WHITE,
    EMPTY;

    public static Team initialOf(Rank rank) {
        if (rank == Rank.SEVEN || rank == Rank.EIGHT) {
            return BLACK;
        }
        return WHITE;
    }
}
