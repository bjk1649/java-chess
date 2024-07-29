package chess.view;

import chess.domain.piece.Piece;
import chess.domain.position.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

  private static final int BOARD_SIZE = 8;
  private static final int INDEX_OFFSET = 1;

  public static void printBoard(final Map<Position, Piece> boardMap) {
    List<List<String>> chessBoard = createEmptyBoard();
    assignSymbols(boardMap, chessBoard);

    chessBoard.stream()
              .map(board -> String.join("", board))
              .forEach(System.out::println);
  }

  public static void printScore(final Double whiteScore, final Double blackScore) {
    System.out.println("White Score: " + whiteScore);
    System.out.println("Black Score: " + blackScore);
  }

  public static void printWinningColor(Color winningColor) {
    System.out.println("Winning Color: " + winningColor);
  }

  public static void printDraw() {
    System.out.println("Draw");
  }

  public static void printErrorMessage(final Exception e) {
    System.out.println(e.getMessage());
    System.out.print(System.lineSeparator());
  }

  public static void printNewGameMessage() {
    System.out.println("새로운 게임을 시작합니다.");
  }

  public static void printContinueMessage() {
    System.out.println("진행 중인 게임을 이어합니다.");
  }

  public static void printNoExistsRunningGameMessage() {
    System.out.println("진행 중인 게임이 없습니다.");
  }

  private static void assignSymbols(final Map<Position, Piece> boardMap,
      final List<List<String>> chessBoard) {
    for (final Map.Entry<Position, Piece> positionPieceEntry : boardMap.entrySet()) {
      final Position position = positionPieceEntry.getKey();
      final Piece piece = positionPieceEntry.getValue();
      final File file = position.getFile();
      final Rank rank = position.getRank();
      final int column = file.getValue();
      final int row = rank.getValue();

      String symbol = Symbol.assignSymbol(piece);
      chessBoard.get(BOARD_SIZE - row).set(column - INDEX_OFFSET, symbol);
    }
  }

  private static List<List<String>> createEmptyBoard() {
    return IntStream.range(0, BOARD_SIZE)
                    .mapToObj(it -> new ArrayList<>(Collections.nCopies(BOARD_SIZE, Symbol.EMPTY.getSymbol())))
                    .collect(Collectors.toList());
  }
}
