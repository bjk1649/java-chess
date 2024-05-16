package chess.ui;

import chess.board.Board;
import chess.piece.Piece;
import chess.position.Position;
import chess.position.Rank;

public class OutputView {
    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(Board board) {
        for (int rank = Position.LAST_RANK; rank >= Position.FIRST_RANK; rank--) {
            printOneRank(board, Rank.findByValue(rank));
            System.out.println();
        }
        System.out.println();
    }

    private static void printOneRank(Board board, Rank rank) {
        for (int file = Position.FIRST_FILE; file <= Position.LAST_FILE; file++) {
            Position position = new Position(file, rank.value());
            Piece piece = board.findPiece(position);
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
        return PieceSymbol.convertTypeToSymbol(piece);
    }
}
