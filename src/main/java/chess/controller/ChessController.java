package chess.controller;

import chess.model.command.CommandFactory;
import chess.model.command.CommandLauncher;
import chess.model.ErrorMessage;
import chess.model.board.Board;
import chess.model.board.InitialBoard;
import chess.model.piece.Piece;
import chess.model.piece.PieceInfo;
import chess.model.position.Color;
import chess.model.position.Position;
import chess.model.score.ScoreCalculator;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

  private final InputView inputView;
  private final OutputView outputView;
  private final CommandFactory commandFactory;
  private final Board board;
  private Color currentTurn;
  private boolean isRunning;

  public ChessController(InputView inputView, OutputView outputView, CommandFactory commandFactory, InitialBoard initialBoard) {
    this.inputView = inputView;
    this.outputView = outputView;
    this.commandFactory = commandFactory;
    this.board = initialBoard.createInitialBoard();
    this.currentTurn = Color.WHITE;
    this.isRunning = true;
  }

  public void runChess() {
    outputView.printStartMessage();
    CommandLauncher receivedCommand = null;

    while (receivedCommand == null) {
      try {
        String initialCommandInput = inputView.receiveCommand();
        receivedCommand = commandFactory.createCommand(initialCommandInput);
        if (receivedCommand.validateInitialCommandType()) {
          break;
        }
        System.out.println(ErrorMessage.INVALID_INITIAL_COMMAND.getMessage());
        receivedCommand = null;
      } catch (IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
      }
    }

    receivedCommand.execute(this);

    while (isRunning) {
      try {
        String commandInput = inputView.receiveCommand();
        receivedCommand = commandFactory.createCommand(commandInput);
        if (receivedCommand.validateStatusCommandType()) {
          receivedCommand.execute(this);
          continue; // 명령이 status 일 경우 턴을 바꾸지 않음
        }
        receivedCommand.execute(this);
        currentTurn = currentTurn.changeTurn();
      } catch (IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
      }
    }
  }

  public void startGame() {
    outputView.printBoard(board.getMap());
  }

  public void endGame() {
    isRunning = false;
  }

  public void movePiece(Position source, Position target) {
    Piece capturedPiece = board.move(source, target, currentTurn);
    checkAndHandleKingCapture(capturedPiece, currentTurn);
    outputView.printBoard(board.getMap());
  }

  public void calculateAndPrintCurrentTurnScore() {
    double score = ScoreCalculator.calculate(board.getMap(), currentTurn);
    outputView.printCurrentTurnScore(currentTurn, score);
  }

  public void printScoreAndWinningColor() {
    double currentTurnScore = ScoreCalculator.calculate(board.getMap(), currentTurn);
    double opponentScore = ScoreCalculator.calculate(board.getMap(), currentTurn.changeTurn());
    outputView.printCurrentScore(currentTurn, currentTurnScore);
    outputView.printCurrentScore(currentTurn.changeTurn(), opponentScore);
    if (currentTurnScore > opponentScore) {
      outputView.printWinningColor(currentTurn);
    }
    if (opponentScore > currentTurnScore) {
      outputView.printWinningColor(currentTurn.changeTurn());
    }
    if (currentTurnScore == opponentScore) {
      outputView.printDraw();
    }
  }

  private void checkAndHandleKingCapture(Piece capturedPiece, Color currentTurn) {
    if (capturedPiece != null && capturedPiece.pieceType() == PieceInfo.KING) {
      endGame();
      double currentTurnScore = ScoreCalculator.calculate(board.getMap(), currentTurn);
      outputView.printCurrentScore(currentTurn, currentTurnScore);
      outputView.printWinningColor(currentTurn);
    }
  }
}
