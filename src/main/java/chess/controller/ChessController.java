package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.InitialCommand;
import chess.domain.game.ChessGame;
import chess.domain.game.State;
import chess.domain.position.Color;
import chess.domain.position.Position;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class ChessController {

  private static final int COMMAND_INDEX = 0;
  private static final int SOURCE_POSITION_INDEX = 1;
  private static final int TARGET_POSITION_INDEX = 2;

  private final Map<Command, BiConsumer<ChessGame, List<String>>> commands =
      new EnumMap<>(Command.class);
  private final ChessGameService chessGameService;

  private final InputView inputView;
  private final OutputView outputView;

  public ChessController(ChessGameService chessGameService, InputView inputView,
      OutputView outputView) {
    putCommands();
    this.chessGameService = chessGameService;
    this.inputView = inputView;
    this.outputView = outputView;
  }

  private void putCommands() {
    commands.put(Command.START, (chessGame, ignored) -> start(chessGame));
    commands.put(Command.END, (chessGame, ignored) -> end(chessGame));
    commands.put(Command.STATUS, (chessGame, ignored) -> status(chessGame));
    commands.put(Command.MOVE, this::movePiece);
  }

  private void start(ChessGame chessGame) {
    chessGame.start();
    outputView.printBoard(chessGame.getBoard().getMap());
  }

  private void end(ChessGame chessGame) {
    chessGame.end();
  }

  private void status(ChessGame chessGame) {
    final Double whiteScore = chessGame.calculateScore(Color.WHITE);
    final Double blackScore = chessGame.calculateScore(Color.BLACK);
    printScoreAndWinningColor(whiteScore, blackScore);
  }

  private void movePiece(ChessGame chessGame, List<String> commandParts) {
    final Position source = parsePosition(commandParts.get(SOURCE_POSITION_INDEX));
    final Position target = parsePosition(commandParts.get(TARGET_POSITION_INDEX));
    chessGame.movePiece(source, target);
    chessGameService.updatePiece(chessGame, source, target);
    outputView.printBoard(chessGame.getBoard().getMap());
  }

  public void run() throws SQLException {
    ChessGame chessGame = initializeChessGame(receiveInitialCommand());

    while (isRunnable(chessGame)) {
      executeCommand(chessGame);
    }
    processIfKingCaptured(chessGame);
  }

  private boolean isRunnable(ChessGame chessGame) {
    return chessGame.getState().equals(State.RUNNING)
        || chessGame.getState().equals(State.WAITING);
  }

  private void processIfKingCaptured(final ChessGame chessGame) {
    if (chessGame.getState().equals(State.CHECKMATE)) {
      final Color winner = chessGame.getTurn();
      outputView.printWinningColor(winner);
    }
  }

  private void executeCommand(ChessGame chessGame) {
    try {
      outputView.printCommandMessage();
      final List<String> commandParts = List.of(inputView.receiveCommand().split(" "));
      final Command command = Command.findCommand(commandParts.get(COMMAND_INDEX));
      commands.get(command).accept(chessGame, commandParts);
    } catch (IllegalArgumentException e) {
      outputView.printErrorMessage(e);
      executeCommand(chessGame);
    }
  }

  private InitialCommand receiveInitialCommand() {
    try {
      outputView.printInitialMessage();
      final String command = inputView.receiveCommand();
      return InitialCommand.findCommand(command);
    } catch (IllegalArgumentException e) {
      outputView.printErrorMessage(e);
      return receiveInitialCommand();
    }
  }

  private ChessGame initializeChessGame(InitialCommand command) throws SQLException {
    ChessGame chessGame = findChessGameIfContinue(command);
    // new 입력하면 메서드가 작동하지 않아 null 로 반환된다
    if (chessGame == null) {
      outputView.printNewGameMessage();
      chessGameService.deleteChessGame();
      chessGame = chessGameService.initializeChessGame();
    }
    return chessGame;
  }

  private ChessGame findChessGameIfContinue(final InitialCommand command) throws SQLException {
    ChessGame chessGame = null;
    if (command.equals(InitialCommand.CONTINUE)) {
      chessGame = chessGameService.findChessGame();
      printContinueMessage(chessGame);
    }
    return chessGame;
  }

  private void printContinueMessage(final ChessGame chessGame) {
    if (chessGame == null) {
      outputView.printNoExistsRunningGameMessage();
    }
    outputView.printContinueMessage();
  }

  private void printScoreAndWinningColor(Double whiteScore, Double blackScore) {
    outputView.printScore(whiteScore, blackScore);
    if (whiteScore > blackScore) {
      outputView.printWinningColor(Color.WHITE);
    }
    if (whiteScore < blackScore) {
      outputView.printWinningColor(Color.BLACK);
    }
    if (whiteScore.equals(blackScore)) {
      outputView.printDraw();
    }
  }

  private static Position parsePosition(String position) {
    int file = position.charAt(0) - 'a' + 1;
    int rank = position.charAt(1) - '0';
    return new Position(file, rank); // 생성자에 1~8 유효성 검사가 있다
  }
}
