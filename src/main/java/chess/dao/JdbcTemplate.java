package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

  private static final int FIRST_INDEX = 1;
  private final Connection connection;

  public JdbcTemplate(final Connection connection) {
    this.connection = connection;
  }

  public void executeUpdate(final String query, final Object... parameters) {
    try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      for (int index = FIRST_INDEX; index <= parameters.length; index++) {
        preparedStatement.setObject(index, parameters[index - FIRST_INDEX]);
      }
      preparedStatement.executeUpdate();
    } catch (final SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Object> executeQuery(final String query, final List<String> resultParameters,
      final Object... sqlParameters) {
    try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      final List<Object> result = new ArrayList<>();
      for (int index = FIRST_INDEX; index <= sqlParameters.length; index++) {
        preparedStatement.setObject(index, sqlParameters[index - FIRST_INDEX]);
      }

      final ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        for (String parameter : resultParameters) {
          result.add(resultSet.getObject(parameter));
        }
      }
      return result;
    } catch (final SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
