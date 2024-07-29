package chess.dao.chessGame;

import chess.dao.chessGame.dto.FindResponseDto;
import chess.dao.chessGame.dto.SaveRequestDto;
import chess.domain.position.Color;

public interface ChessGameDao {

  Long save(final SaveRequestDto saveRequestDto);
  FindResponseDto findChessGameById(final long id);
  void updateTurn(final long id, final Color turn);
  void delete();
}
