package chess.controller.command;

import chess.domain.ErrorMessage;
import java.util.Arrays;

public enum InitialCommandType {
  NEW("new"),
  CONTINUE("continue");

  private final String command;

  InitialCommandType(String command) {
    this.command = command;
  }

  public static InitialCommandType findCommand(final String value) {
    return Arrays.stream(values())
                 .filter(command -> command.command.equals(value))
                 .findAny()
                 .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_INITIAL_COMMAND.getMessage()));
  }
}
