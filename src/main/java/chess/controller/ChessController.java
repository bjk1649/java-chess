package chess.controller;

import chess.model.Command.CommandFactory;
import chess.model.Command.CommandLauncher;
import chess.model.Command.EndCommand;
import chess.model.Command.StartCommand;
import chess.model.ErrorMessage;
import chess.model.board.Board;
import chess.model.board.InitialBoard;
import chess.model.position.Color;
import chess.model.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

  private final InputView inputView;
  private final OutputView outputView;
  private final Board board;
  private Color currentTurn;
  private boolean isRunning;

  public ChessController(InputView inputView, OutputView outputView, InitialBoard initialBoard) {
    this.inputView = inputView;
    this.outputView = outputView;
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
        receivedCommand = CommandFactory.createCommand(initialCommandInput);
        if (!(receivedCommand instanceof StartCommand) && !(receivedCommand instanceof EndCommand)) {
          receivedCommand = null;
          System.out.println(ErrorMessage.INVALID_START_COMMAND.getMessage());
        }
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }

    receivedCommand.execute(this);

    while (isRunning) {
      try {
        receivedCommand = CommandFactory.createCommand(inputView.receiveCommand());
        receivedCommand.execute(this);
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
    board.move(source, target, currentTurn);
    currentTurn = currentTurn.changeTurn(currentTurn);
    outputView.printBoard(board.getMap());
  }
}
