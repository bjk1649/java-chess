package chess.model.score;

import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.piece.pieces.*;
import chess.model.position.Color;
import chess.model.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreCalculatorTest {

  private Board board;

  @BeforeEach
  void setUp() {
    Map<Position, Piece> boardMap = new HashMap<>();
    // Black pieces
    boardMap.put(new Position(2, 8), new King(Color.BLACK));
    boardMap.put(new Position(3, 8), new Rook(Color.BLACK));
    boardMap.put(new Position(1, 7), new Pawn(Color.BLACK));
    boardMap.put(new Position(3, 7), new Pawn(Color.BLACK));
    boardMap.put(new Position(4, 7), new Bishop(Color.BLACK));
    boardMap.put(new Position(2, 6), new Pawn(Color.BLACK));
    boardMap.put(new Position(5, 6), new Queen(Color.BLACK));

    // White pieces
    boardMap.put(new Position(6, 4), new Knight(Color.WHITE));
    boardMap.put(new Position(7, 4), new Queen(Color.WHITE));
    boardMap.put(new Position(6, 3), new Pawn(Color.WHITE));
    boardMap.put(new Position(8, 3), new Pawn(Color.WHITE));
    boardMap.put(new Position(6, 2), new Pawn(Color.WHITE));
    boardMap.put(new Position(7, 2), new Pawn(Color.WHITE));
    boardMap.put(new Position(5, 1), new Rook(Color.WHITE));
    boardMap.put(new Position(6, 1), new King(Color.WHITE));

    board = new Board(boardMap);
  }

  @Test
  @DisplayName("검은색_말_점수_계산")
  void 검은색_말_점수_계산() {
    // given
    Map<Position, Piece> boardMap = board.getMap();

    // when
    double blackScore = ScoreCalculator.calculate(boardMap, Color.BLACK);

    // then
    assertThat(blackScore).isEqualTo(20.0); // 검은색 말의 예상 점수
  }

  @Test
  @DisplayName("흰색_말_점수_계산")
  void 흰색_말_점수_계산() {
    // given
    Map<Position, Piece> boardMap = board.getMap();

    // when
    double whiteScore = ScoreCalculator.calculate(boardMap, Color.WHITE);

    // then
    assertThat(whiteScore).isEqualTo(19.5); // 흰색 말의 예상 점수
  }

  @Test
  @DisplayName("같은_파일에_있는_폰의_점수_계산")
  void 같은_파일에_있는_폰의_점수_계산() {
    // given
    Map<Position, Piece> boardMap = new HashMap<>();
    boardMap.put(new Position(1, 2), new Pawn(Color.WHITE));
    boardMap.put(new Position(1, 3), new Pawn(Color.WHITE));
    board = new Board(boardMap);

    // when
    double whiteScore = ScoreCalculator.calculate(boardMap, Color.WHITE);

    // then
    assertThat(whiteScore).isEqualTo(1.0); // 같은 파일에 있는 흰색 폰의 점수 계산 (0.5 * 2)
  }

  @Test
  @DisplayName("혼합된_말의_점수_계산")
  void 혼합된_말의_점수_계산() {
    // given
    Map<Position, Piece> boardMap = board.getMap();

    // when
    double blackScore = ScoreCalculator.calculate(boardMap, Color.BLACK);
    double whiteScore = ScoreCalculator.calculate(boardMap, Color.WHITE);

    // then
    assertThat(blackScore).isEqualTo(20.0);
    assertThat(whiteScore).isEqualTo(19.5);
  }

  @Test
  @DisplayName("한쪽_말만_있는_경우_점수_계산")
  void 한쪽_말만_있는_경우_점수_계산() {
    // given
    Map<Position, Piece> boardMap = new HashMap<>();
    boardMap.put(new Position(1, 2), new Rook(Color.WHITE));
    boardMap.put(new Position(1, 7), new Queen(Color.BLACK));
    board = new Board(boardMap);

    // when
    double whiteScore = ScoreCalculator.calculate(boardMap, Color.WHITE);
    double blackScore = ScoreCalculator.calculate(boardMap, Color.BLACK);

    // then
    assertThat(whiteScore).isEqualTo(5.0); // 흰색 룩의 점수
    assertThat(blackScore).isEqualTo(9.0); // 검은색 퀸의 점수
  }
}
