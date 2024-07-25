package chess;

import chess.controller.ChessController;
import chess.dao.chessGame.JdbcChessGameDao;
import chess.dao.piece.JdbcPieceDao;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;
import java.sql.SQLException;

public class ChessApplication {

  public static void main(String[] args) throws SQLException {

    final JdbcChessGameDao chessGameDao = new JdbcChessGameDao();
    final JdbcPieceDao PieceDao = new JdbcPieceDao();
    final ChessGameService chessGameService = new ChessGameService(chessGameDao, PieceDao);
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    final ChessController chessController = new ChessController(chessGameService, inputView, outputView);

    chessController.run();
  }
}
