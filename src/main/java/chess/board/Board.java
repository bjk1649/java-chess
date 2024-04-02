package chess.board;

import chess.piece.Type;
import chess.piece.Team;
import chess.position.Position;
import chess.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private static final int FIRST_FILE = 1;
    private static final int LAST_FILE = 8;
    private static final int FIRST_RANK = 1;
    private static final int LAST_RANK = 8;

    private final Map<Position, Piece> board; //좌표, 기물종류

    public Board() {
        this.board = new HashMap<>();
    }

    public void initializeBoard() {
        initializeBlackPiece();
        initializeWhitePiece();
    }

    private void initializeBlackPiece() {
        board.put(new Position(1, 8), new Piece(Type.ROOK, Team.BLACK));
        board.put(new Position(2, 8), new Piece(Type.KNIGHT, Team.BLACK));
        board.put(new Position(3, 8), new Piece(Type.BISHOP, Team.BLACK));
        board.put(new Position(4, 8), new Piece(Type.QUEEN, Team.BLACK));
        board.put(new Position(5, 8), new Piece(Type.KING, Team.BLACK));
        board.put(new Position(6, 8), new Piece(Type.BISHOP, Team.BLACK));
        board.put(new Position(7, 8), new Piece(Type.KNIGHT, Team.BLACK));
        board.put(new Position(8, 8), new Piece(Type.ROOK, Team.BLACK));
        for(int file = FIRST_FILE; file <= LAST_FILE; file++) {
            board.put(new Position(file, 7), new Piece(Type.PAWN, Team.BLACK));
        }
    }

    private void initializeWhitePiece() {
        board.put(new Position(1, 1), new Piece(Type.ROOK, Team.WHITE));
        board.put(new Position(2, 1), new Piece(Type.KNIGHT, Team.WHITE));
        board.put(new Position(3, 1), new Piece(Type.BISHOP, Team.WHITE));
        board.put(new Position(4, 1), new Piece(Type.KING, Team.WHITE));
        board.put(new Position(5, 1), new Piece(Type.QUEEN, Team.WHITE));
        board.put(new Position(6, 1), new Piece(Type.BISHOP, Team.WHITE));
        board.put(new Position(7, 1), new Piece(Type.KNIGHT, Team.WHITE));
        board.put(new Position(8, 1), new Piece(Type.ROOK, Team.WHITE));
        for(int file = FIRST_FILE; file <= LAST_FILE; file++) {
            board.put(new Position(file, 2), new Piece(Type.PAWN, Team.WHITE));
        }
    }

    public Piece getPiece(Position position) {
        return board.get(position);
    }
}
