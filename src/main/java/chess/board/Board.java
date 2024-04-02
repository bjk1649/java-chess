package chess.board;

import chess.piece.Type;
import chess.piece.Team;
import chess.position.Position;
import chess.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board; //좌표, 기물종류

    public Board() {
        this.board = new HashMap<>();
    }

    public void initializeBoard() {
        setBlackPiece();
        setWhitePiece();
    }

    public Piece getPiece(Position position) {
        return board.get(position);
    }

    private void setBlackPiece() {
        board.put(new Position('A', 8), new Piece(Piece.Type.ROOK, Piece.Team.BLACK));
        board.put(new Position('B', 8), new Piece(Piece.Type.KNIGHT, Piece.Team.BLACK));
        board.put(new Position('C', 8), new Piece(Piece.Type.BISHOP, Piece.Team.BLACK));
        board.put(new Position('D', 8), new Piece(Piece.Type.QUEEN, Piece.Team.BLACK));
        board.put(new Position('E', 8), new Piece(Piece.Type.KING, Piece.Team.BLACK));
        board.put(new Position('F', 8), new Piece(Piece.Type.BISHOP, Piece.Team.BLACK));
        board.put(new Position('G', 8), new Piece(Piece.Type.KNIGHT, Piece.Team.BLACK));
        board.put(new Position('H', 8), new Piece(Piece.Type.ROOK, Piece.Team.BLACK));
        for(char row = 'A'; row <= 'H'; row++) {
            board.put(new Position(row, 7), new Piece(Piece.Type.PAWN, Piece.Team.BLACK));
        }
    }

    private void setWhitePiece() {
        board.put(new Position('A', 1), new Piece(Piece.Type.ROOK, Piece.Team.WHITE));
        board.put(new Position('B', 1), new Piece(Piece.Type.KNIGHT, Piece.Team.WHITE));
        board.put(new Position('C', 1), new Piece(Piece.Type.BISHOP, Piece.Team.WHITE));
        board.put(new Position('D', 1), new Piece(Piece.Type.KING, Piece.Team.WHITE));
        board.put(new Position('E', 1), new Piece(Piece.Type.QUEEN, Piece.Team.WHITE));
        board.put(new Position('F', 1), new Piece(Piece.Type.BISHOP, Piece.Team.WHITE));
        board.put(new Position('G', 1), new Piece(Piece.Type.KNIGHT, Piece.Team.WHITE));
        board.put(new Position('H', 1), new Piece(Piece.Type.ROOK, Piece.Team.WHITE));
        for(char row = 'A'; row <= 'H'; row++) {
            board.put(new Position(row, 2), new Piece(Piece.Type.PAWN, Piece.Team.WHITE));
        }
    }
}
