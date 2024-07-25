package chess.controller.command;

import chess.domain.ErrorMessage;
import java.util.Arrays;

public enum InitialCommand {
  NEW("new"),
  CONTINUE("continue");

  private final String command;

  InitialCommand(String command) {
    this.command = command;
  }

  public static InitialCommand findCommand(final String value) {
    return Arrays.stream(values())
                 .filter(command -> command.command.equals(value))
                 .findAny()
                 .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_COMMAND.getMessage()));
  }
}
