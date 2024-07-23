package chess.domain.movement;

import chess.domain.position.Position;
import chess.domain.ErrorMessage;
import java.math.BigInteger;

public class MovementConverter {

  public static Movement convertMovement(Position from, Position to) {
    int fileDiff = to.calculateFileDiff(from);
    int rankDiff = to.calculateRankDiff(from);

    validateSamePosition(fileDiff, rankDiff);

    BigInteger bigIntegerFileDiff = BigInteger.valueOf(fileDiff);
    BigInteger bigIntegerRankDiff = BigInteger.valueOf(rankDiff);

    int gcd = bigIntegerFileDiff.gcd(bigIntegerRankDiff).intValue();

    return Movement.findMovement(fileDiff / gcd, rankDiff / gcd);
  }

  private static void validateSamePosition(int fileDiff, int rankDiff) {
    if (fileDiff == 0 && rankDiff == 0) {
      throw new IllegalArgumentException(ErrorMessage.SAME_POSITION.getMessage());
    }
  }
}
