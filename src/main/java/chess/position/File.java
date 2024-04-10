package chess.position;

import java.util.Arrays;

public enum File {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int value;

    File(final int value) {
        this.value = value;
    }

    public static File findByValue(int label) {
        return Arrays.stream(values())
                .filter(file -> file.value == label)
                .findAny()
                .orElse(null);
    }
}