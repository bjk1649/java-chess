package chess.ui;

import chess.board.Board;
import chess.piece.Piece;
import chess.piece.Team;
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

    public static void printEndGameMessage(Team team) {
        if (team.isSameTeam(Team.WHITE)) {
            System.out.println("WHITE 팀의 승리입니다.");
        }
        if (team.isSameTeam(Team.BLACK)) {
            System.out.println("BLACK 팀의 승리입니다.");
        }
    }

    public static void printEndGameByStatusMessage(Board board) {
        double whiteTeamScore = board.calculateScore(Team.WHITE);
        double blackTeamScore = board.calculateScore(Team.BLACK);

        System.out.println("흰색 기물 점수 : " + whiteTeamScore);
        System.out.println("검은색 기물 점수 : " + blackTeamScore);

        if (whiteTeamScore > blackTeamScore) {
            System.out.println("WHITE 팀의 승리입니다.");
        }
        if (whiteTeamScore < blackTeamScore) {
            System.out.println("BLACK 팀의 승리입니다.");
        }
        if (whiteTeamScore == blackTeamScore) {
            System.out.println("무승부입니다.");
        }
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
        if (piece.isEmpty()) {
            return ".";
        }
        return PieceSymbol.convertTypeToSymbol(piece);
    }
}
