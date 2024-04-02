package chess.ui;

import chess.board.Board;
import chess.piece.Piece;
import chess.position.Position;

public class OutputView {
    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printBoard(Board board) {
        for (int rank = Position.LAST_RANK; rank >= Position.FIRST_RANK; rank--) {
            printOneRank(board, rank);
            System.out.println();
        }
    }

    private static void printOneRank(Board board, int rank) {
        for (int file = Position.FIRST_FILE; file <= Position.LAST_FILE; file++) {
            Position position = new Position(file, rank);
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
        return piece.convertTypeToSymbol(piece);
    }
}
