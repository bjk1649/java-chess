package chess.dao;

import chess.board.Board;
import chess.utils.DatabaseUtil;
import chess.piece.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDao {
    public void createNewRoom() {
        String query = "INSERT INTO game (boardState, turn, status) VALUES (?, ?, ?)";
        Board board = new Board();

        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, board.convertBoardStateToString());
            pstmt.setBoolean(2, true);
            pstmt.setString(3, "ready");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int selectRoom(int gameId) {
        String query = "SELECT status FROM game WHERE gameId = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, gameId); // gameId를 쿼리에 설정
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return gameId;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("존재하지 않는 방 번호입니다.");
    }

    public void saveGame(int gameId, String boardState, boolean turn, String status) {
        String query = "INSERT INTO game (gameId, boardState, turn, status) VALUES (?, ?, ?, ?) " + "ON DUPLICATE KEY UPDATE boardState = VALUES(boardState), turn = VALUES(turn), status = VALUES(status)";

        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, gameId);
            pstmt.setString(2, boardState);
            pstmt.setBoolean(3, turn);
            pstmt.setString(4, status);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCurrentBoard(int gameId, Board board) {
        String query = "SELECT boardState, status FROM game WHERE gameId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, gameId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String boardState = rs.getString("boardState");
                    board.loadBoardState(boardState);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Team getCurrentTurn(int gameId, Team turn) {
        String query = "SELECT turn FROM game WHERE gameID = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, gameId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return turn.getTurnByBinary(rs.getInt("turn"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turn;
    }

    public String getCurrentStatus(int gameId) {
        String query = "SELECT status FROM game WHERE gameId = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, gameId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("status");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("존재하지 않는 방입니다.");
    }

    public void changeStatus(int gameId) {
        String query = "UPDATE game SET status = ? WHERE gameId = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "end");
            pstmt.setInt(2, gameId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
