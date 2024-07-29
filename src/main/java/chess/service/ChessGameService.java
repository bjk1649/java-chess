package chess.service;

import chess.dao.chessGame.ChessGameDao;
import chess.dao.chessGame.dto.FindResponseDto;
import chess.dao.chessGame.dto.SaveRequestDto;
import chess.dao.piece.PieceDao;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Color;
import chess.domain.position.Position;
import java.sql.SQLException;
import java.util.Map;

public class ChessGameService {

  private static final long TEMPORAL_ID = 1;

  private final ChessGameDao chessGameDao;
  private final PieceDao pieceDao;

  public ChessGameService(final ChessGameDao chessGameDao, final PieceDao pieceDao) {
    this.chessGameDao = chessGameDao;
    this.pieceDao = pieceDao;
  }

  public ChessGame findChessGame() throws SQLException {
    final FindResponseDto findResponseDto = chessGameDao.findChessGameById(TEMPORAL_ID);
    return new ChessGame(findResponseDto.getId(),
        new Board(pieceDao.putPiecesById(TEMPORAL_ID)), findResponseDto.getTurn());
  }

  public ChessGame initializeChessGame() {
    Color turn = Color.WHITE;
    final Long id = chessGameDao.save(new SaveRequestDto(turn));
    final ChessGame chessGame = new ChessGame(id, BoardFactory.createInitialBoard(), turn);
    final Map<Position, Piece> board = chessGame.getBoard().getMap();

    for (final Map.Entry<Position, Piece> entry : board.entrySet()) {
      pieceDao.insert(id, entry.getKey(), entry.getValue());
    }
    return chessGame;
  }

  public void updatePiece(final ChessGame chessGame, final Position fromPosition, final Position toPosition) {
    final Piece piece = chessGame.getBoard().getPiece(toPosition);

    pieceDao.delete(chessGame.getId(), toPosition);
    pieceDao.insert(chessGame.getId(), toPosition, piece);
    pieceDao.delete(chessGame.getId(), fromPosition);
    chessGameDao.updateTurn(chessGame.getId(), chessGame.getTurn());
  }

  public void deleteChessGame() {
    chessGameDao.delete();
    pieceDao.deleteAll();
  }
}
