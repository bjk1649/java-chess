package chess.board;

import chess.piece.*;
import chess.position.File;
import chess.position.Position;
import chess.position.StartPiecePosition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        initializeEmptyPiece();
    }

    private void initializeBlackPiece() {
        board.put(StartPiecePosition.BLACK_ROOK_LEFT.getPosition(), new Rook(Team.BLACK));
        board.put(StartPiecePosition.BLACK_KNIGHT_LEFT.getPosition(), new Knight(Team.BLACK));
        board.put(StartPiecePosition.BLACK_BISHOP_LEFT.getPosition(), new Bishop(Team.BLACK));
        board.put(StartPiecePosition.BLACK_QUEEN.getPosition(), new Queen(Team.BLACK));
        board.put(StartPiecePosition.BLACK_KING.getPosition(), new King(Team.BLACK));
        board.put(StartPiecePosition.BLACK_BISHOP_RIGHT.getPosition(), new Bishop(Team.BLACK));
        board.put(StartPiecePosition.BLACK_KNIGHT_RIGHT.getPosition(), new Knight(Team.BLACK));
        board.put(StartPiecePosition.BLACK_ROOK_RIGHT.getPosition(), new Rook(Team.BLACK));
        for (int file = Position.FIRST_FILE; file <= Position.LAST_FILE; file++) {
            board.put(new Position(file, Position.BLACK_PAWN_INITIAL_RANK), new Pawn(Team.BLACK));
        }
    }

    private void initializeWhitePiece() {
        board.put(StartPiecePosition.WHITE_ROOK_LEFT.getPosition(), new Rook(Team.WHITE));
        board.put(StartPiecePosition.WHITE_KNIGHT_LEFT.getPosition(), new Knight(Team.WHITE));
        board.put(StartPiecePosition.WHITE_BISHOP_LEFT.getPosition(), new Bishop(Team.WHITE));
        board.put(StartPiecePosition.WHITE_QUEEN.getPosition(), new Queen(Team.WHITE));
        board.put(StartPiecePosition.WHITE_KING.getPosition(), new King(Team.WHITE));
        board.put(StartPiecePosition.WHITE_BISHOP_RIGHT.getPosition(), new Bishop(Team.WHITE));
        board.put(StartPiecePosition.WHITE_KNIGHT_RIGHT.getPosition(), new Knight(Team.WHITE));
        board.put(StartPiecePosition.WHITE_ROOK_RIGHT.getPosition(), new Rook(Team.WHITE));
        for (int file = Position.FIRST_FILE; file <= Position.LAST_FILE; file++) {
            board.put(new Position(file, Position.WHITE_PAWN_INITIAL_RANK), new Pawn(Team.WHITE));
        }
    }

    private void initializeEmptyPiece() {
        for (int rank = Position.START_EMPTY_PIECE_RANK; rank <= Position.END_EMPTY_PIECE_RANK; rank++) {
            for(int file = Position.FIRST_FILE; file <= Position.LAST_FILE; file++) {
                board.put(new Position(file, rank), new Empty(Team.NONE));
            }
        }
    }

    public void movePiece(Position start, Position target) {
        this.board.put(target, findPiece(start));
        this.board.put(start, new Empty(Team.NONE));
    }

    public void verifyPath(Position start, Position target) {
        Piece selectedPiece = findPiece(start);
        Piece targetPiece = findPiece(target);
        selectedPiece.checkTargetPosition(targetPiece, start, target);

        List<Position> path = selectedPiece.findPath(start, target);

        for (Position position : path) {
            checkPositionIsEmpty(position, target);
        }
    }

    public void checkPositionIsEmpty(Position position, Position target) {
        if (!this.findPiece(position).isEmpty() && !position.equals(target)) {
            throw new IllegalArgumentException("다른 기물이 존재해서 지나갈 수 없습니다.");
        }
    }

    public double calculateScore(Team team) {
        return Score.calculateScore(this, team);
    }

    public Piece findPiece(Position position) {
        return board.get(position);
    }
}
