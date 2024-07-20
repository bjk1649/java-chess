package chess.view;

import chess.model.piece.Piece;
import chess.model.position.Color;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

  private static final int BOARD_SIZE = 8;
  private static final int INDEX_OFFSET = 1;

  public void printStartMessage() {
    System.out.println("> 체스 게임을 시작합니다.");
    System.out.println("> 게임 시작 : start");
    System.out.println("> 게임 종료 : end");
    System.out.println("> 게임 이동 : move source 위치 target 위치 - 예. move b2 b3");
  }

  public void printBoard(final Map<Position, Piece> boardMap) {
    List<List<String>> chessBoard = createEmptyBoard();
    assignSymbols(boardMap, chessBoard);

    chessBoard.stream()
              .map(board -> String.join("", board))
              .forEach(System.out::println);
    System.out.print(System.lineSeparator());
  }

  public void printCurrentTurnScore(Color currentTurn, double score) {
    System.out.println("Current turn: " + currentTurn);
    System.out.println("Score: " + score);
  }

  private void assignSymbols(final Map<Position, Piece> boardMap, final List<List<String>> chessBoard) {
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

  private List<List<String>> createEmptyBoard() {
    return IntStream.range(0, BOARD_SIZE)
                    .mapToObj(it -> new ArrayList<>(Collections.nCopies(BOARD_SIZE, Symbol.EMPTY.getSymbol())))
                    .collect(Collectors.toList());
  }

  public void printCurrentScore(Color currentTurn, double score) {
    System.out.println("Color: " + currentTurn + ", Score: " + score);
  }

  public void printWinningColor(Color currentTurn) {
    System.out.println("Winning Color: " + currentTurn);
  }

  public void printDraw() {
    System.out.println("Draw");
  }
}
