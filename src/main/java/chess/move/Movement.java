package chess.move;

import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import java.util.Arrays;
import java.util.List;

public enum Movement {
    UP(0, 1),
    DOWN(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0),
    UP_RIGHT(1, 1),
    UP_LEFT(-1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1),
    UP_UP_RIGHT(1, 2),
    UP_UP_LEFT(-1, 2),
    DOWN_DOWN_RIGHT(1, -2),
    DOWN_DOWN_LEFT(-1, -2),
    RIGHT_RIGHT_UP(2, 1),
    RIGHT_RIGHT_DOWN(2, -1),
    LEFT_LEFT_UP(-2, 1),
    LEFT_LEFT_DOWN(-2, -1),
    UP_UP(0, 2),
    DOWN_DOWN(0, -2);

    private final int file;
    private final int rank;

    Movement(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Movement findMovement(List<Movement> movableDirections, Position start, Position target) {
        int file = start.fileGap(target);
        int rank = start.rankGap(target);

        Movement movement = Arrays.stream(values())
                .filter(move -> move.file == file && move.rank == rank)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이동 규칙입니다."));
        movement.verifyMovement(movableDirections);
        return movement;
    }

    public static Movement findMovementByDirection(List<Movement> movableDirections, Position start, Position target) {
        int file = Direction.normalizeGapToDirection(start.fileGap(target));
        int rank = Direction.normalizeGapToDirection(start.rankGap(target));
        Movement movement = Arrays.stream(values())
                .filter(move -> move.file == file && move.rank == rank)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이동 규칙입니다."));
        movement.verifyMovement(movableDirections);
        return movement;
    }

    public void verifyMovement(List<Movement> movableDirections) {
        if (!movableDirections.contains(this)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 있는 범위를 벗어났습니다.");
        }
    }

    public Position nextPosition(File file, Rank rank) {
        return new Position(file.value() + this.file, rank.value() + this.rank);
    }
}
