package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.position.Position;
import java.util.List;

public class MoveCommand implements Command {

    private static final int SOURCE_INDEX = 0;
    private static final int DESTINATION_INDEX = 1;

    private final List<String> parameters;

    public MoveCommand(final List<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute(ChessGame chessGame) {
        final Position source = Position.from(parameters.get(SOURCE_INDEX));
        final Position destination = Position.from(parameters.get(DESTINATION_INDEX));
        chessGame.executeMove(source, destination);
    }
}
