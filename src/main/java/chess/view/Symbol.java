package chess.view;

import chess.domain.ErrorMessage;
import chess.domain.piece.Piece;
import chess.domain.piece.pieces.*;
import chess.domain.position.Color;

public enum Symbol {
  KING("k", King.class),
  QUEEN("q", Queen.class),
  ROOK("r", Rook.class),
  BISHOP("b", Bishop.class),
  KNIGHT("n", Knight.class),
  PAWN("p", Pawn.class),

  EMPTY(".", null);

  private final String symbol;
  private final Class<? extends Piece> pieceClass;

  Symbol(String symbol, Class<? extends Piece> pieceClass) {
    this.symbol = symbol;
    this.pieceClass = pieceClass;
  }

  public String getSymbol() {
    return symbol;
  }

  public static String assignSymbol(Piece piece) {
    for (Symbol symbol : Symbol.values()) {
      if (symbol.pieceClass == piece.getClass() && piece.getColor().equals(Color.WHITE)) {
        return symbol.getSymbol().toLowerCase();
      }
      if (symbol.pieceClass == piece.getClass() && !piece.getColor().equals(Color.WHITE)) {
        return symbol.getSymbol().toUpperCase();
      }
    }
    throw new IllegalArgumentException(ErrorMessage.MISMATCH_SYMBOL.getMessage());
  }
}
