package chess.controller.command;

import chess.controller.command.commands.Command;
import chess.controller.command.commands.ContinueCommand;
import chess.controller.command.commands.EndCommand;
import chess.controller.command.commands.MoveCommand;
import chess.controller.command.commands.NewCommand;
import chess.controller.command.commands.StartCommand;
import chess.controller.command.commands.StatusCommand;
import chess.domain.game.ChessGame;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class CommandFactory {

  private static final int COMMAND_INDEX = 0;

  private static final Map<CommandType, BiFunction<ChessGame, List<String>, Command>> COMMAND_TYPES =
      new EnumMap<>(CommandType.class);

  private static final Map<CommandType, Command> INITIAL_COMMAND_TYPES = new EnumMap<>(CommandType.class);

  static {
    COMMAND_TYPES.put(CommandType.START, (chessGame, ignored) -> new StartCommand(chessGame));
    COMMAND_TYPES.put(CommandType.END, (chessGame, ignored) -> new EndCommand(chessGame));
    COMMAND_TYPES.put(CommandType.STATUS, (chessGame, ignored) -> new StatusCommand(chessGame));
    COMMAND_TYPES.put(CommandType.MOVE, MoveCommand::new);

    INITIAL_COMMAND_TYPES.put(CommandType.NEW, new NewCommand());
    INITIAL_COMMAND_TYPES.put(CommandType.CONTINUE, new ContinueCommand());
  }

  public static Command createCommand(ChessGame chessGame, String command) {
    final List<String> commandParts = List.of(command.split(" "));
    final CommandType commandType = CommandType.findCommand(commandParts.get(COMMAND_INDEX));
    return COMMAND_TYPES.get(commandType).apply(chessGame, commandParts);
  }

  public static Command createInitialCommand(String command) {
    final List<String> commandParts = List.of(command.split(" "));
    final CommandType commandType = CommandType.findCommand(commandParts.get(COMMAND_INDEX));
    return INITIAL_COMMAND_TYPES.get(commandType);
  }
}
