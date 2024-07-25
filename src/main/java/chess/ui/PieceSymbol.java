package chess.ui;

import chess.piece.*;

import java.util.HashMap;
import java.util.Map;

public class PieceSymbol {
    private static final Map<Class<?>, String> typeToSymbolMap = new HashMap<>();
    private static final Map<Character, Piece> pieceMap = new HashMap<>();

    static {
        typeToSymbolMap.put(Pawn.class, "P");
        typeToSymbolMap.put(Rook.class, "R");
        typeToSymbolMap.put(Bishop.class, "B");
        typeToSymbolMap.put(Knight.class, "N");
        typeToSymbolMap.put(Queen.class, "Q");
        typeToSymbolMap.put(King.class, "K");
        typeToSymbolMap.put(Empty.class, ".");

        pieceMap.put('p', new Pawn(Team.WHITE));
        pieceMap.put('r', new Rook(Team.WHITE));
        pieceMap.put('b', new Bishop(Team.WHITE));
        pieceMap.put('n', new Knight(Team.WHITE));
        pieceMap.put('q', new Queen(Team.WHITE));
        pieceMap.put('k', new King(Team.WHITE));
        pieceMap.put('P', new Pawn(Team.BLACK));
        pieceMap.put('R', new Rook(Team.BLACK));
        pieceMap.put('B', new Bishop(Team.BLACK));
        pieceMap.put('N', new Knight(Team.BLACK));
        pieceMap.put('Q', new Queen(Team.BLACK));
        pieceMap.put('K', new King(Team.BLACK));
        pieceMap.put('.', new Empty(Team.NONE));
    }

    private PieceSymbol() {
    }

    public static String convertTypeToSymbol(Piece piece) {
        String symbol = typeToSymbolMap.get(piece.getClass());

        if (piece.isSameTeam(Team.WHITE)) {
            return symbol.toLowerCase();
        }
        return symbol;
    }

    public static Piece convertSymbolToPiece(char symbol) {
        return pieceMap.get(symbol);
    }
}
