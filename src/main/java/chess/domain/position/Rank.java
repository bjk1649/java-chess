package chess.domain.position;

import chess.domain.ErrorMessage;
import java.util.Arrays;

public enum Rank {
  RANK_1(1),
  RANK_2(2),
  RANK_3(3),
  RANK_4(4),
  RANK_5(5),
  RANK_6(6),
  RANK_7(7),
  RANK_8(8);

  private final int value;

  Rank(int rank) {
    this.value = rank;
  }

  public static Rank from(int value) {
    return Arrays.stream(Rank.values())
                 .filter(rank -> rank.getValue() == value)
                 .findFirst()
                 .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.MISMATCH_RANK_ARGUMENT.getMessage()));
  }

  public int calculateDiff(Rank other) {
    return this.value - other.value;
  }

  public int getValue() {
    return value;
  }
}
