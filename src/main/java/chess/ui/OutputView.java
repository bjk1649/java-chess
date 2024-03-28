package chess.ui;

import chess.Board;
import chess.Piece;
import chess.Position;

public class OutputView {

    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printBoard(Board board) {
        for (int row = 8; row >= 1; row--) {
            printOneRow(board, row);
            System.out.println();
        }
    }

    private static void printOneRow(Board board, int row) {
        for (char col = 'A'; col <= 'H'; col++) {
            Position position = new Position(col, row);
            Piece piece = board.getPiece(position);
            printOnePiece(piece);
        }
    }

    private static void printOnePiece(Piece piece) {
        System.out.print(getPieceSymbol(piece));
    }

    private static String getPieceSymbol(Piece piece) {
        if (piece == null) {
            return ".";
        }
        return convertSymbolFormat(piece);
    }

    private static String convertSymbolFormat(Piece piece) {
        if (piece.getTeam().equals(Piece.Team.WHITE)) {
            return piece.getType().getSymbol().toLowerCase();
        }
        return piece.getType().getSymbol();
    }
}
