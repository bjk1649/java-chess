package chess.database;

import chess.utils.DatabaseUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseUtilTest {
    @DisplayName("데이터베이스에 성공적으로 연결되는지 테스트")
    @Test
    void testDatabaseConnection() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            assertNotNull(connection, "연결이 null일 수 없습니다");
            assertFalse(connection.isClosed(), "연결이 닫혀 있습니다");
        } catch (SQLException e) {
            fail("연결 실패 : " + e.getMessage());
        }
    }
}
