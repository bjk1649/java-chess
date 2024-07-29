package chess.controller.command;

import chess.domain.ErrorMessage;
import java.util.Arrays;

public enum CommandType {
  START("start"),
  END("end"),
  STATUS("status"),
  MOVE("move"),
  NEW("new"),
  CONTINUE("continue");

  private final String command;

  CommandType(final String command) {
    this.command = command;
  }

  public static CommandType findCommand(final String value) {
    return Arrays.stream(values())
                 .filter(commandType -> commandType.command.equals(value))
                 .findAny()
                 .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_COMMAND.getMessage()));
  }
}
