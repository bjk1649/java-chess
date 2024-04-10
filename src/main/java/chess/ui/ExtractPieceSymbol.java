package chess.ui;

import chess.piece.Piece;
import chess.piece.Team;

public class ExtractPieceSymbol {

    public static String convertTypeToSymbol(Piece piece) {
        if (piece.getTeam().equals(Team.WHITE)) {
            return convertToLowercase(piece.getType().extractFirstLetter());
        }
        return piece.getType().extractFirstLetter();
    }

    public static String convertToLowercase(String symbol) {
        return symbol.toLowerCase();
    }
}
