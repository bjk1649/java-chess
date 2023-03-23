package chess.controller;

import chess.domain.ChessBoardFactory;
import chess.domain.ChessGame;
import chess.controller.command.Command;
import chess.controller.command.CommandFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameGuide();
        ChessGame chessGame = new ChessGame(ChessBoardFactory.create());
        while (chessGame.isNotEnd()) {
            executeCommand(chessGame);
        }
    }

    private void executeCommand(final ChessGame chessGame) {
        try {
            Command command = CommandFactory.from(inputView.readCommandAndParameters());
            command.execute(chessGame);
            outputView.printChessBoard(new ChessBoardDto(chessGame.getChessBoard()));
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            outputView.printError(e.getMessage());
            executeCommand(chessGame);
        }
    }

}
