package chess.dao.chessGame;

import chess.dao.JdbcConnection;
import chess.dao.JdbcTemplate;
import chess.dao.chessGame.dto.FindResponseDto;
import chess.dao.chessGame.dto.SaveRequestDto;
import chess.domain.position.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JdbcChessGameDao implements ChessGameDao {

  private static final long TEMPORARY_ID = 1;

  private final Connection connection;
  private final JdbcTemplate jdbcTemplate;

  public JdbcChessGameDao() {
    this.connection = JdbcConnection.getConnection();
    this.jdbcTemplate = new JdbcTemplate(connection);
  }

  @Override
  public Long save(final SaveRequestDto saveRequestDto) {
    final String query = "INSERT INTO chess_game VALUES (?, ?)";
    try (final PreparedStatement preparedStatement =
        connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
      preparedStatement.setLong(1, TEMPORARY_ID);
      preparedStatement.setString(2, saveRequestDto.getTurn().name());
      preparedStatement.executeUpdate();
      return retrieveGeneratedKey(preparedStatement);
    } catch (final SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private long retrieveGeneratedKey(final PreparedStatement preparedStatement) throws SQLException {
    final ResultSet resultSet = preparedStatement.getGeneratedKeys();
    resultSet.next();
    return resultSet.getLong(1);
  }

  @Override
  public FindResponseDto findChessGameById(final long id) {
    final String query = "SELECT * FROM chess_game WHERE id = ?";
    final List<String> resultParameters = List.of("id", "turn");
    final List<Object> result = jdbcTemplate.executeQuery(query, resultParameters, id);
    return new FindResponseDto(
        (Long) result.get(0),
        Color.valueOf((String) result.get(1))
    );
  }

  @Override
  public void updateTurn(final long id, final Color turn) {
    final String query = "UPDATE chess_game SET turn = ? WHERE id = ?";
    jdbcTemplate.executeUpdate(query, turn.name(), id);
  }

  @Override
  public void delete() {
    final String query = "DELETE FROM chess_game";
    jdbcTemplate.executeUpdate(query);
  }
}