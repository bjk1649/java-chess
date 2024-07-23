package chess.domain.command;

import chess.domain.command.commands.EndCommand;
import chess.domain.command.commands.MoveCommand;
import chess.domain.command.commands.StartCommand;
import chess.domain.ErrorMessage;
import chess.domain.command.commands.StatusCommand;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CommandFactory {

  public static final String START_COMMAND = "start";
  public static final String END_COMMAND = "end";
  public static final String MOVE_COMMAND = "move";
  public static final String STATUS_COMMAND = "status";
  private static final int COMMAND_INDEX = 0;
  private static final int SOURCE_POSITION_INDEX = 1;
  private static final int TARGET_POSITION_INDEX = 2;
  private static final int MOVE_COMMAND_PARTS_COUNT = 3;

  private static final Map<String, Function<List<String>, CommandLauncher>>
      commandMap = new HashMap<>();

  static {
    commandMap.put(START_COMMAND, parts -> new StartCommand());
    commandMap.put(END_COMMAND, parts -> new EndCommand());
    commandMap.put(MOVE_COMMAND, parts -> {
      if (parts.size() != MOVE_COMMAND_PARTS_COUNT) {
        throw new IllegalArgumentException(ErrorMessage.INVALID_MOVE_COMMAND.getMessage());
      }
      Position source = parsePosition(parts.get(SOURCE_POSITION_INDEX));
      Position target = parsePosition(parts.get(TARGET_POSITION_INDEX));
      return new MoveCommand(source, target);
    });
    commandMap.put(STATUS_COMMAND, parts -> new StatusCommand());
  }

  public CommandLauncher createCommand(String inputCommand) {
    List<String> commandParts = List.of(inputCommand.split(" "));
    String command = commandParts.get(COMMAND_INDEX);

    Function<List<String>, CommandLauncher> commandCreator = commandMap.get(command);
    if (commandCreator == null) {
      throw new IllegalArgumentException(ErrorMessage.INVALID_COMMAND.getMessage());
    }
    return commandCreator.apply(commandParts);
  }

  private static Position parsePosition(String position) {
    int file = position.charAt(0) - 'a' + 1;
    int rank = position.charAt(1) - '0';
    return new Position(file, rank); // 생성자에 1~8 유효성 검사가 있다
  }
}
