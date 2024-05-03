package chess.board;

import chess.piece.Type;
import chess.piece.Team;
import chess.position.Position;
import chess.piece.Piece;
import chess.position.StartPiecePosition;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board; //좌표, 기물종류

    public Board() {
        this.board = new HashMap<>();
        initializeBoard();
    }

    private void initializeBoard() {
        initializeBlackPiece();
        initializeWhitePiece();
    }

    private void initializeBlackPiece() {
        board.put(StartPiecePosition.BLACK_ROOK_LEFT.getPosition(), new Piece(Type.ROOK, Team.BLACK));
        board.put(StartPiecePosition.BLACK_KNIGHT_LEFT.getPosition(), new Piece(Type.KNIGHT, Team.BLACK));
        board.put(StartPiecePosition.BLACK_BISHOP_LEFT.getPosition(), new Piece(Type.BISHOP, Team.BLACK));
        board.put(StartPiecePosition.BLACK_KING.getPosition(), new Piece(Type.KING, Team.BLACK));
        board.put(StartPiecePosition.BLACK_QUEEN.getPosition(), new Piece(Type.QUEEN, Team.BLACK));
        board.put(StartPiecePosition.BLACK_BISHOP_RIGHT.getPosition(), new Piece(Type.BISHOP, Team.BLACK));
        board.put(StartPiecePosition.BLACK_KNIGHT_RIGHT.getPosition(), new Piece(Type.KNIGHT, Team.BLACK));
        board.put(StartPiecePosition.BLACK_ROOK_RIGHT.getPosition(), new Piece(Type.ROOK, Team.BLACK));
        for (int file = Position.FIRST_FILE; file <= Position.LAST_FILE; file++) {
            board.put(new Position(file, Position.BLACK_PAWN_RANK), new Piece(Type.PAWN, Team.BLACK));
        }
    }

    private void initializeWhitePiece() {
        board.put(StartPiecePosition.WHITE_ROOK_LEFT.getPosition(), new Piece(Type.ROOK, Team.WHITE));
        board.put(StartPiecePosition.WHITE_KNIGHT_LEFT.getPosition(), new Piece(Type.KNIGHT, Team.WHITE));
        board.put(StartPiecePosition.WHITE_BISHOP_LEFT.getPosition(), new Piece(Type.BISHOP, Team.WHITE));
        board.put(StartPiecePosition.WHITE_KING.getPosition(), new Piece(Type.KING, Team.WHITE));
        board.put(StartPiecePosition.WHITE_QUEEN.getPosition(), new Piece(Type.QUEEN, Team.WHITE));
        board.put(StartPiecePosition.WHITE_BISHOP_RIGHT.getPosition(), new Piece(Type.BISHOP, Team.WHITE));
        board.put(StartPiecePosition.WHITE_KNIGHT_RIGHT.getPosition(), new Piece(Type.KNIGHT, Team.WHITE));
        board.put(StartPiecePosition.WHITE_ROOK_RIGHT.getPosition(), new Piece(Type.ROOK, Team.WHITE));
        for (int file = Position.FIRST_FILE; file <= Position.LAST_FILE; file++) {
            board.put(new Position(file, Position.WHITE_PAWN_RANK), new Piece(Type.PAWN, Team.WHITE));
        }
    }

    public Piece findPiece(Position position) {
        return board.get(position);
    }
}
