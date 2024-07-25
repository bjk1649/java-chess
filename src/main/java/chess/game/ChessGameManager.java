package chess.game;

import chess.board.Board;
import chess.dao.ChessGameDao;
import chess.piece.Piece;
import chess.ui.InputView;
import chess.ui.OutputView;
import chess.position.Position;
import chess.piece.Team;

public class ChessGameManager {
    private static final String START_COMMAND = "start";
    private static final String STATUS_COMMAND = "status";
    private static final String END_COMMAND = "end";
    private static final String CREATE_NEW_ROOM_COMMAND = "create";
    private Team turn = Team.WHITE;
    private boolean isGameOver = false;
    private ChessGameDao chessGameDao;
    private int gameId;

    public ChessGameManager() {
        this.chessGameDao = new ChessGameDao();
    }

    public void startNewGame() {
        OutputView.printStartMessage();
        String command = InputView.inputCommand();
        if (command.equals(START_COMMAND)) {
            Board board = new Board();
            selectRoom();
            loadGame(board);
            OutputView.printBoard(board);
            proceedGame(board);
        }
        if (command.equals(CREATE_NEW_ROOM_COMMAND)) {
            createNewRoom();
            startNewGame();
        }
    }

    public void createNewRoom() {
        OutputView.printCreateRoomMessage();
        chessGameDao.createNewRoom();
    }

    public void proceedGame(Board board) {
        if (isGameOver) {
            changeStatus(gameId);
            return;
        }
        String command = InputView.inputCommand();
        if (command.equals(END_COMMAND)) {
            saveGame(gameId, board);
            return;
        }
        if (!command.equals(STATUS_COMMAND)) {
            moveProcess(board, command);
            saveGame(gameId, board);
            proceedGame(board);
        }
        if (command.equals(STATUS_COMMAND)) {
            OutputView.printEndGameByStatusMessage(board);
            changeStatus(gameId);
        }
    }

    public void moveProcess(Board board, String command) {
        String startPosition = InputView.extractStartPosition(command);
        String targetPosition = InputView.extractTargetPosition(command);

        Position start = searchPosition(startPosition);
        Position target = searchPosition(targetPosition);

        verifyTurn(board.findPiece(start));
        board.verifyPath(start, target);
        if (board.findPiece(target).isKing()) {
            board.movePiece(start, target);
            OutputView.printBoard(board);
            OutputView.printEndGameMessage(turn);
            isGameOver = true;
            return;
        }
        board.movePiece(start, target);
        OutputView.printBoard(board);
        turn = turn.changeTurn();
    }

    public void selectRoom() {
        OutputView.printSelectRoomMessage();
        int roomNumber = Integer.parseInt(InputView.inputCommand());
        if (chessGameDao.getCurrentStatus(roomNumber).equals(END_COMMAND)) {
            throw new IllegalStateException("종료된 게임입니다.");
        }
        OutputView.printEnterRoomMessage(chessGameDao.selectRoom(roomNumber));
        gameId = roomNumber;
    }

    public void saveGame(int gameId, Board board) {
        String status = "playing";
        chessGameDao.saveGame(gameId, board.convertBoardStateToString(), getTurnState(), status);
    }

    public void loadGame(Board board) {
        chessGameDao.updateCurrentBoard(gameId, board);
        turn = chessGameDao.getCurrentTurn(gameId, turn);
    }

    public void changeStatus(int gameId) {
        chessGameDao.changeStatus(gameId);
    }

    public Position searchPosition(String location) {
        int file = location.charAt(0) - 'a' + 1;
        int rank = location.charAt(1) - '0';

        return new Position(file, rank);
    }

    public void verifyTurn(Piece piece) {
        if (!piece.isSameTeam(turn)) {
            throw new IllegalStateException("상대방 기물을 이동시킬 수 없습니다.");
        }
    }

    private boolean getTurnState() {
        return turn.isSameTeam(Team.WHITE);
    }
}
