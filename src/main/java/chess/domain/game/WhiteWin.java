package chess.domain.game;

import chess.domain.piece.white.WhitePiece;

import java.util.Optional;

public class WhiteWin extends Finished {
    public WhiteWin(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public Optional<String> getWinnerColorNotation() {
        return Optional.of(WhitePiece.NOTATION);
    }
}