package chess.move;

import chess.piece.Piece;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

import java.util.Arrays;
import java.util.List;

public enum Movement {
    UP(0,1),
    DOWN(0,-1),
    RIGHT(1,0),
    LEFT(-1,0),
    UP_RIGHT(1,1),
    UP_LEFT(-1,1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1,-1),
    UP_UP_RIGHT(1,2),
    UP_UP_LEFT(-1,2),
    DOWN_DOWN_RIGHT(1,-2),
    DOWN_DOWN_LEFT(-1,-2),
    RIGHT_RIGHT_UP(2,1),
    RIGHT_RIGHT_DOWN(2,-1),
    LEFT_LEFT_UP(-2,1),
    LEFT_LEFT_DOWN(-2,-1),
    UP_UP(0,2),
    DOWN_DOWN(0,-2);

    private final int file;
    private final int rank;

    Movement(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Movement findMovementDirection(int file, int rank) {
        return Arrays.stream(values())
                .filter(movement -> movement.file == file
                        && movement.rank == rank)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 기물이 이동할 수 없는 방향입니다."));
    }

    public Position nextPosition(File file, Rank rank) {
        return new Position(file.value() + this.file, rank.value() + this.rank);
    }
}
