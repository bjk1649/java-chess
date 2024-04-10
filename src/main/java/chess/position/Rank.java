package chess.position;

import java.util.Arrays;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public static Rank findByValue(int label) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == label)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 Rank 입력입니다(1~8)"));
    }

    public int value() {
        return value;
    }
}
