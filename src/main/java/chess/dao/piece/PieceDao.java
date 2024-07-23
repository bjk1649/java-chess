package chess.dao.piece;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.sql.SQLException;
import java.util.Map;

public interface PieceDao {

  void insert(long chessGameId, Position position, Piece piece);
  void delete(long chessGameId, Position position);
  Map<Position, Piece> putPiecesById(long chessGameId) throws SQLException;
  void deleteAll();
}
