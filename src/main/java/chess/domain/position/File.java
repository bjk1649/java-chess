package chess.domain.position;

import chess.domain.ErrorMessage;
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

  File(int file) {
    this.value = file;
  }

  public static File from(int value) {
    return Arrays.stream(File.values())
                 .filter(file -> file.getValue() == value)
                 .findFirst()
                 .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.MISMATCH_FILE_ARGUMENT.getMessage()));
  }

  public int calculateDiff(File other) {
    return this.value - other.value;
  }

  public int getValue() {
    return value;
  }
}
