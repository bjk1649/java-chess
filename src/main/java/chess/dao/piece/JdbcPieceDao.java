package chess.dao.piece;

import chess.dao.JdbcConnection;
import chess.dao.JdbcTemplate;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcPieceDao implements PieceDao {

  private final Connection connection;
  private final JdbcTemplate jdbcTemplate;

  public JdbcPieceDao() {
    this.connection = JdbcConnection.getConnection();
    this.jdbcTemplate = new JdbcTemplate(connection);
  }

  @Override
  public void insert(final long chessGameId, final Position position, final Piece piece) {
    final String query = "INSERT INTO piece (chess_game_id, piece_file, piece_rank, color, type) " +
        "VALUES (?, ?, ?, ?, ?)";
    jdbcTemplate.executeUpdate(query, chessGameId, position.getFile(),
        position.getRank(), piece.getColor().name(), piece.pieceType().name());
  }

  @Override
  public void delete(final long chessGameId, final Position position) {
    final String query = "DELETE FROM piece WHERE piece_file = ? AND piece_rank = ?";
    jdbcTemplate.executeUpdate(query, position.getFile(), position.getRank());
  }

  @Override
  public Map<Position, Piece> putPiecesById(final long chessGameId) {
    Map<Position, Piece> board = new HashMap<>();

    final String query = "SELECT * FROM piece WHERE chess_game_id = ?";
    final List<String> resultParameters = List.of("type", "color", "piece_file", "piece_rank");
    final List<Object> result = jdbcTemplate.executeQuery(query, resultParameters, chessGameId);

    int index = 0;
    while (index < result.size()) {
      final String type = (String) result.get(index++);
      final String color = (String) result.get(index++);
      final int file = (int) result.get(index++);
      final int rank = (int) result.get(index++);
      board.put(new Position(file, rank), PieceFactory.getPiece(type, color));
    }
    return board;
  }

  @Override
  public void deleteAll() {
    final String query = "DELETE FROM piece";
    jdbcTemplate.executeUpdate(query);
  }
}
