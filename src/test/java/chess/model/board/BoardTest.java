package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.piece.Piece;
import chess.model.piece.PieceInfo;
import chess.model.piece.pieces.King;
import chess.model.piece.pieces.Pawn;
import chess.model.piece.pieces.Rook;
import chess.model.position.Color;
import chess.model.position.InitialPosition;
import chess.model.position.Position;
import chess.model.ErrorMessage;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

  private Board board;
  private Map<Position, Piece> initialBoard;

  @BeforeEach
  void setUp() {
    initialBoard = new HashMap<>();
    board = new Board(initialBoard);
  }

  @Test
  @DisplayName("보드_테스트")
  void 보드_테스트() {
    // given
    Position position = InitialPosition.BLACK_PAWN_F.getPosition();
    Piece pawn = new Pawn(Color.BLACK);

    // when
    initialBoard.put(position, pawn);
    Map<Position, Piece> retrievedMap = board.getMap();

    // then
    assertThat(retrievedMap).containsEntry(position, pawn);
  }

  @Test
  @DisplayName("출발점에_말이_없으면_예외_처리한다")
  void 출발점에_말이_없으면_예외_처리한다() {
    // given
    Position from = new Position(1, 1);
    Position to = new Position(1, 2);

    // when & then
    assertThatThrownBy(() -> board.move(from, to, Color.WHITE))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(ErrorMessage.NO_PIECE_AT_SOURCE.getMessage());
  }

  @Test
  @DisplayName("턴이_아닌_말을_이동시키면_예외_처리한다")
  void 턴이_아닌_말을_이동시키면_예외_처리한다() {
    // given
    Position from = InitialPosition.BLACK_PAWN_A.getPosition();
    Position to = new Position(1, 3);
    initialBoard.put(from, new Pawn(Color.BLACK));

    // when & then
    assertThatThrownBy(() -> board.move(from, to, Color.WHITE))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(ErrorMessage.INVALID_TURN.getMessage());
  }

  @Test
  @DisplayName("같은_색_말이_있는_위치로_이동시키면_예외_처리한다")
  void 같은_색_말이_있는_위치로_이동시키면_예외_처리한다() {
    // given
    Position from = InitialPosition.WHITE_PAWN_A.getPosition();
    Position to = InitialPosition.WHITE_PAWN_B.getPosition();
    initialBoard.put(from, new Pawn(Color.WHITE));
    initialBoard.put(to, new Pawn(Color.WHITE));

    // when & then
    assertThatThrownBy(() -> board.move(from, to, Color.WHITE))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("킹_이동_테스트")
  void 킹_이동_테스트() {
    // given
    Position from = InitialPosition.WHITE_KING.getPosition();
    Position to = new Position(5, 2);
    initialBoard.put(from, new King(Color.WHITE));

    // when
    board.move(from, to, Color.WHITE);
    Map<Position, Piece> retrievedMap = board.getMap();

    // then
    assertThat(retrievedMap).containsEntry(to, new King(Color.WHITE));
    assertThat(retrievedMap).doesNotContainKey(from);
  }

  @Test
  @DisplayName("경로에_장애물이_있다면_예외_처리한다")
  void 경로에_장애물이_있다면_예외_처리한다() {
    // given
    Position from = InitialPosition.WHITE_ROOK_LEFT.getPosition();
    Position to = new Position(1, 4);
    initialBoard.put(from, new Rook(Color.WHITE));
    initialBoard.put(new Position(1, 2), new Pawn(Color.WHITE));

    // when & then
    assertThatThrownBy(() -> board.move(from, to, Color.WHITE))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(ErrorMessage.HAS_OBSTACLE.getMessage());
  }

  @Test
  @DisplayName("폰은_처음_두_칸_이동할_수_있다")
  void 폰은_처음_두_칸_이동할_수_있다() {
    // given
    Position from = InitialPosition.WHITE_PAWN_A.getPosition();
    Position to = new Position(1, 4);
    initialBoard.put(from, new Pawn(Color.WHITE));

    // when
    board.move(from, to, Color.WHITE);
    Map<Position, Piece> retrievedMap = board.getMap();

    // then
    assertThat(retrievedMap).containsEntry(to, new Pawn(Color.WHITE));
    assertThat(retrievedMap).doesNotContainKey(from);
  }

  @Test
  @DisplayName("상대방_말이_있는_곳으로_움직이면_상대방_말을_제거한다")
  void 상대방_말이_있는_곳으로_움직이면_상대방_말을_제거한다() {
    //given
    Position from = new Position(3, 4); // white
    Position to = new Position(2, 5); // black
    initialBoard.put(from, new Pawn(Color.WHITE));
    initialBoard.put(to, new Pawn(Color.BLACK));

    //when
    board.move(from, to, Color.WHITE);
    Map<Position, Piece> retrievedMap = board.getMap();

    //then
    assertThat(retrievedMap).containsEntry(to, new Pawn(Color.WHITE));
    assertThat(retrievedMap).doesNotContainKey(from);
    assertThat(retrievedMap).doesNotContainEntry(to, new Pawn(Color.BLACK));
  }

  @Test
  @DisplayName("킹이_잡히면_킹을_캡쳐된_피스로_반환한다")
  void 킹이_잡히면_킹을_캡쳐된_피스로_반환한다() {
    // given
    Position from = new Position(3, 4); // white
    Position to = new Position(2, 5); // black king
    initialBoard.put(from, new Pawn(Color.WHITE));
    initialBoard.put(to, new King(Color.BLACK));

    // when
    Piece capturedPiece = board.move(from, to, Color.WHITE);

    // then
    assertThat(capturedPiece.pieceType()).isEqualTo(PieceInfo.KING);
    assertThat(capturedPiece.getColor()).isEqualTo(Color.BLACK);
  }
}
