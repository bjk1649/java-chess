package chess;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<Position, Piece> board; //좌표, 기물종류

    public Board() {
        this.board = new HashMap<>();
        initializeBoard();
    }

    private void initializeBoard() {
        setBlackPiece();
        setWhitePiece();
    }

    private void setBlackPiece() {
        board.put(new Position('A',8), new Piece(Piece.Type.ROOK, Piece.Team.BLACK));
        board.put(new Position('B',8), new Piece(Piece.Type.KNIGHT, Piece.Team.BLACK));
        board.put(new Position('C',8), new Piece(Piece.Type.BISHOP, Piece.Team.BLACK));
        board.put(new Position('D',8), new Piece(Piece.Type.QUEEN, Piece.Team.BLACK));
        board.put(new Position('E',8), new Piece(Piece.Type.KING, Piece.Team.BLACK));
        board.put(new Position('F',8), new Piece(Piece.Type.BISHOP, Piece.Team.BLACK));
        board.put(new Position('G',8), new Piece(Piece.Type.KNIGHT, Piece.Team.BLACK));
        board.put(new Position('H',8), new Piece(Piece.Type.ROOK, Piece.Team.BLACK));
        board.put(new Position('A',7), new Piece(Piece.Type.PAWN, Piece.Team.BLACK));
        board.put(new Position('B',7), new Piece(Piece.Type.PAWN, Piece.Team.BLACK));
        board.put(new Position('C',7), new Piece(Piece.Type.PAWN, Piece.Team.BLACK));
        board.put(new Position('D',7), new Piece(Piece.Type.PAWN, Piece.Team.BLACK));
        board.put(new Position('E',7), new Piece(Piece.Type.PAWN, Piece.Team.BLACK));
        board.put(new Position('F',7), new Piece(Piece.Type.PAWN, Piece.Team.BLACK));
        board.put(new Position('G',7), new Piece(Piece.Type.PAWN, Piece.Team.BLACK));
        board.put(new Position('H',7), new Piece(Piece.Type.PAWN, Piece.Team.BLACK));
    }

    private void setWhitePiece() {
        board.put(new Position('A',1), new Piece(Piece.Type.ROOK, Piece.Team.WHITE));
        board.put(new Position('B',1), new Piece(Piece.Type.KNIGHT, Piece.Team.WHITE));
        board.put(new Position('C',1), new Piece(Piece.Type.BISHOP, Piece.Team.WHITE));
        board.put(new Position('D',1), new Piece(Piece.Type.KING, Piece.Team.WHITE));
        board.put(new Position('E',1), new Piece(Piece.Type.QUEEN, Piece.Team.WHITE));
        board.put(new Position('F',1), new Piece(Piece.Type.BISHOP, Piece.Team.WHITE));
        board.put(new Position('G',1), new Piece(Piece.Type.KNIGHT, Piece.Team.WHITE));
        board.put(new Position('H',1), new Piece(Piece.Type.ROOK, Piece.Team.WHITE));
        board.put(new Position('A',2), new Piece(Piece.Type.PAWN, Piece.Team.WHITE));
        board.put(new Position('A',2), new Piece(Piece.Type.PAWN, Piece.Team.WHITE));
        board.put(new Position('A',2), new Piece(Piece.Type.PAWN, Piece.Team.WHITE));
        board.put(new Position('A',2), new Piece(Piece.Type.PAWN, Piece.Team.WHITE));
        board.put(new Position('A',2), new Piece(Piece.Type.PAWN, Piece.Team.WHITE));
        board.put(new Position('A',2), new Piece(Piece.Type.PAWN, Piece.Team.WHITE));
        board.put(new Position('A',2), new Piece(Piece.Type.PAWN, Piece.Team.WHITE));
        board.put(new Position('A',2), new Piece(Piece.Type.PAWN, Piece.Team.WHITE));
    }
}
