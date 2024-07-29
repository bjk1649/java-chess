package chess.dao.chessGame.dto;

import chess.domain.position.Color;

public class FindResponseDto {

  private final Long id;
  private final Color turn;

  public FindResponseDto(Long id, Color turn) {
    this.id = id;
    this.turn = turn;
  }

  public Long getId() {
    return id;
  }

  public Color getTurn() {
    return turn;
  }
}
