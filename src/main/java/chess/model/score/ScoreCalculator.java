package chess.model.score;

import chess.model.piece.Piece;
import chess.model.piece.PieceInfo;
import chess.model.position.Color;
import chess.model.position.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreCalculator {

  private static final double PAWN_SPECIAL_SCORE = 0.5;

  public static double calculate(Map<Position, Piece> board, Color color) {
    List<Piece> pieces = board.values().stream()
                              .filter(piece -> piece.getColor() == color)
                              .toList();

    double totalScore = pieces.stream()
                              .mapToDouble(piece -> piece.pieceType().getBaseScore())
                              .sum();

    Map<Integer, Long> pawnsByFile = board.entrySet()
                                          .stream()
                                          .filter(entry -> entry.getValue().pieceType() == PieceInfo.PAWN && entry.getValue().getColor() == color)
                                          .collect(Collectors.groupingBy(entry -> entry.getKey().getFile().getValue(), Collectors.counting()));

    for (long pawnCount : pawnsByFile.values()) {
      if (pawnCount > 1) {
        totalScore -= PAWN_SPECIAL_SCORE * pawnCount;
      }
    }

    return totalScore;
  }
}
