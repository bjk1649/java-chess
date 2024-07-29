package chess.dao.chessGame.dto;

import chess.domain.position.Color;

public class SaveRequestDto {

  private final Color turn;

  public SaveRequestDto(Color turn) {
    this.turn = turn;
  }

  public Color getTurn() {
    return turn;
  }
}
