package chess.controller.command;

import chess.domain.ErrorMessage;
import java.util.Arrays;

public enum Command {
  START("start"),
  END("end"),
  STATUS("status"),
  MOVE("move");

  private final String command;

  Command(final String command) {
    this.command = command;
  }

  public static Command findCommand(final String value) {
    return Arrays.stream(values())
                 .filter(command -> command.command.equals(value))
                 .findAny()
                 .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_COMMAND.getMessage()));
  }
}
