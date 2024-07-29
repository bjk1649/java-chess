package chess.domain.piece;

import chess.domain.piece.pieces.Bishop;
import chess.domain.piece.pieces.King;
import chess.domain.piece.pieces.Knight;
import chess.domain.piece.pieces.Pawn;
import chess.domain.piece.pieces.Queen;
import chess.domain.piece.pieces.Rook;
import chess.domain.position.Color;

public enum PieceFactory {
  PAWN {
    @Override
    public Piece createPiece(Color color) {
      return new Pawn(color);
    }
  },
  ROOK {
    @Override
    public Piece createPiece(Color color) {
      return new Rook(color);
    }
  },
  KNIGHT {
    @Override
    public Piece createPiece(Color color) {
      return new Knight(color);
    }
  },
  BISHOP {
    @Override
    public Piece createPiece(Color color) {
      return new Bishop(color);
    }
  },
  QUEEN {
    @Override
    public Piece createPiece(Color color) {
      return new Queen(color);
    }
  },
  KING {
    @Override
    public Piece createPiece(Color color) {
      return new King(color);
    }
  };

  public abstract Piece createPiece(Color color);

  public static Piece getPiece(String type, String color) {
    for (PieceFactory pieceFactory : PieceFactory.values()) {
      if (pieceFactory.name().equalsIgnoreCase(type)) {
        return pieceFactory.createPiece(Color.valueOf(color.toUpperCase()));
      }
    }
    return null;
  }
}
